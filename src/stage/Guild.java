package stage;

import java.io.IOException;

public class Guild {
	private final int VIEW = 1;
	private final int ADD = 2;
	private final int LEAVE = 3;
	private final int PARTY = 4;

	private textrpg.Player player = textrpg.Player.getInstance();

	private static Guild instance = new Guild();

	public static Guild getInstance() {
		return instance;
	}

	public void guildRun() {
		printMenu();
		guildSystem();
	}

	private void printMenu() {
		textrpg.TextRPG.buffer.setLength(0);
		textrpg.TextRPG.buffer.append("=== 더조은길드 === \n");
		textrpg.TextRPG.buffer.append("1)길드원목록 2)길드원모집 3)길드원추방 4)파티교체 *)처음으로\n");
		try {
			textrpg.TextRPG.writer.append(textrpg.TextRPG.buffer);
			textrpg.TextRPG.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void guildSystem() {
		int sel = textrpg.TextRPG.input("메뉴 선택: ");

		if (sel == VIEW) {
			player.guildList();
		} else if (sel == ADD) {

		} else if (sel == LEAVE) {
			leaveGuild();
		} else if (sel == PARTY) {

		}
	}

	private void leaveGuild() {
		player.guildList();

		int sel = textrpg.TextRPG.input("추방 할 길드원 선택: ") - 1;

		if (sel < 0 || sel >= player.guilds.size()) {
			return;
		}

		player.guilds.remove(sel);
		textrpg.TextRPG.buffer.setLength(0);
		textrpg.TextRPG.buffer.append("추방완료\n");
		try {
			textrpg.TextRPG.writer.append(textrpg.TextRPG.buffer);
			textrpg.TextRPG.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}