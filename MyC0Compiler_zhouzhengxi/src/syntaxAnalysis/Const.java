package syntaxAnalysis;

import errorDeal.ErrorFactory;
import errorDeal.Errors;
import getNextWord.GetNext;
import getNextWord.WordStructure;

/*
 * ���������ӳ���
 */
public class Const {
	
	/*
	 * tempName;�Ǵ���
	 * �����������õġ�
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
					 * �����һ�䲻Ҫ
					 * ��Ϊ����ConstDeal()�У�����Ѿ���ȡ����һ������
					 * �����ж����ǲ��ǣ�
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
