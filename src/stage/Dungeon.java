package stage;

import java.io.IOException;
import java.util.ArrayList;

import textrpg.Player;
import units.Monster;

public class Dungeon {
	private final int ORC = 0;
	private final int BAT = 1;
	private final int SLIME = 2;

	private static Dungeon instance = new Dungeon();

	public static Dungeon getInstance() {
		return instance;
	}

	private int killCnt;
	private int monsterKillCnt;
	private int lv;

	private Player player = Player.getInstance();

	private ArrayList<units.Hero> heros = new ArrayList<>();
	private ArrayList<units.Monster> monsters = new ArrayList<>();

	private boolean isRun = true;

	public void dungeonRun() {
		printMenu();
		selectMenu(textrpg.TextRPG.input("1~10 단계 던전 선택: "));
	}

	private void printMenu() {
		textrpg.TextRPG.buffer.setLength(0);
		textrpg.TextRPG.buffer.append("=== 던전 입구 === \n");
		try {
			textrpg.TextRPG.writer.append(textrpg.TextRPG.buffer);
			textrpg.TextRPG.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void selectMenu(int sel) {
		if (Guild.partyCnt == 0) {
			System.err.println("파티원이 없습니다.");
			return;
		}

		if (sel < 1 || sel > 10) {
			System.err.println("1~10단계 선택!");
			return;
		}

		lv = sel;

		battleRun();
	}

	private void battleRun() {
		battleSet();
		battle();
	}

	private void battleSet() {
		CreateHeroParty();
		CreateMonsterParty();
	}

	private void CreateHeroParty() {
		for (int i = 0; i < player.guilds.size(); i++) {
			if (player.guilds.get(i).isParty()) {
				heros.add(player.guilds.get(i));
			}
		}
	}

	private void CreateMonsterParty() {
		for (int i = 0; i < 4; i++) {
			int ranMonster = Guild.ran.nextInt(3);
			int ranHp = Guild.ran.nextInt(20) + 40;
			int ranMp = Guild.ran.nextInt(20) + 40;
			int ranAtt = Guild.ran.nextInt(4);
			int ranDef = Guild.ran.nextInt(4);

			if (ranMonster == ORC) {
				monsters.add(new units.Orc("오크", lv, ranHp, ranMp, ranAtt, ranDef));
			} else if (ranMonster == BAT) {
				monsters.add(new units.Bat("박쥐", lv, ranHp, ranMp, ranAtt, ranDef));
			} else if (ranMonster == SLIME) {
				monsters.add(new units.Slime("슬라임", lv, ranHp, ranMp, ranAtt, ranDef));
			}
		}
	}

	private void battle() {
		while (isRun) {
			printBattle();
			action();
		}
	}

	private void printBattle() {
		textrpg.TextRPG.buffer.setLength(0);
		textrpg.TextRPG.buffer.append("==몬스터 리스트==\n");
		try {
			textrpg.TextRPG.writer.append(textrpg.TextRPG.buffer);
			textrpg.TextRPG.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int i = 1;
		for (Monster monsterList : monsters) {
			textrpg.TextRPG.buffer.setLength(0);
			textrpg.TextRPG.buffer.append(i++ + ") ");
			textrpg.TextRPG.buffer.append(monsterList + "\n");
			try {
				textrpg.TextRPG.writer.append(textrpg.TextRPG.buffer);
				textrpg.TextRPG.writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		textrpg.TextRPG.buffer.setLength(0);
		textrpg.TextRPG.buffer.append("==히어로 리스트==\n");
		try {
			textrpg.TextRPG.writer.append(textrpg.TextRPG.buffer);
			textrpg.TextRPG.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		player.partyList();
	}

	private void action() {
		int i = 1;
		while (i < 4) {
			attack(i - 1, textrpg.TextRPG.input(i++ + "번 HERO [1]공격 [2]스킬: "));

			if (i == 4) {
				i = 1;
				monsterAttack();
			}

			if (killCnt == Guild.partyCnt) {
				textrpg.TextRPG.buffer.setLength(0);
				textrpg.TextRPG.buffer.append("전투 패배...\n");
				try {
					textrpg.TextRPG.writer.append(textrpg.TextRPG.buffer);
					textrpg.TextRPG.writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}

				isRun = false;
				break;
			} else if (monsterKillCnt == 4) {
				int ranMoney = (Guild.ran.nextInt(500) + 800) * lv;

				textrpg.TextRPG.buffer.setLength(0);
				textrpg.TextRPG.buffer.append("전투 승리! ");
				textrpg.TextRPG.buffer.append(ranMoney + "골드 획득\n");
				try {
					textrpg.TextRPG.writer.append(textrpg.TextRPG.buffer);
					textrpg.TextRPG.writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}

				player.setMoney(player.getMoney() + ranMoney);
				isRun = false;
				break;
			}
		}
	}

	private void attack(int idx, int sel) {
		int ranAttack = Guild.ran.nextInt(3);

		if (sel == 1) {
			heros.get(idx).attack(monsters.get(ranAttack));
		} else if (sel == 2) {
			heros.get(idx).skill(monsters.get(ranAttack));
		}
	}

	private void monsterAttack() {

	}

}