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

package jp.l1j.server.packets.server;

import java.util.List;
import java.util.logging.Logger;
import jp.l1j.server.codes.Opcodes;
import jp.l1j.server.model.instance.L1ItemInstance;

// Referenced classes of package jp.l1j.server.serverpackets:
// ServerBasePacket

public class S_InvList extends ServerBasePacket {

	private static Logger _log = Logger.getLogger(S_InvList.class.getName());

	private static final String S_INV_LIST = "[S] S_InvList";

	/**
	 * インベントリにアイテムを複数個まとめて追加する。
	 */
	public S_InvList(List<L1ItemInstance> items) {
		writeC(Opcodes.S_OPCODE_INVLIST);
		writeC(items.size()); // アイテム数

		for (L1ItemInstance item : items) {
			writeD(item.getId());
			writeC(item.getItem().getUseType());
			writeC(0);
			writeH(item.getGfxId());
			writeC(item.getStatusForPacket());
			writeD(item.getCount());
			writeC((item.isIdentified()) ? 1 : 0);
			writeS(item.getViewName());
			if (!item.isIdentified()) {
				// 未鑑定の場合ステータスを送る必要はない
				writeC(0);
			} else {
				byte[] status = item.getStatusBytes();
				writeC(status.length);
				for (byte b : status) {
					writeC(b);
				}
			}
		}
	}

	@Override
	public byte[] getContent() {
		return _bao.toByteArray();
	}

	@Override
	public String getType() {
		return S_INV_LIST;
	}
}
