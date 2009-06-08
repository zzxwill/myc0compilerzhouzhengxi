package syntaxAnalysis;

import outputPCode.PCodePut;
import symbolTable.Symbol;
import symbolTable.SymbolOper;
import errorDeal.ErrorOper;
import errorDeal.Errors;
import getNextWord.GetNext;
import getNextWord.WordStructure;

public class IO {
	int relativeAddress;
	Symbol st;

	public IO() {
		super();
	}

	public boolean payBack() {
		WordStructure word;
		boolean result = false;
		String belong="global";
		if (Factor.tempName.equals("printf")) {
			word = GetNext.payBack();
			if (word.getWordName().equals("(")) {

				word = GetNext.payBack();
		
				int lays = 1;
			

				PCodePut.output("LIT  " + (lays-1) + "  " + word.getWordName());
				System.out.println("LIT  " + (lays-1) + "  " + word.getWordName());
				
				
				word = GetNext.payBack();
				if (word.getWordName().equals(",")) {
					PCodePut.output("WRT  " + 0 + "  " + 0);
					System.out.println("WRT  " + 0 + "  " + 0);
					
					word = GetNext.payBack();
					if (word.getCode() == 27) {
//						PCodePut.output("WRT  " + 0 + "  " + 0);
//						System.out.println("WRT  " + 0 + "  " + 0);
						st = SymbolOper.getSymbol(word.getWordName(), belong);
						relativeAddress = SymbolOper.getNoByName(st.getName(),
								st.getLayer());
						System.out.println("LOD  " + st.getLayer() +" "
								+ String.valueOf(relativeAddress));
						PCodePut.output("LOD  " + st.getLayer() +"  "
								+ relativeAddress); 
//						System.out.println("LOD  " + st.getLayer() +" "
//								+ String.valueOf(relativeAddress));
						PCodePut.output("WRT  " + 0 + "  " + 0);
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
						new ErrorOper(error).display();
					}
				} else {
					Errors error = Errors.lostXKH2;
					new ErrorOper(error).display();
				}
			} else {
				Errors error = Errors.lostXKH1;
				new ErrorOper(error).display();
			}

		} else if (Factor.tempName.equals("printf")) {
			}
		return result;
	}

}
