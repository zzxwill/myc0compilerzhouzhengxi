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
		 * 常量声明
		 */
		new ConstHandler().payBack();
		/*
		 * 变量声明
		 */
		new VariableDeclare().payBack();
		/*
		 *  main 函数
		 */
		new MainProcedure().payBack();
		
		new PCode();
		/*
		 * 输出Symbol中的内容
		 * 
		 */
		PCode.display();
		
		/*
		 * PCode的解释执行
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
