package syntaxAnalysis;

import layerControl.Layer;
import outputPCode.Command;
import outputPCode.PCodePut;
import errorDeal.ErrorFactory;
import errorDeal.Errors;
import getNextWord.GetNext;
import getNextWord.WordStructure;

public class MainFunction {
	WordStructure word;
	public MainFunction(){
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
			
			int curCommandNum = PCodePut.getCommandNum();
			
			Command command = new Command(1,"JMP","0",""+(curCommandNum+1));
			System.out.println("JMP	"+"0"+"	"+(curCommandNum+1));
//			PCodePut.output("LIT  " + layer + "  " + word.getWordName());
//			System.out.println("LIT  " + layer + "  " + word.getWordName());
			
			/*
			 *   保存Pcode
			 */
			PCodePut.insertCommand(command);
		
			/*
			 * 更新当前函数名
			 */
			Layer.setCurProcedureName("main");
			/*
			String name = "main";
			int kind = 6;
			String value = "#";
			String belong = "global";
			int addr = PCodePut.getCommandNum()+1;
			Symbol symbol = new Symbol(name,kind,value,belong,addr);
			SymbolOper.add(symbol);
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
						
						if(new Factor().payBack()){    //复合语句
						
							word=GetNext.payBack();
							if(word.getWordName().equals("}")){
								//PCodePut.output("");
								PCodePut.output("JMP  " + 0 + "  "
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
