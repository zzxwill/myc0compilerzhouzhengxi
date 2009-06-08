package test;

import interpret.Interpreter;
import syntaxAnalysis.Start_SyntaxAnalysis;
import wordAnalysis.Start_WordAnalysis;

public class Test {
	public static void main(String args[]){
		new Start_WordAnalysis().payBack();
		new Start_SyntaxAnalysis().payBack();
		Interpreter.payBack();
		
	}

}
