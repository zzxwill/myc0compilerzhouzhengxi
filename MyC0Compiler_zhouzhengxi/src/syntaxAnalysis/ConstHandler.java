package syntaxAnalysis;

import errorMg.ErrorFactory;
import errorMg.Errors;
import getNextWord.GetNext;
import getNextWord.WordStructure;


public class ConstHandler {
	
	/*
	 * tempName;�Ǵ���
	 * �����������õġ�
	 */
	public static String tempName;
	public ConstHandler(){
		super();
	}
	
	public boolean payBack(){
		boolean result = false;
		WordStructure word;
		
		word = GetNext.payBack();
		if(word.getWordName().equals("const")){      //����˵��
			do{
				if(new ConstDefineHandler().payBack()){    //��������
//					word = GetNext.payBack();
					/*
					 * �����һ�䲻Ҫ
					 * ��Ϊ����ConstDefineHandler()�У�����Ѿ���ȡ����һ������
					 * �����ж����ǲ��ǣ�
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
