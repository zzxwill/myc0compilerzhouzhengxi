package syntaxAnalysis;

import outputPCode.PCode;

/*
 * �﷨��������Ŀ�ʼ
 */
public class Start_SyntaxAnalysis {
	public Start_SyntaxAnalysis(){
		super();
	}
	
	public boolean payBack(){
		boolean result = false;
		
		/*
		 * ��������
		 */
		new Const().payBack();
		/*
		 * ��������
		 */
		new Variable().payBack();
		/*
		 *  main ����
		 */
		new MainFunction().payBack();
		

		/*
		 * ���Symbol�е�����
		 * 
		 */
		PCode.display();
		
		/*
		 * PCode�Ľ���ִ��
		 */
		
		
		return result;
	}
	
	
	
	public static void main(String args[]){
		Start_SyntaxAnalysis start=new Start_SyntaxAnalysis();
		start.payBack();
//		SymbolOper.printSymbol();
		
	}

}
