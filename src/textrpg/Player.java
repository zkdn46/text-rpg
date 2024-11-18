package textrpg;

import java.io.IOException;
import java.util.ArrayList;

public class Player {
	private boolean isRun;
	public int money = 10000;

	public ArrayList<Item> items = new ArrayList<>();
	public ArrayList<units.Hero> guilds = new ArrayList<>();
	public ArrayList<units.Hero> partys = new ArrayList<>();
	
	private static Player instance = new Player();

	public static Player getInstance() {
		return instance;
	}

	public void inventory() {
		isRun = true;
		guilds.add(new units.Hero("뭐", 1, 1, 1, 1, 1));
		guildList();
	}
	
	private void guildList() {
		if (guilds.size() == 0) {
			TextRPG.buffer.setLength(0);
			TextRPG.buffer.append("길드원이 없습니다.\n");
			try {
				TextRPG.writer.append(TextRPG.buffer);
				TextRPG.writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		while (isRun) {
			for (int i = 0; i < guilds.size(); i++) {
				System.out.print(i + 1 + ")");
				guilds.get(i).printStatus();
				guilds.get(i).printEquitedItem();
			}
			PrintEquip(TextRPG.input("뒤로가기(0)번 / 길드원 선택: ") - 1);
		}
	}
}
