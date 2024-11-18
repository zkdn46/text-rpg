package stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Guild {
	private final int VIEW = 1;
	private final int ADD = 2;
	private final int LEAVE = 3;
	private final int PARTY = 4;

	private Random ran = new Random();

	private textrpg.Player player = textrpg.Player.getInstance();
	private ArrayList<units.Hero> buyGuild = new ArrayList<>();

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
			addGuild();
		} else if (sel == LEAVE) {
			leaveGuild();
		} else if (sel == PARTY) {

		}
	}

	private void addGuild() {
		CreateGuild();
		printBuyGuild();
		int sel = textrpg.TextRPG.input("모집 할 길드원 선택: ") - 1;

		player.guilds.add(buyGuild.get(sel));
	}

	private void CreateGuild() {
		buyGuild.clear();
		for (int i = 0; i < 3; i++) {
			int ranHp = ran.nextInt(20) + 90;
			int ranMp = ran.nextInt(20) + 90;
			int ranAtt = ran.nextInt(10) + 1;
			int ranDef = ran.nextInt(10) + 1;
			buyGuild.add(new units.Hero(CreateClassType(), 1, ranHp, ranMp, ranAtt, ranDef));
		}
	}
	
	private String CreateClassType() {
		String[] classTypes = { "전사", "도적", "마법사", "힐러" };
		int ranClass = ran.nextInt(4);
		String ranclass = classTypes[ranClass];

		return ranclass;
	}

	private void printBuyGuild() {
		for (int i = 0; i < buyGuild.size(); i++) {
			System.out.print(i + 1 + ")");
			buyGuild.get(i).printStatus();
			buyGuild.get(i).printEquitedItem();
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