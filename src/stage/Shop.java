package stage;

import java.io.IOException;
import java.util.ArrayList;

public class Shop {
	private final int BUY =1;
	private final int SELL =2;
	
	private static Shop instance = new Shop();

	public static Shop getInstance() {
		return instance;
	}
	
	private textrpg.Player player = textrpg.Player.getInstance();
	protected ArrayList<textrpg.Item> itemList = new ArrayList<>();

	public void shopRun() {
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
	
	
	
}
