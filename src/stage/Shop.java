package stage;

import java.io.IOException;
import java.util.ArrayList;

public class Shop {
	private final int BUY = 1;
	private final int SELL = 2;

	private static Shop instance = new Shop();

	public static Shop getInstance() {
		return instance;
	}

	private textrpg.Player player = textrpg.Player.getInstance();
	protected ArrayList<textrpg.Item> itemList = new ArrayList<>();

	protected void shopRun() {
		setItem();
		printMenu();
	}

	protected void printMenu() {
		textrpg.TextRPG.buffer.setLength(0);
		textrpg.TextRPG.buffer.append("=== 더조은상점 === \n");
		textrpg.TextRPG.buffer.append(String.format("현재 GOLD: %d\n", player.money));
		textrpg.TextRPG.buffer.append("1)구매 2)판매 *)처음으로\n");
		try {
			textrpg.TextRPG.writer.append(textrpg.TextRPG.buffer);
			textrpg.TextRPG.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int sel = textrpg.TextRPG.input("메뉴 선택: ");

		if (sel == BUY) {
			buyMenu();
		} else if (sel == SELL) {
			sellMenu();
		}
	}

	private void buyMenu() {
		int i = 1;
		for (textrpg.Item item : itemList) {
			textrpg.TextRPG.buffer.setLength(0);
			textrpg.TextRPG.buffer.append(i++ + "." + item + "\n");
			try {
				textrpg.TextRPG.writer.append(textrpg.TextRPG.buffer);
				textrpg.TextRPG.writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		buy(textrpg.TextRPG.input("구매할 상품 선택: ") - 1);
	}

	private void buy(int x) {
		if (itemList.get(x).getPrice() > player.money || x < 0 || x > 8) {
			textrpg.TextRPG.buffer.setLength(0);
			textrpg.TextRPG.buffer.append("구매실패\n");
			try {
				textrpg.TextRPG.writer.append(textrpg.TextRPG.buffer);
				textrpg.TextRPG.writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		player.items.add(itemList.get(x));
		player.money -= itemList.get(x).getPrice();
		textrpg.TextRPG.buffer.setLength(0);
		textrpg.TextRPG.buffer.append("구매완료\n");
		try {
			textrpg.TextRPG.writer.append(textrpg.TextRPG.buffer);
			textrpg.TextRPG.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void setItem() {
		itemList.clear();
		itemList.add(new textrpg.Item(textrpg.Item.WEAPON, "나뭇가지", 3, 1000));
		itemList.add(new textrpg.Item(textrpg.Item.WEAPON, "검", 5, 2000));
		itemList.add(new textrpg.Item(textrpg.Item.WEAPON, "마법검", 10, 4000));
		itemList.add(new textrpg.Item(textrpg.Item.ARMOR, "티셔츠", 3, 1000));
		itemList.add(new textrpg.Item(textrpg.Item.ARMOR, "가죽조끼", 5, 2000));
		itemList.add(new textrpg.Item(textrpg.Item.ARMOR, "철갑옷", 10, 4000));
		itemList.add(new textrpg.Item(textrpg.Item.RING, "동반지", 3, 1000));
		itemList.add(new textrpg.Item(textrpg.Item.RING, "은반지", 5, 2000));
		itemList.add(new textrpg.Item(textrpg.Item.RING, "금반지", 10, 4000));
	}

}