package units;

public abstract class Monster extends Unit {

	protected Monster(String classType, int level, int hp, int mp, int att, int def) {
		super(classType, level, hp + (25 * level), mp + (5 * level), att + (2 * level), def + (2 * level));
	}
	
}