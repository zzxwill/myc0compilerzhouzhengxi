package syntaxAnalysis;

import errorDeal.ErrorOper;
import errorDeal.Errors;
import getNextWord.GetNext;
import getNextWord.WordStructure;
import layerControl.Layer;
import outputPCode.PCodePut;
import symbolTable.Symbol;
import symbolTable.SymbolOper;

/**
 * ����
 * 
 * @author ����ϲ
 * 
 */
public class Factor {
	
	public int relativeAddress;
	WordStructure word;
	private int factorKind = 1; // �����ӵ�����
	Symbol st;
	public static String tempName;

	public Factor() {
		super();
	}


	public boolean payBack() {
		boolean result = false;

		word = GetNext.payBack();
		String str="a";
		
		/*
		 * 14 int 19 float 27 ��־��
		 */
		if (word.getCode() == 14 || word.getCode() == 19
				|| word.getCode() == 27) { 
			if (word.getCode() == 27) {

				/*
				 * �ж��Ƿ��Ѿ��������
				 */
				
				
				if (SymbolOper.isDefined(word.getWordName(), Layer
						.getCurProcedureName())) {

					/*
					 * ͨ���������Ͳ�λ�õ��ʵ���Ϣ��
					 */
					st = SymbolOper.getSymbol(word.getWordName(), Layer
							.getCurProcedureName());
					// System.out.println(st);

					factorKind = st.getKind();
					int NO=SymbolOper.getNoByName(word.getWordName(), st.getLayer());
					/*
					 * ���ֵΪ�գ���û
					 */
					// if(!st.getValue().equals("")){
					// int layOfTheSymbol = st.getLevel();
					// int lays = Layer.getCurLayer() - layOfTheSymbol;
					int lays = 1;
					if (st.getBelong().equals("global")) {
						lays = 0;
					}

					int addr = st.getAddr();

					if (factorKind == 14 || factorKind == 19 || factorKind == 6||factorKind==27) {
						factorKind = st.getKind();
						result = true;

						new SymbolOper();

						relativeAddress = SymbolOper.getNoByName(st.getName(),
								st.getLayer());

//						PCodePut.output("LOD  " + lays + "  "
//								+ relativeAddress);
//						System.out.println("LOD  " + lays + "  "
//								+ relativeAddress);


					}

					word = GetNext.payBack();
					if (word.getWordName().equals("=")) {

						word = GetNext.payBack();
						if (word.getWordName().equals(str)) {
							st = SymbolOper.getSymbol(word.getWordName(),
									Layer.getCurProcedureName());
							// relativeAddress =
							// SymbolOper.getNoByName(st.getName(),
							// st.getLayer());
							PCodePut.output("LIT  " + lays + "  "
									+ st.getValue()); 
							System.out.println("LIT  " + lays + "  "
									+ st.getValue());
							/*
							 * ��ó�����ֵ
							 */
							int value = word.getAddress();

							word = GetNext.payBack();
							if (word.getWordName().equals("+")) {
								word = GetNext.payBack();
								if (word.getCode() == 28) {
									PCodePut.output("LIT  " + lays + "  "
											+ word.getWordName()); 
									System.out.println("LIT  " + lays + "  "
											+ word.getWordName());
									
									
									PCodePut.output("OPR  " + 0 + "  "
											+ 2); 
									System.out.println("OPR  " + 0 + "  "
											+ 2);
									
									PCodePut.output("STO  " + lays+ "  "
											+ NO); 
									System.out.println("STO  " + lays+ "  "
											+ NO);
									
									word = GetNext.payBack();
									if (word.getWordName().equals(";")) {
										;
									}

								}

							}

						}
					}
				} else {
					Errors error = Errors.variableUndefined;
					new ErrorOper(error).display();
				}
			}
			if (word.getCode() == 14) {
				factorKind = 14; // ����
				result = true;
				PCodePut.output("LIT  0  "
						+ Integer.parseInt(word.getWordName()));
				System.out.println("LIT  0  "
						+ Integer.parseInt(word.getWordName()));
			}
			if (word.getCode() == 19) {
				factorKind = 19; // ������
				result = true;
				PCodePut.output("LIT  0  "
						+ Float.parseFloat(word.getWordName()));
				System.out.println("LIT  0  "
						+ Float.parseFloat(word.getWordName()));
				
			}
		} else if (word.getWordName().equals("(")) { // ���ʽ
			
				word = GetNext.payBack();
				if (word.getWordName().equals(")")) {
					result = true;
				} else {
					result = false;
				}
		} else if (word.getWordName().equals(";")) {
			Errors error = Errors.expressionError;
			new ErrorOper(error).display();
			result = false;
		} else {
			Errors error = Errors.factorError;
			new ErrorOper(error).display();
		}
		
		
		/*
		 * 
		 */
		word=GetNext.payBack();
		/*
		 * �������ڻ�ȡ�ĵ��ʣ�
		 * �Ա���������
		 * IOHandler����
		 */
		tempName=word.getWordName();
		
		if(word.getWordName().equals("printf")||word.getWordName().equals("scanf")){
			new IO().payBack();
			
		}

		return result;
	}
}
