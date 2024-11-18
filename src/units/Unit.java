package units;

public abstract class Unit {
	protected String classType;
	protected int hp;
	protected int maxHp;
	protected int mp;
	protected int maxMp;
	protected int att;
	protected int def;
	protected int level;

	protected Unit() {

	}

	protected Unit(String classType, int level, int hp, int mp, int att, int def) {
		this.classType = classType;
		this.level = level;
		this.hp = hp;
		this.maxHp = hp;
		this.mp = mp;
		this.maxMp = mp;
		this.att = att;
		this.def = def;
	}
}
