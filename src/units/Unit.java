package units;

public abstract class Unit {
	protected String name;
	protected int hp;
	protected int maxHp;
	protected int mp;
	protected int att;
	protected int def;
	protected int level;
	
	protected Unit(){
		
	}

	protected Unit(String name, int level, int hp, int mp, int att, int def) {
		this.name = name;
		this.level = level;
		this.hp = hp;
		this.maxHp = hp;
		this.mp = mp;
		this.att = att;
		this.def = def;
	}
}
