package textrpg;

import java.io.IOException;
import java.util.ArrayList;

import testrpg.Main;

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
	
	private void PrintEquip(int x) {
		if (x == -1) {
			isRun = false;
		}

		if (x < 0 || x > guilds.size()) {
			return;
		}
		guilds.get(x).printStatus();
		guilds.get(x).printEquitedItem();

		printItemList();

		equip(x, TextRPG.input("종료(0)번 / 장착할 장비 선택: ") - 1);
	}
	
}