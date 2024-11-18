package textrpg;

public class Item {
	public final static int WEAPON = 1;
	public final static int ARMOR = 2;
	public final static int RING = 3;
	
	private int kind;
	private String name;
	private int power;
	private int price;
	
	public Item(int kind, String name, int power, int price) {
		this.kind = kind;
		this.name = name;
		this.power = power;
		this.price = price;
	}

	public int getKind() {
		return kind;
	}
	
	public String getName() {
		return name;
	}

	public int getPower() {
		return power;
	}

	public int getPrice() {
		return price;
	}

	public String toString() {
		if (kind == WEAPON) {
			return String.format("무기) %s / 능력치 %d / 가격: %d", name, power, price);
		} else if (kind == ARMOR) {
			return String.format("방어구) %s / 능력치 %d / 가격: %d", name, power, price);
		} else if (kind == RING) {
			return String.format("장신구) %s / 능력치 %d / 가격: %d", name, power, price);
		}
		return null;
	}
	
}
