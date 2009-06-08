package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.io.*;
import javax.swing.JSplitPane;
import compiler.structure.KeyWord;
import compiler.structure.equ;
import compiler.structure.Stack;
import compiler.structure.symble;
import compiler.structure.token;
import compiler.structure.var;


public class AnalyseFrame extends JFrame implements DocumentListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// �ļ���
	String filename;

	// �ʷ�������������
	char ch;
	/*
	 * ��ʱ��ȡ���ַ�����
	 */
	int tempchar;
	/*
	 * �Ѿ������˷��ű�ķ��Ÿ���
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
	 * ��ʱ
	 */
	token CurrentToken = new token();
	
	symble Currentsymble = new symble();

	symble[] SymbleList = new symble[100];
	symble[] SymbleList_Pro = new symble[100];
	token[] tokenList = new token[1024];

	InputStreamReader reader;

	// �﷨������������
	int token_pos;
	int code;
	int address;
	int LineOfEqu;
	int total_var;
	/*
	 * ��ջ�ε�ʵ��
	 */
	Stack[] stack_expr = new Stack[100];
	/*
	 * ��Ԫʽ�ṹ
	 */
	equ[] Equ = new equ[1024];
	/*
	 * ������
	 */
	var[] VarList = new var[100];
	String ID;
	/*
	 * pos�ǽ�ջ�ķ��Ÿ�����
	 * ����ţ��п�����
	 * Ҳ����Push(code,address)ִ�еĴ�����
	 * 
	 */
	int pos;
	int now_addr;
	int len_count;
	boolean E_Contrl;
	boolean printf_num = false;
	int E_rtn;
	int temp_count;
	int let_count;
	int gen_pos;
	BufferedReader token_reader;

	

	
