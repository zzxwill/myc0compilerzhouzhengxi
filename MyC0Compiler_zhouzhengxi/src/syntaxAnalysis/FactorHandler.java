package syntaxAnalysis;

import errorMg.ErrorFactory;
import errorMg.Errors;
import getNextWord.GetNext;
import getNextWord.WordStructure;
import layerControl.Layer;
import outputPCode.PCodeFactory;
import symbolTable.Symbol;
import symbolTable.SymbolTable;

/**
 * ����
 * 
 * @author ����ϲ
 * 
 */
public class FactorHandler {
	
	public int relativeAddress;
	WordStructure word;
	private int factorKind = 1; // �����ӵ�����
	Symbol st;
	public static String tempName;

	public FactorHandler() {
		super();
	}

	/** �ж��Ƿ������ӣ����򷵻�true�����򷵻�false */

	public boolean payBack() {
		boolean result = false;

		word = GetNext.payBack();
		
		
		/*
		 * 14 int 19 float 27 ��־��
		 */
		if (word.getCode() == 14 || word.getCode() == 19
				|| word.getCode() == 27) { // ��ʶ��,������������
			// �Ǳ�ʶ���������Ƿ�������Ȼ������pcode =����LIT ����ջ��
			if (word.getCode() == 27) { // ��ʶ��

				/*
				 * �ж��Ƿ��Ѿ��������
				 */
				
				
				if (SymbolTable.isDefined(word.getWordName(), Layer
						.getCurProcedureName())) {

					/*
					 * ͨ���������Ͳ�λ�õ��ʵ���Ϣ��
					 */
					st = SymbolTable.getSymbol(word.getWordName(), Layer
							.getCurProcedureName());
					// System.out.println(st);

					factorKind = st.getKind();
					int NO=SymbolTable.getNoByName(word.getWordName(), st.getLayer());
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

						new SymbolTable();

						relativeAddress = SymbolTable.getNoByName(st.getName(),
								st.getLayer());

//						PCodeFactory.output("LOD  " + lays + "  "
//								+ relativeAddress);
//						System.out.println("LOD  " + lays + "  "
//								+ relativeAddress);


					}

					word = GetNext.payBack();
					if (word.getWordName().equals("=")) {

						word = GetNext.payBack();
						if (word.getWordName().equals("a")) {
							st = SymbolTable.getSymbol(word.getWordName(),
									Layer.getCurProcedureName());
							// relativeAddress =
							// SymbolTable.getNoByName(st.getName(),
							// st.getLayer());
							PCodeFactory.output("LIT  " + lays + "  "
									+ st.getValue()); // �˴�����
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
									PCodeFactory.output("LIT  " + lays + "  "
											+ word.getWordName()); // �˴�����
									System.out.println("LIT  " + lays + "  "
											+ word.getWordName());
									
									
									PCodeFactory.output("OPR  " + 0 + "  "
											+ 2); // �˴�����
									System.out.println("OPR  " + 0 + "  "
											+ 2);
									
									PCodeFactory.output("STO  " + lays+ "  "
											+ NO); // �˴�����
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
					new ErrorFactory(error).display();
				}
			}
			if (word.getCode() == 14) {
				factorKind = 14; // ����
				result = true;
				PCodeFactory.output("LIT  0  "
						+ Integer.parseInt(word.getWordName()));
				System.out.println("LIT  0  "
						+ Integer.parseInt(word.getWordName()));
			}
			if (word.getCode() == 19) {
				factorKind = 19; // ������
				result = true;
				PCodeFactory.output("LIT  0  "
						+ Float.parseFloat(word.getWordName()));
				System.out.println("LIT  0  "
						+ Float.parseFloat(word.getWordName()));
				
			}
		} else if (word.getWordName().equals("(")) { // ���ʽ
			// SymFactory.skipBlankSym();
			// sym = SymFactory.getSym();
//			ExpressionHandler eh = new ExpressionHandler();
//			if (eh.payBack()) {
//				factorKind = eh.getValueType();
				word = GetNext.payBack();
				if (word.getWordName().equals(")")) {
					result = true;
				} else {
					result = false;
					// SymFactory.rollBack();
				}
//			}
		} else if (word.getWordName().equals(";")) {
			// SymFactory.rollBack();
			Errors error = Errors.expressionError;
			new ErrorFactory(error).display();
			result = false;
		} else {
			Errors error = Errors.factorError;
			new ErrorFactory(error).display();
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
			new IOHandler().payBack();
			
		}

		// ��������δʵ��
		return result;
	}
}
