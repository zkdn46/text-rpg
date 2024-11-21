package units;

import java.io.IOException;

import textrpg.TextRPG;

public class Bat extends Monster {

	public Bat(String classType, int level, int hp, int mp, int att, int def) {
		super(classType, level, hp, mp, att + 5, def);
	}

	public void attack(Unit enenmy) {
		Hero target = (Hero) enenmy;
		int skill = Orc.ran.nextInt(3);
		int damage = 0;

		damage = att - target.def;

		if (damage < 1) {
			System.err.println("MISS!");
			return;
		}

		if (mp >= 20 && skill == 1) {
			mp -= 20;

			target.hp -= damage;
			hp += damage / 2;
			if (hp > maxHp) {
				hp = maxHp;
			}

			TextRPG.buffer.setLength(0);
			TextRPG.buffer.append("흡혈!\n");
			TextRPG.buffer.append("박쥐 HP " + damage / 2 + " 회복\n");
			try {
				TextRPG.writer.append(TextRPG.buffer);
				TextRPG.writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			target.hp -= damage;
		}

		TextRPG.buffer.setLength(0);
		TextRPG.buffer.append(target.classType + "에게 피해 " + damage + " 입힘\n");
		try {
			TextRPG.writer.append(TextRPG.buffer);
			TextRPG.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
