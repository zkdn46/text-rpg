package stage;

import java.io.IOException;

public class Guild {
	private static Guild instance = new Guild();

	public static Guild getInstance() {
		return instance;
	}

	public void guildRun() {
		printMenu();
		
	}
	
	protected void printMenu() {
		textrpg.TextRPG.buffer.setLength(0);
		textrpg.TextRPG.buffer.append("=== 더조은길드 === \n");
		textrpg.TextRPG.buffer.append("1)길드원목록 2)길드원모집 3)길드원추방 4)파티교체 *)처음으로\n");
		try {
			textrpg.TextRPG.writer.append(textrpg.TextRPG.buffer);
			textrpg.TextRPG.writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int sel = textrpg.TextRPG.input("메뉴 선택: ");


	}
}
