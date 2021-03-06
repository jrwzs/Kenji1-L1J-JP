/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jp.l1j.server.packets.client;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.logging.Logger;
import jp.l1j.configure.Config;
import jp.l1j.server.ClientThread;
import jp.l1j.server.datatables.AuctionHouseTable;
import jp.l1j.server.datatables.CharacterTable;
import jp.l1j.server.datatables.HouseTable;
import jp.l1j.server.datatables.InnKeyTable;
import jp.l1j.server.datatables.InnTable;
import jp.l1j.server.datatables.ItemTable;
import jp.l1j.server.datatables.NpcActionTable;
import jp.l1j.server.model.instance.L1ItemInstance;
import jp.l1j.server.model.instance.L1NpcInstance;
import jp.l1j.server.model.instance.L1PcInstance;
import jp.l1j.server.model.L1World;
import jp.l1j.server.model.inventory.L1Inventory;
import jp.l1j.server.model.item.L1ItemId;
import jp.l1j.server.model.npc.L1NpcHtml;
import jp.l1j.server.model.npc.action.L1NpcAction;
import jp.l1j.server.packets.server.S_NpcTalkReturn;
import jp.l1j.server.packets.server.S_ServerMessage;
import jp.l1j.server.templates.L1AuctionHouse;
import jp.l1j.server.templates.L1House;
import jp.l1j.server.templates.L1Inn;
import jp.l1j.server.templates.L1InventoryItem;

// Referenced classes of package jp.l1j.server.clientpackets:
// ClientBasePacket, C_Amount

public class C_Amount extends ClientBasePacket {

	private static Logger _log = Logger.getLogger(C_Amount.class
			.getName());
	private static final String C_AMOUNT = "[C] C_Amount";

