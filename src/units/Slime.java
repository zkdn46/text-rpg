package units;

import java.io.IOException;

import textrpg.TextRPG;

public class Slime extends Monster {

	public Slime(String classType, int level, int hp, int mp, int att, int def) {
		super(classType, level, hp+20, mp, att, def+3);
		
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
		
		target.hp -= damage;

		if (mp >= 20 && skill == 1) {
			mp -= 20;

			target.mp -= damage/2;

			TextRPG.buffer.setLength(0);
			TextRPG.buffer.append("마나공격!\n");
			TextRPG.buffer.append("마나 피해 " + damage/2 + " 입힘\n");
			try {
				TextRPG.writer.append(TextRPG.buffer);
				TextRPG.writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		TextRPG.buffer.setLength(0);
		TextRPG.buffer.append(target.classType + "에게 피해 " + damage+" 입힘\n");
		try {
			TextRPG.writer.append(TextRPG.buffer);
			TextRPG.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
