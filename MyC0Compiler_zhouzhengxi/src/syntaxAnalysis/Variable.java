package syntaxAnalysis;

import errorDeal.ErrorFactory;
import errorDeal.Errors;
import getNextWord.GetNext;
import getNextWord.WordStructure;

public class Variable {
	WordStructure word;
	public Variable(){
		super();
	}
	public boolean payBack(){
		boolean result = false;
		
		/**
		 * 变量说明
		 */
		VariableDeal vd = new VariableDeal();
		
		
		/*
		 * 简单处理，所以将此处的while 改为
		 *  if 
		 */
//		while(vd.payBack()){  
		if(vd.payBack()){  
			
//			word=GetNext.payBack();
			
			//System.out.println(sym);
//			if(!word.getWordName().equals(";")){
			if(!VariableDeal.tempName.equals(";")){
				Errors error = Errors.lostFH_Error;
				new ErrorFactory(error).display();
				result = false;
//				break;
			}else{
				result = true;
			}
		}
		//SymFactory.rollBack();
		
		return result;
	}
}
