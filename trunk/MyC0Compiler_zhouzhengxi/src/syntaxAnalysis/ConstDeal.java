package syntaxAnalysis;

import outputPCode.PCodePut;
import errorDeal.*;
import getNextWord.GetNext;
import getNextWord.WordStructure;

import symbolTable.*;
import layerControl.*;
/*
 * ���������ӳ���--���崦��
 */
public class ConstDeal {
	public static String tempName;

	public ConstDeal() {
		super();
	}

	public boolean payBack() {
		boolean result = false;
		WordStructure word;
//String name,int kind,int value,int addr,String layer,String belong
		
		String name;
		int kind;
		String value;
		String belong;
		String layer="0";
		int addr;
		
		/*
		 * ����������ڽ�����'���ַ��͵�Const�н����Ƿ���
		 * ���ļ��顣
		 * 
		 */
		

		word = GetNext.payBack();
		if (word.getWordName().equals("int")) {

			do {
				word = GetNext.payBack();
				if (word.getCode() == 27) { 
					/*
					 * ��־��������
					 */
					name = word.getWordName();

					word = GetNext.payBack();
					if (word.getWordName().equals("=")) {
						
						word = GetNext.payBack();
						addr = word.getAddress();
						kind=word.getCode();
						if (word.getCode() == 28) {
							// ���뵽���ű���
							/*
							 */
//							kind = 1;
							value = word.getWordName();
							belong = Layer.getCurProcedureName();
//							addr = word.getAddress();
							/*
							 */
							Symbol symbol = new Symbol(name, kind, value,addr,layer,belong);
							System.out.println("Symbol(name, kind,value,addr,layer, belong)"+ "	" + name + "	" + kind + "	" + value + "	"
									+ addr + "	" +layer+ "	" +belong );
							
							 /*
							  * �����wordName���Ǳ�����ֵ
							  * const int a=3;
							  */
							PCodePut.output("LIT  " + layer + "  " + word.getWordName());
							System.out.println("LIT  " + layer + "  " + word.getWordName());

							if (!SymbolOper.add(symbol)) {
								Errors error = Errors.consReDefine;
								new ErrorFactory(error).display();
								result = false;
								break;
							} else {
								result = true;
							}

						} else {
							Errors error = Errors.lostValue;
							new ErrorFactory(error).display();
							result = false;
							break;
						}
					} else {
						result = false;
						break;

					}

				} else {
					Errors error = Errors.lostSymbol;
					new ErrorFactory(error).display();
					result = false;
					break;
				}
				word = GetNext.payBack();
				tempName=word.getWordName();
			} while (tempName.equals(","));

		}

		/*
		 * ����float
		 * 
		 */
		
		if (word.getWordName().equals("float")) {

			do {
				word = GetNext.payBack();
				if (word.getCode() == 27) { // �ж��Ǳ�ʶ��
					/*
					 * ��־��������
					 */
					name = word.getWordName();

					word = GetNext.payBack();
					if (word.getWordName().equals("=")) {
						
						word = GetNext.payBack();
						addr = word.getAddress();
						kind=word.getCode();
						if (word.getCode() == 29||word.getCode()==28) {
							// ���뵽���ű���
							/*
						
							 */
//							kind = 1;
							value = word.getWordName();
							belong = Layer.getCurProcedureName();
//							addr = word.getAddress();
							Symbol symbol = new Symbol(name, kind, value,belong, addr);
							System.out.println("Symbol(name, kind,value,addr,layer, belong)"+ "	" + name + "	" + kind + "	" + value + "	"
									+ addr + "	" +layer+ "	" +belong );
							
							PCodePut.output("LIT  " + layer + "  " + word.getWordName());
							System.out.println("LIT  " + layer + "  " + word.getWordName());

							if (!SymbolOper.add(symbol)) {
								Errors error = Errors.consReDefine;
								new ErrorFactory(error).display();
								result = false;
								break;
							} else {
								result = true;
							}

						} else {
							Errors error = Errors.lostValue;
							new ErrorFactory(error).display();
							result = false;
							break;
						}
					} else {
						result = false;
						break;

					}

				} else {
					Errors error = Errors.lostSymbol;
					new ErrorFactory(error).display();
					result = false;
					break;
				}
				word = GetNext.payBack();
				tempName=word.getWordName();
			} while (tempName.equals(","));


		}
		/*
		 * ����char
		 */
		
		if (word.getWordName().equals("char")) {

			do {
				word = GetNext.payBack();
				if (word.getCode() == 27) { // �ж��Ǳ�ʶ��
					/*
					 * ��־��������
					 */
					name = word.getWordName();

					word = GetNext.payBack();
					if (word.getWordName().equals("=")) {
						
						word = GetNext.payBack();
						addr = word.getAddress();
						kind=word.getCode();
						if (word.getCode() == 27) {
							// ���뵽���ű���
							/*
							 */
//							kind = 1;
							value = word.getWordName();
							belong = Layer.getCurProcedureName();
//							addr = word.getAddress();
							Symbol symbol = new Symbol(name, kind, value,belong, addr);
							System.out.println("Symbol(name, kind,value,addr,layer, belong)"+ "	" + name + "	" + kind + "	" + value + "	"
									+ addr + "	" +layer+ "	" +belong );
//							symbol.setConst();
							PCodePut.output("LIT  " + layer + "  " + word.getWordName());
							System.out.println("LIT  " + layer + "  " + word.getWordName());

							if (!SymbolOper.add(symbol)) {
								Errors error = Errors.consReDefine;
								new ErrorFactory(error).display();
								result = false;
								break;
							} else {
								result = true;
							}

						} else {
							Errors error = Errors.lostValue;
							new ErrorFactory(error).display();
							result = false;
							break;
						}
					} else {
						result = false;
						break;

					}

				} else {
					Errors error = Errors.lostSymbol;
					new ErrorFactory(error).display();
					result = false;
					break;
				}
				word = GetNext.payBack();
				tempName=word.getWordName();
			} while (tempName.equals(","));


		}
		return result;
	}
	



}
