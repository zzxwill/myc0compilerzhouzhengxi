package syntaxAnalysis;

import errorDeal.ErrorFactory;
import errorDeal.Errors;
import getNextWord.GetNext;
import getNextWord.WordStructure;

public class VariableDeclare {
	WordStructure word;
	public VariableDeclare(){
		super();
	}
	public boolean payBack(){
		boolean result = false;
		
		/**
		 * ����˵��
		 */
		VariableDefine vd = new VariableDefine();
		
		
		/*
		 * �򵥴������Խ��˴���while ��Ϊ
		 *  if 
		 */
//		while(vd.payBack()){  
		if(vd.payBack()){  
			
//			word=GetNext.payBack();
			
			//System.out.println(sym);
//			if(!word.getWordName().equals(";")){
			if(!VariableDefine.tempName.equals(";")){
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