	public C_Amount(byte[] decrypt, ClientThread client) throws Exception {
		super(decrypt);
		int objectId = readD();
		int amount = readD();
		int c = readC();
		String s = readS();

		L1PcInstance pc = client.getActiveChar();
		L1NpcInstance npc = (L1NpcInstance) L1World.getInstance().findObject(
				objectId);
		if (npc == null) {
			return;
		}

		String s1 = "";
		String s2 = "";
		try {
			StringTokenizer stringtokenizer = new StringTokenizer(s);
			s1 = stringtokenizer.nextToken();
			s2 = stringtokenizer.nextToken();
		} catch (NoSuchElementException e) {
			s1 = "";
			s2 = "";
		}
		if (s1.equalsIgnoreCase("agapply")) { // ???????????????????????????
			String pcName = pc.getName();
			AuctionHouseTable boardTable = new AuctionHouseTable();
			for (L1AuctionHouse board : boardTable.getAuctionBoardTableList()) {
				if (pcName.equalsIgnoreCase(board.getBidderName())) {
					pc.sendPackets(new S_ServerMessage(523)); // ??????????????????????????????????????????????????????
					return;
				}
			}
			int houseId = Integer.valueOf(s2);
			L1AuctionHouse board = boardTable.getAuctionBoardTable(houseId);
			if (board != null) {
				int nowPrice = board.getPrice();
				int nowBidderId = board.getBidderId();
				if (pc.getInventory().consumeItem(L1ItemId.ADENA, amount)) {
					// ????????????????????????
					board.setPrice(amount);
					board.setBidderId(pc.getId());
					board.setBidderName(pc.getName());
					boardTable.updateAuctionBoard(board);
					if (nowBidderId != 0) {
						// ??????????????????????????????
						L1PcInstance bidPc = (L1PcInstance) L1World
								.getInstance().findObject(nowBidderId);
						if (bidPc != null) { // ??????????????????
							bidPc.getInventory().storeItem(L1ItemId.ADENA,
									nowPrice);
							// ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????%n
							// ??????????????????????????????%0?????????????????????????????????%n????????????????????????????????????%n%n
							bidPc.sendPackets(new S_ServerMessage(525, String
									.valueOf(nowPrice)));
						} else { // ??????????????????
							L1ItemInstance item = ItemTable.getInstance()
									.createItem(L1ItemId.ADENA);
							item.setCount(nowPrice);
							item.setOwner(nowBidderId,
									L1InventoryItem.LOC_CHARACTER);
							item.save();
						}
					}
				} else {
					pc.sendPackets(new S_ServerMessage(189)); // \f1????????????????????????????????????
				}
			}
		} else if (s1.equalsIgnoreCase("agsell")) { // ?????????????????????
			int houseId = Integer.valueOf(s2);
			AuctionHouseTable boardTable = new AuctionHouseTable();
			L1AuctionHouse board = new L1AuctionHouse();
			if (board != null) {
				// ????????????????????????????????????
				board.setHouseId(houseId);
				L1House house = HouseTable.getInstance().getHouseTable(houseId);
				TimeZone tz = TimeZone.getTimeZone(Config.TIME_ZONE);
				Calendar cal = Calendar.getInstance(tz);
				cal.add(Calendar.DATE, 5); // 5??????
				cal.set(Calendar.MINUTE, 0); // ????????????????????????
				cal.set(Calendar.SECOND, 0);
				board.setDeadline(cal);
				board.setPrice(amount);
				board.setOwnerId(pc.getId());	
				board.setOwnerName(pc.getName());
				board.setBidderId(0);
				board.setBidderName(null);
				boardTable.insertAuctionBoard(board);

				house.setOnSale(true); // ??????????????????
				house.setPurchaseBasement(true); // ?????????????????????????????????
				HouseTable.getInstance().updateHouse(house); // DB???????????????
			}
		} else {
			// ??????NPC
			int npcId = npc.getNpcId();
			if (npcId == 70070 || npcId == 70019 || npcId == 70075
					|| npcId == 70012 || npcId == 70031 || npcId == 70084
					|| npcId == 70065 || npcId == 70054 || npcId == 70096) {

				if (pc.getInventory().checkItem(L1ItemId.ADENA, (300 * amount))) {
					L1Inn inn = InnTable.getInstance().getTemplate(npcId,
							pc.getInnRoomNumber());
					if (inn != null) {
						Timestamp dueTime = inn.getDueTime();
						if (dueTime != null) { // ???????????????????????????
							Calendar cal = Calendar.getInstance();
							if (((cal.getTimeInMillis() - dueTime.getTime()) / 1000) < 0) { // ??????????????????
								pc.sendPackets(new S_NpcTalkReturn(npc.getId(), ""));
								return;
							}
						}
						// ?????????????????????
						Timestamp ts = new Timestamp(System.currentTimeMillis()
								+ (60 * 60 * 4 * 1000));
						L1ItemInstance item = ItemTable.getInstance()
								.createItem(40312); // ???????????????
						if (item != null) {
							item.setKeyId(item.getId()); // ?????????????????????
							item.setInnNpcId(npcId); // ??????NPC
							item.setHall(pc.checkRoomOrHall()); // ????????? or ?????????
							item.setDueTime(ts); // ????????????
							item.setCount(amount); // ????????????

							inn.setKeyId(item.getKeyId()); // ???????????????
							inn.setLodgerId(pc.getId()); // ??????PC
							inn.setHall(pc.checkRoomOrHall()); // ????????? or ?????????
							inn.setDueTime(ts); // ????????????
							InnTable.getInstance().updateInn(inn);

							pc.getInventory().consumeItem(L1ItemId.ADENA,
									(300 * amount));
							L1Inventory inventory;
							if (pc.getInventory().checkAddItem(item, amount) == L1Inventory.OK) {
								inventory = pc.getInventory();
							} else {
								inventory = L1World.getInstance().getInventory(
										pc.getLocation());
							}
							inventory.storeItem(item);

							if (InnKeyTable.hasKey(item)) {
								InnKeyTable.deleteKey(item);
								InnKeyTable.storeKey(item);
							} else {
								InnKeyTable.storeKey(item);
							}

							String itemName = (item.getItem().getName() + item
									.getInnKeyName());
							if (amount > 1) {
								itemName = (itemName + " (" + amount + ")");
							}
							String[] msg = { npc.getName() };
							pc.sendPackets(new S_NpcTalkReturn(npc.getId(), "inn4",
									msg));
						}
					}
				} else {
					String[] msg = { npc.getName() };
					pc.sendPackets(new S_NpcTalkReturn(npc.getId(), "inn3", msg));
				}
			} else {
				L1NpcAction action = NpcActionTable.getInstance().get(s, pc,
						npc);
				if (action != null) {
					L1NpcHtml result = action.executeWithAmount(s, pc, npc,
							amount);
					if (result != null) {
						pc
								.sendPackets(new S_NpcTalkReturn(npc.getId(),
										result));
					}
					return;
				}
			}
		}
	}

	@Override
	public String getType() {
		return C_AMOUNT;
	}
}