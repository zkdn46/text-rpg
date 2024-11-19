package textrpg;

import java.io.IOException;
import java.util.ArrayList;

public class Player {
	private boolean isRun;
	private int money = 3000;

	public ArrayList<Item> items = new ArrayList<>();
	public ArrayList<units.Hero> guilds = new ArrayList<>();

	private static Player instance = new Player();

	public static Player getInstance() {
		return instance;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void inventory() {
		guildSelect();
	}

	private void guildSelect() {
		isRun = true;
		while (isRun) {
			guildList();
			if (isRun) {
				PrintEquip(TextRPG.input("뒤로가기(0)번 / 길드원 선택: ") - 1);
			}
		}
	}

	public void guildList() {
		if (guilds.size() == 0) {
			System.err.println("길드원이 없습니다.");
			isRun = false;
			return;
		} else {
			for (int i = 0; i < guilds.size(); i++) {
				System.out.print(i + 1 + ")");
				guilds.get(i).printStatus();
				guilds.get(i).printEquitedItem();
			}
		}
	}

	public void partyList() {
		int num = 0;
		for (int i = 0; i < guilds.size(); i++) {
			if (guilds.get(i).isParty()) {
				System.out.print(num++ + 1 + ")");
				guilds.get(i).printStatus();
				guilds.get(i).printEquitedItem();
			}
		}
	}

	private void PrintEquip(int x) {
		if (x == -1) {
			isRun = false;
		}

		if (x < 0 || x >= guilds.size()) {
			return;
		}
		guilds.get(x).printStatus();
		guilds.get(x).printEquitedItem();

		printItemList();

		equip(x, TextRPG.input("종료(0)번 / 장착할 장비 선택: ") - 1);
	}

	private void printItemList() {
		int i = 1;
		for (Item item : items) {
			TextRPG.buffer.setLength(0);
			TextRPG.buffer.append(i++ + "." + item + "\n");
			try {
				TextRPG.writer.append(TextRPG.buffer);
				TextRPG.writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void equip(int idx, int item) {
		if (item == -1) {
			isRun = false;
		}

		if (item < 0 || item >= items.size()) {
			return;
		}

		if (items.get(item).getKind() == Item.WEAPON) {
			if (guilds.get(idx).getWeapon() == null) {
				guilds.get(idx).setWeapon(items.get(item));
				items.remove(item);
			} else {
				items.add(guilds.get(idx).getWeapon());
				guilds.get(idx).setWeapon(items.get(item));
				items.remove(item);
			}
		} else if (items.get(item).getKind() == Item.ARMOR) {
			if (guilds.get(idx).getArmor() == null) {
				guilds.get(idx).setArmor(items.get(item));
				items.remove(item);
			} else {
				items.add(guilds.get(idx).getArmor());
				guilds.get(idx).setArmor(items.get(item));
				items.remove(item);
			}
		} else if (items.get(item).getKind() == Item.RING) {
			if (guilds.get(idx).getRing() == null) {
				guilds.get(idx).setRing(items.get(item));
				items.remove(item);
			} else {
				items.add(guilds.get(idx).getRing());
				guilds.get(idx).setRing(items.get(item));
				items.remove(item);
			}
		}

		TextRPG.buffer.setLength(0);
		TextRPG.buffer.append("장비 장착 완료.\n");
		try {
			TextRPG.writer.append(TextRPG.buffer);
			TextRPG.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}