//	private File sourFile = null;// source file you want to compile
	private File sourFile = new File("sourceCode.txt");// source file you want to compile
	

	private Document indocument;
	private boolean edited = false;// judge whether the file is edited or not

	public static void main(String[] args) {
		new AnalyseFrame();
		new AnalyseFrame().analyseFile();

	}

	/**
	 * This is the default constructor
	 */
	public AnalyseFrame() {
		super();
//		initialize();
	}



	/**
	 * �����ļ�
	 */

	private void analyseFile() {
		filename = sourFile.getName().substring(0,
				sourFile.getName().indexOf("."));
		System.out.print(filename);
		/*
		 * �ʷ�
		 */
		word_analysis();
		/*
		 * �﷨������
		 */
//		parser();

		

	}

	

	/* �ʷ��������� */
	public void word_analysis() {
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
		 * To initial the example of class Symble,KeyWord,Token.
		 */
		for (i = 0; i < 100; i++) {
			/*
			 * ����Ҫ�����д�뵽keyword.txt��token.txt,symble.txt�У�
			 * ���ƣ�keyword.txt,symble.txt���Ϊ 100�� token.txtΪ1024�
			 * ���Ƕ���ͨ��SymbleList[i]��SymbleList_Pro[i]��key[i]��ȡ��Ȼ�󣬴��뵽
			 * ��Щ�ĵ��еġ���ˣ�ѡ��ʼ��
			 */
			SymbleList[i] = new symble();
			SymbleList_Pro[i] = new symble();
			key[i] = new KeyWord();
		}
		for (i = 0; i < 1024; i++)
			tokenList[i] = new token();
		Scanner();
	}

	// ������
	public void Scanner() {
		/*
		 * The total number of error
		 */
		error_count = 0;

		// ��������,key[i]�д�����ǵ��ʵ����ƺͱ���
		/*
		 * It seems that it is useless. Not correctly. It has nothing to do with
		 * word analysis. But it has to other function.
		 */
		ScannerInit();

		System.out.println("�ʷ���������ʼ\n����token������:\n");// �ʷ�����
	
		/*
		 * source file to be compiled.
		 */
		File file = sourFile;

		try {
			// һ�ζ�һ���ַ�
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
					 * A-Z��a-z��_
					 */
					if (((ch > 64) && (ch < 91)) || ((ch > 96) && (ch < 123))

					|| ch == '_')
						/*
						 * ʶ�����ֺͱ�ʶ��
						 */
						IsAlpha();
					else {
						if (ch == '/')
							/*
							 * ������ź�ע��
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
		

		// �ʷ�������� �жϸ������ʵ�����
	}

	// ��������
	/*
	 * Read the Keyword.txt to key[i]
	 */
	public void ScannerInit() {
		int i = 0;
		File file = new File("keyword-pre.txt");
		BufferedReader reader1 = null;
		try {
			reader1 = new BufferedReader(new FileReader(file));
			String tempString = null;
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
			while ((tempString = reader1.readLine()) != null) {
				i++;
				String[] temp = tempString.split(" ");
				/*
				 * ���뵥�ʵ����ֺͱ���
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

	// ���ִ���
	public void IsNumber() {
		boolean flag = false;
		char ch1;
		CurrentToken.setname("");
		while ((ch > 47) && (ch < 58))
		/*
		 * 48��57��Ӧ��ASCII��ֱ���1��9
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
		 * ������
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
				 * ʵ����
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

	// ��ĸ����
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

		// �ж��Ƿ�Ϊ������
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
			 * ��־��
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

	// ע�ʹ���
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
				 * Ϊ��/��
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

	// �ַ�������
	private void IsChar() {
		CurrentToken.setname("");
		try {
			/*
			 * �ַ�����
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

	// ��������Ĵ���
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
			case '.':
				CurrentToken.setcode(38);
				CurrentToken.setaddr(-1);
				CurrentToken.setlabel(label_count++);
				CurrentToken.setname(".");
				OutPut();
				tempchar = reader.read();
				break;
			case ':':
				ch1 = ch;
				tempchar = reader.read();
				ch = (char) tempchar;
				if (ch != '=') {
					CurrentToken.setcode(40);
					CurrentToken.setaddr(-1);
					CurrentToken.setlabel(label_count++);
					CurrentToken.setname(":");
					OutPut();
				} else {
					CurrentToken.setcode(41);
					CurrentToken.setaddr(-1);
					CurrentToken.setlabel(label_count++);
					CurrentToken.setname(":=");
					OutPut();
					tempchar = reader.read();
				}
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

	// ���ģ��
	public void OutPut() {
		boolean flag = false;
		if (!Is_Program) {
			/*
			 * ��ʶ�� 27 ������ 28 ʵ���� 29 �ַ����� 30
			 */
			if ((CurrentToken.getcode() == 27)
					|| (CurrentToken.getcode() == 30)
					|| (CurrentToken.getcode() == 28)
					|| (CurrentToken.getcode() == 29)) {
				/*
				 * Token private int label=0; private String name=""; private
				 * int code=0; private int addr=0;
				 * 
				 * Symble int number; // ��� int type; // ���� String name; // ����
				 */
				Currentsymble.setnumber(CurrentToken.getaddr());
				Currentsymble.settype(CurrentToken.getcode());
				Currentsymble.setname(CurrentToken.getname());
				flag = WordHave();
				if (((CurrentToken.getcode() == 27) && (flag == true))
						|| (CurrentToken.getcode() == 30)
						|| (CurrentToken.getcode() == 28)
						|| (CurrentToken.getcode() == 29))
					append("symble.txt", Currentsymble.getnumber(),
							Currentsymble.gettype(), Currentsymble.getname());
			}
		} else {
			if ((CurrentToken.getcode() == 27)
					|| (CurrentToken.getcode() == 30)
					|| (CurrentToken.getcode() == 28)
					|| (CurrentToken.getcode() == 29)) {
				Currentsymble.setnumber(CurrentToken.getaddr());
				Currentsymble.settype(CurrentToken.getcode());
				Currentsymble.setname(CurrentToken.getname());
				WordHave_pro();
				/*
				 * ��
				 */
				if ((CurrentToken.getcode() == 30)
						|| (CurrentToken.getcode() == 28)
						|| (CurrentToken.getcode() == 29))
					append("symble.txt", Currentsymble.getnumber(),
							Currentsymble.gettype(), Currentsymble.getname());
			}
		}
		append("token.txt", CurrentToken.getlabel(), CurrentToken.getcode(),
				CurrentToken.getaddr(), CurrentToken.getname());
	
		tokenList[token_num++].setcode(CurrentToken.getcode());
		System.out.print("out put " + tokenList[token_num - 1].getcode() + " "
				+ token_num + "\n");

	}

	// ��ӡ����aΪ�������͵ı���
	public void Error(int a) {
		error_count++;
		switch (a) {
		case 1:
			System.out.println("error" + error_count + ":�Ƿ��ַ������ڵ�" + LineOfPro
					+ "��!\n");
			break;
		case 2:
			System.out.println("error" + error_count + ":ʵ������������ڵ�" + LineOfPro
					+ "��!\n");
			break;
		case 3:
			System.out.println("error" + error_count + ":û��ƥ���ע�ͷ���");
			break;
		case 4:
			System.out.println("error" + "��ʽ����,��һ����main�����﷨����\n");
			break;
		case 5:
			System.out.println("error" + "��ʽ����,��һ���ٳ�����\n");
			break;
		case 6:
			System.out.println("error" + "��" + LineOfPro + "������������\n");
			break;
		case 7:
			System.out.println("error" + "��" + LineOfPro + "��ֵ������\n");
			break;
		case 8:
			System.out.println("error" + "��" + LineOfPro + "����ȱ��'}'��ƥ��\n");
			break;
		case 9:
			System.out.println("error" + "��" + LineOfPro + "��������\n");
			break;
		case 10:
			System.out.println("error" + "��" + LineOfPro + "�����ʽ������\n");
			break;
		case 11:
			System.out.println("error" + "��" + LineOfPro + "����if������\n");
			break;
		case 12:
			System.out.println("error" + "��" + LineOfPro + "��,while������\n");
			break;
		case 13:
			System.out.println("error" + "��" + LineOfPro + "��,for������\n");
			break;
		case 14:
			System.out.println("error" + "main����ȱ��')'����\n");
			break;
		case 15:
			System.out.println("error" + "��" + LineOfPro + "���������ʽ������\n");
			break;
		case 16:
			System.out.println("error" + "��" + LineOfPro + "���������ʽ���ȱ��')'����\n");
			break;
		case 17:
			System.out.println("error" + "main����ȱ��'('����\n");
			break;
		case 18:
			System.out.println("error" + "main����ȱ��'{'����\n");
			break;
		case 19:
			System.out.println("error" + "main����ȱ��'}'����\n");
			break;
		case 20:
			System.out.println("error" + "ȱ��main��������\n");
			break;
		case 52:
			System.out.println("error" + "�������ȱ��.��\n");
			break;
		case 53:
			System.out.println("error" + "δд�κ����\n");
			break;
		case 54:
			System.out.println("error" + "��" + LineOfPro + "�����ȱ��': '��\n");
			break;
		default:
			break;
		}
	}

	// �жϱ�ʶ���Ƿ����
	public boolean WordHave_pro() {
		int i;
		for (i = 0; i < pro_var; i++) {
			if (Currentsymble.getname().equals(SymbleList_Pro[i].getname())
					&& Currentsymble.gettype() == 27) {
				CurrentToken.setaddr(SymbleList_Pro[i].getnumber());
				return false;
			}
		}
		SymbleList_Pro[pro_var].setname(CurrentToken.getname());
		SymbleList_Pro[pro_var].settype(CurrentToken.getcode());
		SymbleList_Pro[pro_var].setnumber(CurrentToken.getaddr());
		pro_var++;
		return true;
	}

	// �жϵ����Ƿ����

	public boolean WordHave() {
		int i;
		/*
		 * var_count is the variable which has been added into symble.txt
		 */
		for (i = 0; i < var_count; i++) {
			if (Currentsymble.getname().equals(SymbleList[i].getname())
					&& Currentsymble.gettype() == 27) {
				/*
				 * ��symble�еķ��ŵ����numble���뵽CurrentToken�С�
				 */
				CurrentToken.setaddr(SymbleList[i].getnumber());
				return false;
			}
		}

		/*
		 * CurrentToken is used as a temporary variable.
		 */
		SymbleList[var_count].setname(CurrentToken.getname());
		SymbleList[var_count].settype(CurrentToken.getcode());
		SymbleList[var_count].setnumber(CurrentToken.getaddr());
		var_count++;
		return true;
	}

	// д���ļ�
	public void append(String fileName, int number, int type, String name) {
		try {
			// ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
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
			// ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
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

	/*
	 * ����Ŀ�����
	 */
	public void gen_target(String fileName, String content) {
		try {
			// ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ���symble,token�ļ�
	public void clearObj() {
		try {
			FileWriter writer = new FileWriter("symble.txt");
			writer.write("");
			FileWriter writer1 = new FileWriter("token.txt");
			writer1.write("");
			FileWriter writer2 = new FileWriter("equ.txt");
			writer1.write("");
			writer.close();
			writer1.close();
			writer2.close();
		} catch (IOException ioe) {
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}


}