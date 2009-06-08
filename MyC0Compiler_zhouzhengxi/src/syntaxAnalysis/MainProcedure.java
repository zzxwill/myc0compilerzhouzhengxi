package syntaxAnalysis;

import layerControl.Layer;
import outputPCode.Command;
import outputPCode.PCodeFactory;
import errorMg.ErrorFactory;
import errorMg.Errors;
import getNextWord.GetNext;
import getNextWord.WordStructure;

public class MainProcedure {
	WordStructure word;
	public MainProcedure(){
		super();
	}
	public boolean payBack(){
		boolean result = false;
		int layer=1;
		word=GetNext.payBack();
		if(word.getWordName().equals("void")){
			word=GetNext.payBack();
		}
		//System.out.print(sym);
		if(word.getWordName().equals("main")){
			/*
			 * 获取当前Pcode命令的个数
			 * 以便于下面的命令编号
			 * 同时
			 * JMP 0,a为无条件跳到a
			 * 这里，a是指令的编号
			 */
			
			int curCommandNum = PCodeFactory.getCommandNum();
			
			Command command = new Command(1,"JMP","0",""+(curCommandNum+1));
			System.out.println("JMP	"+"0"+"	"+(curCommandNum+1));
//			PCodeFactory.output("LIT  " + layer + "  " + word.getWordName());
//			System.out.println("LIT  " + layer + "  " + word.getWordName());
			
			/*
			 *   保存Pcode
			 */
			PCodeFactory.insertCommand(command);
		
			/*
			 * 更新当前函数名
			 */
			Layer.setCurProcedureName("main");
			/*
			String name = "main";
			int kind = 6;
			String value = "#";
			String belong = "global";
			int addr = PCodeFactory.getCommandNum()+1;
			Symbol symbol = new Symbol(name,kind,value,belong,addr);
			SymbolTable.add(symbol);
			*/
			word=GetNext.payBack();
	
			if(word.getWordName().equals("(")){
				word=GetNext.payBack();
				if(word.getWordName().equals(")")){
					word=GetNext.payBack();
					if(word.getWordName().equals("{")){
						//Layer.addLayer();
						//if(true){
						
						/*
						 * 在这里进行简单处理
						 */
//						if(new ComplexSentenceHandler().payBack()){    //复合语句
						
						if(new FactorHandler().payBack()){    //复合语句
						
							word=GetNext.payBack();
							if(word.getWordName().equals("}")){
								//PCodeFactory.output("");
								PCodeFactory.output("JMP  " + 0 + "  "
										+ "END"); // 此处待议
								System.out.println("LIT  " + 0 + "  "
										+ "END");
								
								Layer.setCurProcedureName("global");
								result = true;
							}else{
								Errors error = Errors.lostDKH2;
								new ErrorFactory(error).display();
							}
						}
						
					}else{
						Errors error = Errors.lostDKH1;
						new ErrorFactory(error).display();
					}
				}else{
					Errors error = Errors.lostXKH2;
					new ErrorFactory(error).display();
				}
			}else{
				Errors error = Errors.lostXKH1;
				new ErrorFactory(error).display();
			}			
			
		}else{
			Errors error = Errors.lostMain;
			new ErrorFactory(error).display();
		}
		
		return result;
	}
}
