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

package jp.l1j.server.command.executor;

import java.util.logging.Logger;
import jp.l1j.server.model.instance.L1PcInstance;
import jp.l1j.server.model.instance.L1TrapInstance;
import jp.l1j.server.model.L1Object;
import static jp.l1j.server.model.skill.L1SkillId.*;
import jp.l1j.server.packets.server.S_RemoveObject;
import jp.l1j.server.packets.server.S_SystemMessage;

public class L1ShowTrap implements L1CommandExecutor {
	private static Logger _log = Logger.getLogger(L1ShowTrap.class.getName());

	private L1ShowTrap() {
	}

	public static L1CommandExecutor getInstance() {
		return new L1ShowTrap();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {
		if (arg.equalsIgnoreCase("on")) {
			pc.setSkillEffect(GMSTATUS_SHOWTRAPS, 0);
		} else if (arg.equalsIgnoreCase("off")) {
			pc.removeSkillEffect(GMSTATUS_SHOWTRAPS);

			for (L1Object obj : pc.getKnownObjects()) {
				if (obj instanceof L1TrapInstance) {
					pc.removeKnownObject(obj);
					pc.sendPackets(new S_RemoveObject(obj));
				}
			}
		} else {
			pc.sendPackets(new S_SystemMessage(cmdName + " on|off と入力してください。"));
		}
	}
}
