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
		this.mp += 10;
		this.att += 2;
		this.def += 2;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	protected int level;

	public int getMaxHp() {
		return maxHp;
	}

	public int getMaxMp() {
		return maxMp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}

	public void equipHp(int power) {
		this.hp += power;
	}

	public void equipAtt(int power) {
		this.att += power;
	}

	public void equipDef(int power) {
		this.def += power;
	}

	public int getHp() {
		return hp;
	}
	
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
	
	public abstract void attack(Unit enenmy);
	
	public String toString() {
		return String.format("[%s] [Lv.%d]\n[HP: %d/%d] [MP: %d/%d] [ATT: %d] [DEF: %d]", classType, level, hp, maxHp,
				mp, maxMp, att, def);
	}

}