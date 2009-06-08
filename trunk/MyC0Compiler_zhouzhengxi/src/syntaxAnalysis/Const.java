package syntaxAnalysis;

import errorDeal.ErrorFactory;
import errorDeal.Errors;
import getNextWord.GetNext;
import getNextWord.WordStructure;

/*
 * 声明常量子程序
 */
public class Const {
	
	/*
	 * tempName;是传给
	 * 变量声明来用的。
	 */
	public static String tempName;
	public Const(){
		super();
	}
	
	public boolean payBack(){
		boolean result = false;
		WordStructure word;
		
		word = GetNext.payBack();
		if(word.getWordName().equals("const")){      
			do{
				if(new ConstDeal().payBack()){   
//					word = GetNext.payBack();
					/*
					 * 上面的一句不要
					 * 因为，在ConstDeal()中，最后，已经获取和了一个单词
					 * 并且判断它是不是，
					 * 
					 * 
					 */
					if(ConstDeal.tempName.equals(";")){
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
		
		
		return result;
	}

}
