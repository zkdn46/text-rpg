package stage;

import java.io.IOException;
import java.util.ArrayList;

import textrpg.Player;
import units.Hero;
import units.Monster;

public class Dungeon {
	private final int ORC = 0;
	private final int BAT = 1;
	private final int SLIME = 2;

	private static Dungeon instance = new Dungeon();

	public static Dungeon getInstance() {
		return instance;
	}

	private int lv;

	private Player player = Player.getInstance();

	private ArrayList<units.Hero> heros = new ArrayList<>();
	private ArrayList<units.Hero> battleHeros = new ArrayList<>();
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
		isRun = true;
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
		battleHeros.clear();
		heros.clear();
		for (int i = 0; i < player.guilds.size(); i++) {
			if (player.guilds.get(i).isParty()) {
				heros.add(player.guilds.get(i));
				battleHeros.add(player.guilds.get(i));
			}
		}
	}

	private void CreateMonsterParty() {
		monsters.clear();
		for (int i = 0; i < 4; i++) {
			int ranMonster = Guild.ran.nextInt(3);
			int ranHp = Guild.ran.nextInt(20) + 40;
			int ranMp = Guild.ran.nextInt(20) + 40;
			int ranAtt = Guild.ran.nextInt(4);
			int ranDef = Guild.ran.nextInt(3);

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
			action();
		}
		recovery();
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

		i = 1;
		for (Hero heroList : battleHeros) {
			textrpg.TextRPG.buffer.setLength(0);
			textrpg.TextRPG.buffer.append(i++ + ") ");
			textrpg.TextRPG.buffer.append(heroList + "\n");
			try {
				textrpg.TextRPG.writer.append(textrpg.TextRPG.buffer);
				textrpg.TextRPG.writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void action() {
		int i = 1;
		while (i <= Guild.partyCnt) {
			printBattle();
			int sel = textrpg.TextRPG.input(i + "번 HERO [1]공격 [2]스킬 [0]종료: ");

			if (sel < 0 || sel > 2) {
				System.err.println("0 ~ 2 입력!");
				return;
			}

			if (sel == 0) {
				isRun = false;
				break;
			}
			
			attack(i - 1, sel);
			
			i++;
			
			if (i > Guild.partyCnt) {
				i = 1;
				monsterAttack();
			}

			if (battleHeros.size() == 0) {
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
			} else if (monsters.size() == 0) {
				result();
				isRun = false;
				break;
			}
		}
	}

	private void result() {
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

		int ranExp = (Guild.ran.nextInt(50) + (5 * lv));

		for (int i = 0; i < heros.size(); i++) {
			Hero target = heros.get(i);
			target.plusExp(ranExp);
			if (target.getExp() >= 100) {
				target.plusExp(-100);
				target.levelup();

				textrpg.TextRPG.buffer.setLength(0);
				textrpg.TextRPG.buffer.append("레벨업!\n");
				try {
					textrpg.TextRPG.writer.append(textrpg.TextRPG.buffer);
					textrpg.TextRPG.writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void attack(int idx, int sel) {
		int ranAttack = Guild.ran.nextInt(monsters.size());

		if (sel == 1) {
			battleHeros.get(idx).attack(monsters.get(ranAttack));

		} else if (sel == 2) {
			battleHeros.get(idx).skill(monsters.get(ranAttack), battleHeros, monsters);
		}

		for (int i = 0; i < monsters.size(); i++) {
			if (monsters.get(i).getHp() < 1) {
				monsters.remove(i);
			}
		}
	}

	private void monsterAttack() {
		for (int i = 0; i < battleHeros.size(); i++) {
			int ranAttack = Guild.ran.nextInt(battleHeros.size());
			monsters.get(i).attack(battleHeros.get(ranAttack));
			if (battleHeros.get(ranAttack).getHp() < 1) {
				battleHeros.remove(ranAttack);
			}
		}
	}

	private void recovery() {
		for (int i = 0; i < heros.size(); i++) {
			Hero target = heros.get(i);
			target.setHp(target.getMaxHp());
			target.setMp(target.getMaxMp());
		}
	}

}