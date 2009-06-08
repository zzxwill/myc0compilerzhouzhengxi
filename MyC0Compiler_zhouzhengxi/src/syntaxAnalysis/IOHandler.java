package syntaxAnalysis;

import outputPCode.PCodeFactory;
import symbolTable.Symbol;
import symbolTable.SymbolTable;
import errorDeal.ErrorFactory;
import errorDeal.Errors;
import getNextWord.GetNext;
import getNextWord.WordStructure;

public class IOHandler {
	int relativeAddress;
	Symbol st;

	public IOHandler() {
		super();
	}

	public boolean payBack() {
		WordStructure word;
		boolean result = false;
		String belong="global";
		if (FactorHandler.tempName.equals("printf")) {
			word = GetNext.payBack();
			if (word.getWordName().equals("(")) {

				word = GetNext.payBack();
				// Symbol symbol = SymbolTable.getSymbol(word.getWordName(),
				// Layer
				// .getCurProcedureName());
				// symbol.setValue("#");
				// if (symbol != null) {
				
				// belong = symbol.getBelong();
				int lays = 1;
				// if (belong.equals("global"))
				// lays = 0;

				// int addr = symbol.getAddr();

				PCodeFactory.output("LIT  " + (lays-1) + "  " + word.getWordName());
				System.out.println("LIT  " + (lays-1) + "  " + word.getWordName());
				
				
				word = GetNext.payBack();
				if (word.getWordName().equals(",")) {
					PCodeFactory.output("WRT  " + 0 + "  " + 0);
					System.out.println("WRT  " + 0 + "  " + 0);
					
					word = GetNext.payBack();
					if (word.getCode() == 27) {
//						PCodeFactory.output("WRT  " + 0 + "  " + 0);
//						System.out.println("WRT  " + 0 + "  " + 0);
						st = SymbolTable.getSymbol(word.getWordName(), belong);
						relativeAddress = SymbolTable.getNoByName(st.getName(),
								st.getLayer());
						System.out.println("LOD  " + st.getLayer() +" "
								+ String.valueOf(relativeAddress));
						PCodeFactory.output("LOD  " + st.getLayer() +"  "
								+ relativeAddress); 
//						System.out.println("LOD  " + st.getLayer() +" "
//								+ String.valueOf(relativeAddress));
						PCodeFactory.output("WRT  " + 0 + "  " + 0);
						System.out.println("WRT  " + 0 + "  " + 0);

					}
				}

				word = GetNext.payBack();
				if (word.getWordName().equals(")")) {
					result = true;
					word=GetNext.payBack();
					if(word.getWordName().equals(";")){
						;
					}
					else{
						Errors error = Errors.lostFH_Error;
						new ErrorFactory(error).display();
					}
				} else {
					Errors error = Errors.lostXKH2;
					new ErrorFactory(error).display();
				}
			} else {
				Errors error = Errors.lostXKH1;
				new ErrorFactory(error).display();
			}

		} else if (FactorHandler.tempName.equals("printf")) {
			/***/
			/*
			 * SymFactory.skipBlankSym(); sym = SymFactory.getSym(); if
			 * (sym.getValue().equals("(")) { SymFactory.skipBlankSym(); sym =
			 * SymFactory.getSym(); if (sym.getValue().equals("\"")) { //
			 * SymFactory.rollBack(); CharFactory.rollBack(); StringHandler sh =
			 * new StringHandler(); if (sh.process()) { // 输出字符串 // String s =
			 * sh.getStringAddr(); // int addr = PCodeFactory.getCommandNum();
			 * int addr = sh.getSAddr(); // StringTable.insertString(addr,s);
			 * PCodeFactory.output("LOD  -1  " + addr);
			 * PCodeFactory.output("WRT  0  0"); SymFactory.skipBlankSym(); sym
			 * = SymFactory.getSym(); if (sym.getValue().equals(",")) { // 表达式处理
			 * if (new ExpressionHandler().process()) {
			 * PCodeFactory.output("WRT  0  0"); result = true; } } else if
			 * (sym.getValue().equals(")")) { result = true;
			 * SymFactory.rollBack(); } else { Errors error =
			 * Errors.notIOSentence; new ErrorFactory(sym.getLine(),
			 * sym.getRow(), error) .display(); } }
			 * 
			 * } else { SymFactory.rollBack(); if (new
			 * ExpressionHandler().process()) {
			 * PCodeFactory.output("WRT  0  0"); result = true; } }
			 * SymFactory.skipBlankSym(); sym = SymFactory.getSym(); if
			 * (!sym.getValue().equals(")")) { while
			 * (!sym.getValue().equals(";")) { sym = SymFactory.getSym(); }
			 * result = false; }
			 * 
			 * } else { Errors error = Errors.lostXKH1; new
			 * ErrorFactory(sym.getLine(), sym.getRow(), error).display(); }
			 * 
			 * } else { Errors error = Errors.notIOSentence; new
			 * ErrorFactory(sym.getLine(), sym.getRow(), error).display();
			 */}
		return result;
	}

}
