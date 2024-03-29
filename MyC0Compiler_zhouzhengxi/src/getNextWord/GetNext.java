package getNextWord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GetNext {
	public static BufferedReader tokenReader;
	public static String wordName = "";
	static int code = 0;
	static int address;

	public GetNext() {
		
	}

	/*
	 * 读入词法分析后的单词信息
	 * 
	 * static 里边的代码只执行一次！
	 * 因为读文件只需要读一次。
	 */
	static {
		try {
			File file = new File("Token.txt");
			tokenReader = new BufferedReader(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WordStructure payBack() {
		/*
		 * 如果将readFile()放在这里，则每头调用时，都要重新读文件，这样， 得到的总是第一行的数据
		 */
		// readFile();
		WordStructure wordStructure = new WordStructure();

		try {
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束

			if ((tempString = tokenReader.readLine()) != null) {
				String[] temp = tempString.split(" ");
				/*
				 * 如token中的一行：2 main 50 -1，则： ID=main code=50 address=-1
				 */
				wordName = temp[1];
				code = Integer.parseInt(temp[2]);
				address = Integer.parseInt(temp[3]);
				wordStructure = new WordStructure(wordName, code, address);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return wordStructure;
	}

}
