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
			 * ��ȡ��ǰPcode����ĸ���
			 * �Ա��������������
			 * ͬʱ
			 * JMP 0,aΪ����������a
			 * ���a��ָ��ı��
			 */
			
			int curCommandNum = PCodeFactory.getCommandNum();
			
			Command command = new Command(1,"JMP","0",""+(curCommandNum+1));
			System.out.println("JMP	"+"0"+"	"+(curCommandNum+1));
//			PCodeFactory.output("LIT  " + layer + "  " + word.getWordName());
//			System.out.println("LIT  " + layer + "  " + word.getWordName());
			
			/*
			 *   ����Pcode
			 */
			PCodeFactory.insertCommand(command);
		
			/*
			 * ���µ�ǰ������
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
						 * ��������м򵥴���
						 */
//						if(new ComplexSentenceHandler().payBack()){    //�������
						
						if(new FactorHandler().payBack()){    //�������
						
							word=GetNext.payBack();
							if(word.getWordName().equals("}")){
								//PCodeFactory.output("");
								PCodeFactory.output("JMP  " + 0 + "  "
										+ "END"); // �˴�����
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
