package syntaxAnalysis;

import outputPCode.PCode;

/*
 * 语法分析程序的开始
 */
public class Start_SyntaxAnalysis {
	public Start_SyntaxAnalysis(){
		super();
	}
	
	public boolean payBack(){
		boolean result = false;
		
		/*
		 * 常量声明
		 */
		new Const().payBack();
		/*
		 * 变量声明
		 */
		new Variable().payBack();
		/*
		 *  main 函数
		 */
		new MainFunction().payBack();
		

		/*
		 * 输出Symbol中的内容
		 * 
		 */
		PCode.display();
		
		/*
		 * PCode的解释执行
		 */
		
		
		return result;
	}
	
	
	
	public static void main(String args[]){
		Start_SyntaxAnalysis start=new Start_SyntaxAnalysis();
		start.payBack();
//		SymbolOper.printSymbol();
		
	}

}
