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
 * 因子
 * 
 * @author 周正喜
 * 
 */
public class FactorHandler {
	
	public int relativeAddress;
	WordStructure word;
	private int factorKind = 1; // 该因子的类型
	Symbol st;
	public static String tempName;

	public FactorHandler() {
		super();
	}

	/** 判断是否是因子，是则返回true，否则返回false */

	public boolean payBack() {
		boolean result = false;

		word = GetNext.payBack();
		
		
		/*
		 * 14 int 19 float 27 标志符
		 */
		if (word.getCode() == 14 || word.getCode() == 19
				|| word.getCode() == 27) { // 标识符,整数，浮点数
			// 是标识符，则检查是否声明，然后生成pcode =》》LIT 放入栈顶
			if (word.getCode() == 27) { // 标识符

				/*
				 * 判断是否已经定义过。
				 */
				
				
				if (SymbolTable.isDefined(word.getWordName(), Layer
						.getCurProcedureName())) {

					/*
					 * 通过单词名和层次获得单词的信息。
					 */
					st = SymbolTable.getSymbol(word.getWordName(), Layer
							.getCurProcedureName());
					// System.out.println(st);

					factorKind = st.getKind();
					int NO=SymbolTable.getNoByName(word.getWordName(), st.getLayer());
					/*
					 * 如果值为空，则没
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
									+ st.getValue()); // 此处待议
							System.out.println("LIT  " + lays + "  "
									+ st.getValue());
							/*
							 * 获得常量的值
							 */
							int value = word.getAddress();

							word = GetNext.payBack();
							if (word.getWordName().equals("+")) {
								word = GetNext.payBack();
								if (word.getCode() == 28) {
									PCodeFactory.output("LIT  " + lays + "  "
											+ word.getWordName()); // 此处待议
									System.out.println("LIT  " + lays + "  "
											+ word.getWordName());
									
									
									PCodeFactory.output("OPR  " + 0 + "  "
											+ 2); // 此处待议
									System.out.println("OPR  " + 0 + "  "
											+ 2);
									
									PCodeFactory.output("STO  " + lays+ "  "
											+ NO); // 此处待议
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
				factorKind = 14; // 整数
				result = true;
				PCodeFactory.output("LIT  0  "
						+ Integer.parseInt(word.getWordName()));
				System.out.println("LIT  0  "
						+ Integer.parseInt(word.getWordName()));
			}
			if (word.getCode() == 19) {
				factorKind = 19; // 浮点数
				result = true;
				PCodeFactory.output("LIT  0  "
						+ Float.parseFloat(word.getWordName()));
				System.out.println("LIT  0  "
						+ Float.parseFloat(word.getWordName()));
				
			}
		} else if (word.getWordName().equals("(")) { // 表达式
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
		 * 保存现在获取的单词，
		 * 以便其它类如
		 * IOHandler调用
		 */
		tempName=word.getWordName();
		
		if(word.getWordName().equals("printf")||word.getWordName().equals("scanf")){
			new IOHandler().payBack();
			
		}

		// 报错功能尚未实现
		return result;
	}
}
