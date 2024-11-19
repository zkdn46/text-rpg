package units;

import java.io.IOException;
import java.util.Random;

import textrpg.TextRPG;

public class Orc extends Monster {

	private Random ran = new Random();

	public Orc(String classType, int level, int hp, int mp, int att, int def) {
		super(classType, level, hp + 30, mp, att + 5, def);

	}

	public void attack(Unit enenmy) {
		Hero target = (Hero) enenmy;
		int skill = ran.nextInt(3);
		int damage = 0;

		if (mp >= 20 && skill == 1) {
			mp -= 20;

			damage = att * 2 - target.def;

			TextRPG.buffer.setLength(0);
			TextRPG.buffer.append("쎄게 치기!\n");
			try {
				TextRPG.writer.append(TextRPG.buffer);
				TextRPG.writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			damage = att - target.def;
		}

		if (damage < 1) {
			System.err.println("MISS!");
			return;
		}
		target.hp -= damage;

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