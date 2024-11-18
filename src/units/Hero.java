package units;

import java.io.IOException;

import textrpg.TextRPG;

public class Hero extends Unit {
	private int exp;
	private boolean isParty;

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
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
		TextRPG.buffer.append("[HP: " + hp + " / " + maxHp + "] ");
		if (ring != null) {
			TextRPG.buffer.append("[MP: " + mp + " + " + ring.getPower());
		} else {
			TextRPG.buffer.append("[MP: " + mp);
		}
		if (ring != null) {
			TextRPG.buffer.append(" / " + maxMp + " + " + ring.getPower() + "] ");
		} else {
			TextRPG.buffer.append(" / " + maxMp + "] ");
		}
		if (weapon != null) {
			TextRPG.buffer.append("[ATT: " + att + " + " + weapon.getPower() + "] ");
		} else {
			TextRPG.buffer.append("[ATT: " + att + "] ");
		}
		if (armor != null) {
			TextRPG.buffer.append("[DEF: " + def + " + " + armor.getPower() + "] ");
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

}
