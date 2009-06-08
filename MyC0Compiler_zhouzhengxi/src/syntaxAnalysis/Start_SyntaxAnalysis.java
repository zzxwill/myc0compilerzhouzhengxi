package syntaxAnalysis;

import interpret.Interpreter;
import outputPCode.PCode;
import symbolTable.SymbolTable;



public class Start_SyntaxAnalysis {
	public Start_SyntaxAnalysis(){
		super();
	}
	
	public boolean payBack(){
		boolean result = false;
		
		/*
		 * ��������
		 */
		new ConstHandler().payBack();
		/*
		 * ��������
		 */
		new VariableDeclare().payBack();
		/*
		 *  main ����
		 */
		new MainProcedure().payBack();
		
		new PCode();
		/*
		 * ���Symbol�е�����
		 * 
		 */
		PCode.display();
		
		/*
		 * PCode�Ľ���ִ��
		 */
		Interpreter.payBack();
		
		return result;
	}
	
	
	
	public static void main(String args[]){
		Start_SyntaxAnalysis start=new Start_SyntaxAnalysis();
		start.payBack();
//		SymbolTable.printSymbol();
		
	}

}
