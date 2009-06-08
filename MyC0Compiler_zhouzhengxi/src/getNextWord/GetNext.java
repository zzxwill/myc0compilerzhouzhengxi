package getNextWord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GetNext {
	public static BufferedReader token_reader;
	public static String wordName = "";
	static int code = 0;
	static int address;

	public GetNext() {
		
	}

	/*
	 * ����ʷ�������ĵ�����Ϣ
	 * 
	 * static ��ߵĴ���ִֻ��һ�Σ�
	 * ��Ϊ���ļ�ֻ��Ҫ��һ�Ρ�
	 */
	static {
		try {
			File file = new File("token.txt");
			token_reader = new BufferedReader(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WordStructure payBack() {
		/*
		 * �����readFile()���������ÿͷ����ʱ����Ҫ���¶��ļ��������� �õ������ǵ�һ�е�����
		 */
		// readFile();
		WordStructure wordStructure = new WordStructure();

		try {
			String tempString = null;
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����

			if ((tempString = token_reader.readLine()) != null) {
				String[] temp = tempString.split(" ");
				/*
				 * ��token�е�һ�У�2 main 50 -1���� ID=main code=50 address=-1
				 */
				code = Integer.parseInt(temp[2]);
				wordName = temp[1];
				address = Integer.parseInt(temp[3]);
				wordStructure = new WordStructure(wordName, code, address);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return wordStructure;
	}

}
