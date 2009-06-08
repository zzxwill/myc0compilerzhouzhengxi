package wordAnalysis;

import java.io.*;

public class AnalyseFrame {
	private static final long serialVersionUID = 1L;

	// 文件名
	String filename;

	// 词法分析声明变量
	char ch;
	/*
	 * 临时获取的字符变量
	 */
	int tempchar;
	/*
	 * 已经存入了符号表的符号个数
	 */
	int var_count;
	/*
	 * Increase error_count when use Error(int a)
	 */
	int error_count;
	int label_count;
	int code_count;
	int addr_count;
	int LineOfPro;
	int token_num;
	int check_pro = 0;
	int prev_num;
	int pro_var;
	boolean Is_Program = false;
	KeyWord[] key = new KeyWord[100];
	/*
	 * 临时
	 */
	token CurrentToken = new token();

	token[] tokenList = new token[1024];

	InputStreamReader reader;

	BufferedReader token_reader;

	private File sourFile = new File("sourceCode.txt");// source file you want

	public static void main(String[] args) {
		new AnalyseFrame().word_analysis();
	}
	
	/* 词法分析函数 */
	public void word_analysis() {
		new AnalyseFrame().clearObj();
		int i = 0;
		code_count = 0;
		LineOfPro = 0;
		var_count = 0;
		pro_var = 0;
		prev_num = 1002;
		addr_count = 1;
		label_count = 1;
		token_num = 1;
		/*
		 * To initial the example of class KeyWord
		 */
		for (i = 0; i < 100; i++) {
			/*
			 * 最终要将结果写入到keyword.txt，token.txt,
			 * 估计，keyword.txt,最多为 100项 token.txt为1024项。
			 * 它们都是通过key[i]获取，然后，存入到
			 * 这些文档中的。因此，选初始化
			 */
			key[i] = new KeyWord();
		}
		for (i = 0; i < 1024; i++)
			tokenList[i] = new token();
		Scanner();
	}

