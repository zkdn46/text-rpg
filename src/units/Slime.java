package units;

public class Slime extends Monster {

	public Slime(String classType, int level, int hp, int mp, int att, int def) {
		super(classType, level, hp+20, mp, att, def+5);
		
	}

}
