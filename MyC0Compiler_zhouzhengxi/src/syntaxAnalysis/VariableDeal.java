package syntaxAnalysis;

import errorDeal.*;
import getNextWord.GetNext;
import getNextWord.WordStructure;
import symbolTable.*;
import layerControl.*;
import outputPCode.PCodePut;

public class VariableDeal {
	WordStructure word;
	public static String tempName;

	/**
	 * 变量定义
	 */
	public VariableDeal() {
	}

	public boolean payBack() {

		String name;
		int kind = 0;
		String value;
		String belong;
		int addr;
		String layer="0";

		boolean result = true;

		// word=GetNext.payBack();
		if (Const.tempName.equals("int")
				|| Const.tempName.equals("float")
				|| Const.tempName.equals("char")) {
			/*
			 * if(sym.getValue().equals("int")) kind = 1;
			 * if(sym.getValue().equals("float")) kind = 2;
			 * if(sym.getValue().equals("char")) kind = 3;
			 */

			/*
			 * 
			 */
			value = "declareVariable";

			belong = "global";

			int flag = 0;

			do {
				// addr = SymbolOper.getAddr(belong);
				// addr=word.getAddress();

				flag++;
				word = GetNext.payBack();
				if (word.getCode() == 27) {
					name = word.getWordName();
					kind = word.getCode();
					// addr=Const.tempAddress;
					addr = word.getAddress();
					Symbol symbol = new Symbol(name, kind,value,addr,layer, belong);
					System.out.println("Symbol(name, kind,value,addr,layer, belong)"+ "	" + name + "	" + kind + "	" + value + "	"
							+ addr + "	" +layer+ "	" +belong );
					
					if (!SymbolOper.add(symbol)) {
						Errors error = Errors.variableRedefine;
						new ErrorOper(error).display();
						result = false;
						
						
						
						break;
					} else {
						
						int m =SymbolOper.getNoByName(word.getWordName(), layer);
						PCodePut.output("LOD  " + layer + "  " + (SymbolOper.getNoByName(word.getWordName(), layer)));
						System.out.println("LOD  " + layer + "  " + (SymbolOper.getNoByName(word.getWordName(), layer)));
						
						
						
//						word = GetNext.payBack();

						if (word.getWordName().equals("(") && flag == 1) { // 此时说明是有返回值的函数

							// SymFactory.rollBack();

							// 该函数名字已经加入到符号表中，此处需将其类型改为 1，以及设置返回值的类型
							/*
							 * 1代表有返回值的函数
							 */
							kind = 1;
	//						Layer.setCurProcedureName(name);
							SymbolOper.delete(symbol);
							symbol = new Symbol(name, kind, value, belong, addr);
							System.out.println("Symbol(name, kind,value,addr,layer, belong)"+ "	" + name + "	" + kind + "	" + value + "	"
									+ addr + "	" +layer+ "	" +belong );
							// symbol.setParam();
							
							
							symbol.setAddr(PCodePut.getCommandNum() + 1);
							SymbolOper.add(symbol);


							result = false;
							return false; 

						}
					}
				} else {
					Errors error = Errors.variableDefineError;
					new ErrorOper(error).display();
					result = false;
					break;
				}
				word = GetNext.payBack();
				tempName = word.getWordName();
			} while (tempName.equals(","));

		} else {
			result = false;
		}

		return result;
	}

}
