package units;

import java.io.IOException;
import java.util.ArrayList;

import textrpg.TextRPG;

public class Hero extends Unit {
	private int exp;
	private boolean isParty;

	public int getExp() {
		return exp;
	}

	public void plusExp(int exp) {
		this.exp += exp;
	}

	public boolean isParty() {
		return isParty;
	}

	public void setParty(boolean isParty) {
		this.isParty = isParty;
	}

	private textrpg.Item weapon;
	private textrpg.Item armor;
	private textrpg.Item ring;

	public textrpg.Item getWeapon() {
		return weapon;
	}

	public void setWeapon(textrpg.Item weapon) {
		this.weapon = weapon;
	}

	public void setArmor(textrpg.Item armor) {
		this.armor = armor;
	}

	public void setRing(textrpg.Item ring) {
		this.ring = ring;
	}

	public textrpg.Item getArmor() {
		return armor;
	}

	public textrpg.Item getRing() {
		return ring;
	}

	public Hero(String classType, int level, int hp, int mp, int att, int def) {
		super(classType, level, hp, mp, att, def);
	}

	protected Hero() {

	}

	public void printStatus() {
		TextRPG.buffer.setLength(0);
		TextRPG.buffer.append(" [직업: " + classType + "] ");
		TextRPG.buffer.append("[Lv." + level + "] ");
		TextRPG.buffer.append("[파티: " + isParty + "] ");
		TextRPG.buffer.append("\n");
		if (ring != null) {
			TextRPG.buffer.append("[HP: " + hp + " + " + ring.getPower());
		} else {
			TextRPG.buffer.append("[HP: " + hp);
		}
		if (ring != null) {
			TextRPG.buffer.append(" / " + maxHp + " + (" + ring.getPower() + ")] ");
		} else {
			TextRPG.buffer.append(" / " + maxHp + "] ");
		}
		TextRPG.buffer.append("[MP: " + mp + " / " + maxMp + "] ");
		if (weapon != null) {
			TextRPG.buffer.append("[ATT: " + att + " + (" + weapon.getPower() + ")] ");
		} else {
			TextRPG.buffer.append("[ATT: " + att + "] ");
		}
		if (armor != null) {
			TextRPG.buffer.append("[DEF: " + def + " + (" + armor.getPower() + ")] ");
		} else {
			TextRPG.buffer.append("[DEF: " + def + "] ");
		}
		TextRPG.buffer.append("\n");
		try {
			TextRPG.writer.append(TextRPG.buffer);
			TextRPG.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void printEquitedItem() {
		TextRPG.buffer.setLength(0);
		if (weapon == null) {
			TextRPG.buffer.append("[무기: 없음] ");
		} else {
			TextRPG.buffer.append("[무기: " + weapon.getName() + "] ");
		}
		if (armor == null) {
			TextRPG.buffer.append("[방어구: 없음] ");
		} else {
			TextRPG.buffer.append("[방어구: " + armor.getName() + "] ");
		}
		if (ring == null) {
			TextRPG.buffer.append("[반지: 없음] ");
		} else {
			TextRPG.buffer.append("[반지: " + ring.getName() + "] ");
		}
		TextRPG.buffer.append("\n");
		try {
			TextRPG.writer.append(TextRPG.buffer);
			TextRPG.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean attack(Unit monster) {
		Monster target = (Monster) monster;

		int damage = att - target.def;
		if (damage < 1) {
			System.err.println("MISS!");
			return false;
		}

		target.hp -= damage;

		TextRPG.buffer.setLength(0);
		TextRPG.buffer.append(monster.classType + "에게 피해 " + damage + " 입힘\n");
		try {
			TextRPG.writer.append(TextRPG.buffer);
			TextRPG.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (target.hp < 1) {
			TextRPG.buffer.setLength(0);
			TextRPG.buffer.append("몬스터 사망\n");
			try {
				TextRPG.writer.append(TextRPG.buffer);
				TextRPG.writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}

	}

	public boolean skill(Unit monster, ArrayList<Hero> heros, ArrayList<Monster> monsters) {
		Monster target = (Monster) monster;

		if (classType.equals("전사")) {
			if (mp < 20) {
				System.err.println("마나 부족! 일반 공격!");
				attack(target);
			} else {
				mp -= 20;

				int damage = att * 2 - target.def;
				if (damage < 1) {
					System.err.println("MISS!");
					return false;
				}

				target.hp -= damage;

				TextRPG.buffer.setLength(0);
				TextRPG.buffer.append("파워스트라이크!\n");
				TextRPG.buffer.append(monster.classType + "에게 피해 " + damage + " 입힘\n");
				try {
					TextRPG.writer.append(TextRPG.buffer);
					TextRPG.writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		} else if (classType.equals("마법사")) {
			if (mp < 20) {
				System.err.println("마나 부족! 일반 공격!");
				attack(target);
			} else {
				mp -= 20;

				TextRPG.buffer.setLength(0);
				TextRPG.buffer.append("전체 공격!\n");
				try {
					TextRPG.writer.append(TextRPG.buffer);
					TextRPG.writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}

				for (Monster monsterAll : monsters) {
					int damage = att - monsterAll.def;
					if (damage < 1) {
						System.err.println("MISS!");
					} else {
						monsterAll.hp -= damage;
					}
				}

			}
		} else if (classType.equals("힐러")) {
			if (mp < 20) {
				System.err.println("마나 부족! 일반 공격!");
				attack(target);
			} else {
				mp -= 30;

				TextRPG.buffer.setLength(0);
				TextRPG.buffer.append("광역 회복!\n");
				try {
					TextRPG.writer.append(TextRPG.buffer);
					TextRPG.writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}

				for (Hero heroAll : heros) {
					heroAll.hp += 10;
					if (heroAll.hp > heroAll.maxHp) {
						heroAll.hp = heroAll.maxHp;
					}
					heroAll.mp += 10;
					if (heroAll.mp > heroAll.maxMp) {
						heroAll.mp = heroAll.maxMp;
					}
				}

			}
		}

		if (target.hp < 1) {
			TextRPG.buffer.setLength(0);
			TextRPG.buffer.append("몬스터 사망\n");
			try {
				TextRPG.writer.append(TextRPG.buffer);
				TextRPG.writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}

	}

}
