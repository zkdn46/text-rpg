package units;

public abstract class Unit {
	protected String classType;
	protected int hp;
	protected int maxHp;
	protected int mp;
	protected int maxMp;
	protected int att;
	protected int def;

	public void levelup() {
		this.level++;
		this.hp += 20;
		this.mp += 5;
		this.att += 2;
		this.def += 2;
	}

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

	abstract boolean attack(Unit enenmy);

	abstract boolean skill(Unit enenmy);
}
