package getNextPCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GetNext {
	public static BufferedReader pcode_reader;
	public static int num=0;
	public static String f = "";
	public static int l = 0;
	public static String a="";

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
			File file = new File("pcode.txt");
			pcode_reader = new BufferedReader(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static PCodeStructure payBack() {
		/*
		 * 如果将readFile()放在这里，则每头调用时，都要重新读文件，这样， 得到的总是第一行的数据
		 */
		// readFile();
 		PCodeStructure pcode = new PCodeStructure();

		try {
			String tempString = null;
	
			// 一次读入一行，直到读入null为文件结束

			if ((tempString = pcode_reader.readLine()) != null) {
				String[] temp = tempString.split("  ",4);
				/*
				 * 如token中的一行：2 main 50 -1，则： ID=main code=50 address=-1
				 */
				num=Integer.parseInt(temp[0]);
				f=temp[1];
				l = Integer.parseInt(temp[2]);
				a = temp[3];
				
				pcode = new PCodeStructure(num,f,l,a);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return pcode;
	}

}
