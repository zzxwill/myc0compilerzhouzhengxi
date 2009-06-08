package syntaxAnalysis;

import errorMg.ErrorFactory;
import errorMg.Errors;
import getNextWord.GetNext;
import getNextWord.WordStructure;


public class ConstHandler {
	
	/*
	 * tempName;是传给
	 * 变量声明来用的。
	 */
	public static String tempName;
	public ConstHandler(){
		super();
	}
	
	public boolean payBack(){
		boolean result = false;
		WordStructure word;
		
		word = GetNext.payBack();
		if(word.getWordName().equals("const")){      //常量说明
			do{
				if(new ConstDefineHandler().payBack()){    //常量定义
//					word = GetNext.payBack();
					/*
					 * 上面的一句不要
					 * 因为，在ConstDefineHandler()中，最后，已经获取和了一个单词
					 * 并且判断它是不是，
					 * 
					 * 
					 */
					if(ConstDefineHandler.tempName.equals(";")){
						result = true;
						
					
					}else{
						Errors error = Errors.lostFH_Error;
						new ErrorFactory(error).display();
						result = false;
						break;
					}
				}else{
					break;
				}
				word=GetNext.payBack();
				tempName=word.getWordName();
				
			}while(tempName.equals("const"));			
		}
		
//		SymFactory.rollBack();
		
		return result;
	}

}
