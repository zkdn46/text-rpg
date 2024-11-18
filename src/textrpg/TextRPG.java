package textrpg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TextRPG {
	private final int TOWN = 1;
	private final int DUNGEON = 2;
	private final int EXIT = 3;

	public static StringBuffer buffer = new StringBuffer();
	public static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private boolean isRun = true;

	private static TextRPG instance = new TextRPG();

	public static TextRPG getInstance() {
		return instance;
	}

	public void run() {
		while (isRun) {
			printMenu();
			
		}
	}
	
	private void printMenu() {
		buffer.setLength(0);
		buffer.append("=== test RPG ===\n");
		buffer.append("1)마을 2)던전 3)종료\n");
		try {
			writer.append(buffer);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected static int input(String msg) {
		int num = -1;
		try {
			writer.append(msg);
			writer.flush();
			num = Integer.parseInt(reader.readLine());
			return num;
		} catch (Exception e) {
			System.err.println("숫자 입력!");
			return num;
		}
	}

}