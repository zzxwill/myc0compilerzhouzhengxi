package syntaxAnalysis;

import errorDeal.*;
import getNextWord.GetNext;
import getNextWord.WordStructure;
import symbolTable.*;
import layerControl.*;
import outputPCode.PCodeFactory;

public class VariableDefine {
	WordStructure word;
	public static String tempName;

	/**
	 * 变量定义
	 */
	public VariableDefine() {
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
		if (ConstHandler.tempName.equals("int")
				|| ConstHandler.tempName.equals("float")
				|| ConstHandler.tempName.equals("char")) {
			/*
			 * if(sym.getValue().equals("int")) kind = 1;
			 * if(sym.getValue().equals("float")) kind = 2;
			 * if(sym.getValue().equals("char")) kind = 3;
			 */

			/*
			 * 函数的 值是？ 用code代替
			 */
			value = "declareVariable";

			belong = "global";

			int flag = 0;

			do {
				// addr = SymbolTable.getAddr(belong);
				// addr=word.getAddress();

				flag++;
				word = GetNext.payBack();
				if (word.getCode() == 27) {
					name = word.getWordName();
					kind = word.getCode();
					// addr=ConstHandler.tempAddress;
					addr = word.getAddress();
					Symbol symbol = new Symbol(name, kind,value,addr,layer, belong);
					System.out.println("Symbol(name, kind,value,addr,layer, belong)"+ "	" + name + "	" + kind + "	" + value + "	"
							+ addr + "	" +layer+ "	" +belong );
					
					if (!SymbolTable.add(symbol)) {
						Errors error = Errors.variableRedefine;
						new ErrorFactory(error).display();
						result = false;
						
						
						
						break;
					} else {
						
						int m =SymbolTable.getNoByName(word.getWordName(), layer);
						PCodeFactory.output("LOD  " + layer + "  " + (SymbolTable.getNoByName(word.getWordName(), layer)));
						System.out.println("LOD  " + layer + "  " + (SymbolTable.getNoByName(word.getWordName(), layer)));
						
						
						
//						word = GetNext.payBack();

						if (word.getWordName().equals("(") && flag == 1) { // 此时说明是有返回值的函数

							// SymFactory.rollBack();
	//						Layer.setCurProcedureName(name);

							// 该函数名字已经加入到符号表中，此处需将其类型改为 1，以及设置返回值的类型
							/*
							 * 1代表有返回值的函数
							 */
							kind = 1;
	//						Layer.setCurProcedureName(name);
							SymbolTable.delete(symbol);
							symbol = new Symbol(name, kind, value, belong, addr);
							System.out.println("Symbol(name, kind,value,addr,layer, belong)"+ "	" + name + "	" + kind + "	" + value + "	"
									+ addr + "	" +layer+ "	" +belong );
							// symbol.setParam();
							
							
							symbol.setAddr(PCodeFactory.getCommandNum() + 1);
							SymbolTable.add(symbol);


							result = false;
							return false; // 此时虽然返回的是false ，但处理了有返回值的函数是正确的

						}
					}
				} else {
					Errors error = Errors.variableDefineError;
					new ErrorFactory(error).display();
					result = false;
					break;
				}
				word = GetNext.payBack();
				tempName = word.getWordName();
			} while (tempName.equals(","));
			// SymFactory.rollBack();

		} else {
			// SymFactory.rollBack();
			result = false;
		}

		return result;
	}

}