	// 主程序
	public void Scanner() {
		/*
		 * The total number of error
		 */
		error_count = 0;

		// 读入编码表,key[i]中存入的是单词的名称和编码
		/*
		 * It seems that it is useless. Not correctly. It has nothing to do with
		 * word analysis. But it has to other function.
		 */
		ScannerInit();

		System.out.println("词法分析程序开始\n生成token表如下:\n");// 词法分析

		/*
		 * source file to be compiled.
		 */
		File file = sourFile;

		try {
			// 一次读一个字符
			reader = new InputStreamReader(new FileInputStream(file));
			/*
			 * Read a char from the source file every time.
			 */
			tempchar = reader.read();
			while (tempchar != -1) {

				ch = (char) tempchar;
				/*
				 * 0-9
				 */
				if (ch > 47 && ch < 58)
					IsNumber();
				else {
					/*
					 * A-Z或a-z或_
					 */
					if (((ch > 64) && (ch < 91)) || ((ch > 96) && (ch < 123))

					|| ch == '_')
						/*
						 * 识别保留字和标识符
						 */
						IsAlpha();
					else {
						if (ch == '/')
							/*
							 * 处理除号和注释
							 */
							IsAnotation();
						else if (ch == '"')
							/*
							 * To test whether it is a char or not.
							 */
							IsChar();
						else
							/*
							 * To test other situation
							 */
							IsOther();
					}
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 词法分析完毕 判断各个单词的种类
	}

	// 读入编码表
	/*
	 * Read the Keyword.txt to key[i]
	 */
	public void ScannerInit() {
		int i = 0;
		File file = new File("keyword.txt");
		BufferedReader reader1 = null;
		try {
			reader1 = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader1.readLine()) != null) {
				i++;
				String[] temp = tempString.split(" ");
				/*
				 * 存入单词的名字和编码
				 */
				key[i].setname(temp[0]);
				/*
				 * Change the String read into Integer
				 */
				key[i].setcode(Integer.parseInt(temp[1]));
				System.out.println(temp[0] + " " + temp[1]);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 数字处理
	public void IsNumber() {
		boolean flag = false;
		char ch1;
		CurrentToken.setname("");
		while ((ch > 47) && (ch < 58))
		/*
		 * 48和57对应的ASCII码分别是1和9
		 */
		{
			CurrentToken.setname(CurrentToken.getname() + ch);
			try {
				if (tempchar != -1) {
					tempchar = reader.read();
					ch = (char) tempchar;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (ch == '.') {
				flag = true;
				break;
			}
		}
		/*
		 * 整常数
		 */
		CurrentToken.setcode(28);
		CurrentToken.setaddr(addr_count++);
		CurrentToken.setlabel(label_count++);

		if (flag) {
			try {
				tempchar = reader.read();
				ch1 = (char) tempchar;
				if ((ch1 > 47) && (ch1 < 58))
					CurrentToken.setname(CurrentToken.getname() + ch);
				else
					Error(2);
				ch = ch1;
				while ((ch > 47) && (ch < 58)) {
					CurrentToken.setname(CurrentToken.getname() + ch);
					tempchar = reader.read();
					ch = (char) tempchar;
				}
				/*
				 * 实常数
				 */
				CurrentToken.setcode(29);
				if (ch == '.') {
					Error(2);
					tempchar = reader.read();
					ch = (char) tempchar;
					while ((ch > 47) && (ch < 58)) {
						tempchar = reader.read();
						ch = (char) tempchar;
					}
				}
				if (((ch > 64) && (ch < 90)) || ((ch > 96) && (ch < 123)))
				/*
				 * A--Z
				 */
				{
					Error(2);
					while (((ch > 64) && (ch < 90))
							|| ((ch > 96) && (ch < 123))) {
						tempchar = reader.read();
						ch = (char) tempchar;
						while ((ch > 47) && (ch < 58)) {
							tempchar = reader.read();
							ch = (char) tempchar;
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		OutPut();
	}

	// 字母处理
	private void IsAlpha() {
		int i;
		boolean h = false;
		CurrentToken.setname("");
		while (((ch > 64) && (ch < 90)) || ((ch > 96) && (ch < 123))
				|| ch == '_') {
			/*
			 * A--Z a--z
			 */
			CurrentToken.setname(CurrentToken.getname() + ch);
			try {
				tempchar = reader.read();
				ch = (char) tempchar;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 判断是否为保留字
		for (i = 1; i < 60; i++) {

			if (CurrentToken.getname().equals(key[i].getname())) {
				h = true;
				break;
			}
		}
		if (h) {
			CurrentToken.setcode(key[i].getcode());
			CurrentToken.setaddr(-1);
		} else if (Is_Program) {
			/*
			 * 标志符
			 */
			CurrentToken.setcode(27);
			CurrentToken.setaddr(prev_num);
			prev_num += 2;
		} else {
			CurrentToken.setcode(27);
			CurrentToken.setaddr(addr_count++);
		}
		CurrentToken.setlabel(label_count++);
		OutPut();
	}

	// 注释处理
	private void IsAnotation() {
		CurrentToken.setname("");
		try {

			tempchar = reader.read();
			ch = (char) tempchar;

			if (ch == '*') {
				for (;;) {
					tempchar = reader.read();
					ch = (char) tempchar;
					if (tempchar == -1) {
						Error(3);
						break;
					}
					if (ch == '*') {
						tempchar = reader.read();
						ch = (char) tempchar;
						if (ch == '/') {
							tempchar = reader.read();
							break;
						}
					}
				}

			} else {
				/*
				 * 为“/”
				 */
				CurrentToken.setcode(39);
				CurrentToken.setaddr(-1);
				CurrentToken.setlabel(label_count++);
				CurrentToken.setname("/");
				OutPut();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 字符串处理
	private void IsChar() {
		CurrentToken.setname("");
		try {
			/*
			 * 字符常数
			 */
			CurrentToken.setcode(30);
			for (;;) {
				tempchar = reader.read();
				ch = (char) tempchar;
				if (ch != '"')
					CurrentToken.setname(CurrentToken.getname() + ch);
				else
					break;
			}
			CurrentToken.setaddr(addr_count++);
			CurrentToken.setlabel(label_count++);
			OutPut();
			tempchar = reader.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 其它情况的处理
	public void IsOther() {
		CurrentToken.setname("");
		try {
			char ch1;
			CurrentToken.setname("");
			switch (ch) {
			case '(':
				if (tokenList[token_num - 1].getcode() == 27
						&& (tokenList[token_num - 2].getcode() == 49 || tokenList[token_num - 2]
								.getcode() == 14)) {
					Is_Program = true;
				}
				CurrentToken.setcode(32);
				CurrentToken.setaddr(-1);
				CurrentToken.setlabel(label_count++);
				CurrentToken.setname("(");
				OutPut();
				tempchar = reader.read();
				break;
			case ')':
				CurrentToken.setcode(33);
				CurrentToken.setaddr(-1);
				CurrentToken.setlabel(label_count++);
				CurrentToken.setname(")");
				OutPut();
				tempchar = reader.read();
				break;
			case '{':
				if (Is_Program)
					check_pro++;
				CurrentToken.setcode(51);
				CurrentToken.setaddr(-1);
				CurrentToken.setlabel(label_count++);
				CurrentToken.setname("{");
				OutPut();
				tempchar = reader.read();
				break;
			case '}':
				if (Is_Program) {
					check_pro--;
					if (check_pro == 0) {
						pro_var = 0;
						Is_Program = false;
					}
				}
				CurrentToken.setcode(52);
				CurrentToken.setaddr(-1);
				CurrentToken.setlabel(label_count++);
				CurrentToken.setname("}");
				OutPut();
				tempchar = reader.read();
				break;
			case '*':
				CurrentToken.setcode(34);
				CurrentToken.setaddr(-1);
				CurrentToken.setlabel(label_count++);
				CurrentToken.setname("*");
				OutPut();
				tempchar = reader.read();
				break;
			case '%':
				CurrentToken.setcode(56);
				CurrentToken.setaddr(-1);
				CurrentToken.setlabel(label_count++);
				CurrentToken.setname("%");
				OutPut();
				tempchar = reader.read();
				break;
			case '+':
				CurrentToken.setcode(35);
				CurrentToken.setaddr(-1);
				CurrentToken.setlabel(label_count++);
				CurrentToken.setname("+");
				OutPut();
				tempchar = reader.read();
				break;
			case ',':
				CurrentToken.setcode(37);
				CurrentToken.setaddr(-1);
				CurrentToken.setlabel(label_count++);
				CurrentToken.setname(",");
				OutPut();
				tempchar = reader.read();
				break;
			case '-':
				CurrentToken.setcode(36);
				CurrentToken.setaddr(-1);
				CurrentToken.setlabel(label_count++);
				CurrentToken.setname("-");
				OutPut();
				tempchar = reader.read();
				break;
		
		
			case ';':
				CurrentToken.setcode(42);
				CurrentToken.setaddr(-1);
				CurrentToken.setlabel(label_count++);
				CurrentToken.setname(";");
				OutPut();
				tempchar = reader.read();
				break;
			case '<':
				tempchar = reader.read();
				ch1 = (char) tempchar;
				if (ch1 == '=') {
					CurrentToken.setcode(44);
					CurrentToken.setaddr(-1);
					CurrentToken.setlabel(label_count++);
					CurrentToken.setname("<=");
					OutPut();
					tempchar = reader.read();
				} else {
					if (ch1 == '>') {
						CurrentToken.setcode(45);
						CurrentToken.setaddr(-1);
						CurrentToken.setlabel(label_count++);
						CurrentToken.setname("<>");
						OutPut();
						tempchar = reader.read();
					} else {
						CurrentToken.setcode(43);
						CurrentToken.setaddr(-1);
						CurrentToken.setlabel(label_count++);
						CurrentToken.setname("<");
						OutPut();
					}
				}
				break;
			case '=':
				ch1 = ch;
				tempchar = reader.read();
				ch = (char) tempchar;
				if (ch != '=') {
					CurrentToken.setcode(41);
					CurrentToken.setaddr(-1);
					CurrentToken.setlabel(label_count++);
					CurrentToken.setname("=");
					OutPut();
				} else {
					CurrentToken.setcode(46);
					CurrentToken.setaddr(-1);
					CurrentToken.setlabel(label_count++);
					CurrentToken.setname("==");
					OutPut();
					tempchar = reader.read();
				}
				break;
			case '!':
				ch1 = ch;
				tempchar = reader.read();
				ch = (char) tempchar;
				if (ch == '=') {
					CurrentToken.setcode(45);
					CurrentToken.setaddr(-1);
					CurrentToken.setlabel(label_count++);
					CurrentToken.setname("!=");
					OutPut();
					tempchar = reader.read();
				}
				break;
			case '>':
				tempchar = reader.read();
				ch1 = (char) tempchar;
				if (ch1 == '=') {
					CurrentToken.setcode(48);
					CurrentToken.setaddr(-1);
					CurrentToken.setlabel(label_count++);
					CurrentToken.setname("> =");
					OutPut();
					tempchar = reader.read();
				} else {
					CurrentToken.setcode(47);
					CurrentToken.setaddr(-1);
					CurrentToken.setlabel(label_count++);
					CurrentToken.setname(">");
					OutPut();
				}
				break;
			case 10:
				LineOfPro++;
				tempchar = reader.read();
				break;
			case 13:
				LineOfPro++;
				tempchar = reader.read();
				break;
			case ' ':
				tempchar = reader.read();
				break;
			default:
				Error(1);
				tempchar = reader.read();
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 输出模块
	public void OutPut() {
		if (!Is_Program) {
			/*
			 * 标识符 27 整常数 28 实常数 29 字符常数 30
			 */
			if ((CurrentToken.getcode() == 27)
					|| (CurrentToken.getcode() == 30)
					|| (CurrentToken.getcode() == 28)
					|| (CurrentToken.getcode() == 29)) {
				/*
				 * Token private int label=0; private String name=""; private
				 * int code=0; private int addr=0;
				 * 
				 * Symble int number; // 序号 int type; // 类型 String name; // 名称
				 */

				// flag = WordHave();
			}
		} else {

		}
		append("token.txt", CurrentToken.getlabel(), CurrentToken.getcode(),
				CurrentToken.getaddr(), CurrentToken.getname());

		tokenList[token_num++].setcode(CurrentToken.getcode());

	}

	// 打印错误，a为错误类型的编码
	public void Error(int a) {
		error_count++;
		switch (a) {
		case 1:
			System.out.println("error" + error_count + ":非法字符出现在第" + LineOfPro
					+ "行!\n");
			break;
		case 2:
			System.out.println("error" + error_count + ":实常数错误出现于第" + LineOfPro
					+ "行!\n");
			break;
		case 3:
			System.out.println("error" + error_count + ":没有匹配的注释符号");
			break;
		case 4:
			System.out.println("error" + "格式出错,第一行少main函数语法出错\n");
			break;
		case 5:
			System.out.println("error" + "格式出错,第一行少程序名\n");
			break;
		case 6:
			System.out.println("error" + "第" + LineOfPro + "条声明语句出错\n");
			break;
		case 7:
			System.out.println("error" + "第" + LineOfPro + "赋值语句出错\n");
			break;
		case 8:
			System.out.println("error" + "第" + LineOfPro + "条，缺少'}'不匹配\n");
			break;
		case 9:
			System.out.println("error" + "第" + LineOfPro + "条语句出错\n");
			break;
		case 10:
			System.out.println("error" + "第" + LineOfPro + "条表达式语句出错\n");
			break;
		case 11:
			System.out.println("error" + "第" + LineOfPro + "条，if语句出错\n");
			break;
		case 12:
			System.out.println("error" + "第" + LineOfPro + "条,while语句出错\n");
			break;
		
		case 14:
			System.out.println("error" + "main函数缺少')'出错\n");
			break;
		
		case 17:
			System.out.println("error" + "main函数缺少'('出错\n");
			break;
		case 18:
			System.out.println("error" + "main函数缺少'{'出错\n");
			break;
		case 19:
			System.out.println("error" + "main函数缺少'}'出错\n");
			break;
		case 20:
			System.out.println("error" + "缺少main函数出错\n");
			break;
		
		case 53:
			System.out.println("error" + "未写任何语句\n");
			break;
		case 54:
			System.out.println("error" + "第" + LineOfPro + "条语句缺乏': '号\n");
			break;
		default:
			break;
		}
	}

	// 写入文件
	public void append(String fileName, int number, int type, String name) {
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(number + " ");
			writer.write(type + " ");
			writer.write(name + " \r\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void append(String fileName, int label, int code, int addr,
			String name) {
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(label + " ");
			writer.write(name + " ");
			writer.write(code + " ");
			writer.write(addr + " \r\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 清空token文件
	public void clearObj() {
		try {

			FileWriter writer1 = new FileWriter("token.txt");
			writer1.write("");

			writer1.close();

		} catch (IOException ioe) {
		}
	}

}