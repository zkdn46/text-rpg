package units;

public class Orc extends Monster {

	public Orc(String classType, int level, int hp, int mp, int att, int def) {
		super(classType, level, hp+30, mp, att+5, def);
		
	}
	
	public void attack() {
		
	}

}
