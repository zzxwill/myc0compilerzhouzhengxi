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
	 * ����ʷ�������ĵ�����Ϣ
	 * 
	 * static ��ߵĴ���ִֻ��һ�Σ�
	 * ��Ϊ���ļ�ֻ��Ҫ��һ�Ρ�
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
		 * �����readFile()���������ÿͷ����ʱ����Ҫ���¶��ļ��������� �õ������ǵ�һ�е�����
		 */
		// readFile();
 		PCodeStructure pcode = new PCodeStructure();

		try {
			String tempString = null;
	
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����

			if ((tempString = pcode_reader.readLine()) != null) {
				String[] temp = tempString.split("  ",4);
				/*
				 * ��token�е�һ�У�2 main 50 -1���� ID=main code=50 address=-1
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
