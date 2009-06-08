package symbolTable;


import java.util.*;



public class SymbolOper {
	private static int addr;
	private static ArrayList<Symbol> al = new ArrayList<Symbol>();
	
	
	
	public SymbolOper(){
		super();
	}
	
	public static boolean add(Symbol symbol){
		
		for(Symbol s : al){
			if(s.getBelong().equals(symbol.getBelong()) && s.getName().equals(symbol.getName())){
				return false;
			}	
		}
		
		al.add(symbol);
		return true;
	}
	
	public static Symbol getSymbol(String name,String belong){
		Symbol symbol = null;
		for(Symbol s : al){
			if(s.getName().equals(name) && s.getBelong().equals(belong)){
				return s;
			}
		}		
		for(Symbol s :al){
			if(s.getName().equals(name) && s.getBelong().equals("global")){
				return s;
			}
		}
		return symbol;
	}
	
	public static Symbol getSymbol(int addr,String belong){
		Symbol symbol = null;
		for(Symbol s : al){
			if(s.getBelong().equals(belong) && s.getAddr() == addr){
				symbol = s;
			}			
		}	
		
		return symbol;
	}
	
	public static int getAddr(String belong){
		//Symbol symbol = null;
		addr = 0;
		for(Symbol s : al){
			if(s.getBelong().equals(belong))
				addr++;
		}
		return addr+1;
	}
	public static boolean isDefined(String name,String belong){
		for(Symbol s : al){
			if((s.getBelong().equals(belong)||s.getBelong().equals("global")) && name.equals(s.getName())){
				return true;
			}	
		}
		return false;
	}
	
	
	/*
	 * 打印arrayList中内容。
	 */
	public static void  printSymbol(){
		System.out.println("单词名"+" "+"类型"+" "+"值"+" "+"相对地址"+" "+"层数"+" "+"所属层");
		
		System.out.println(al.size());
		System.out.println(al.get(0));
		System.out.println(al.get(0).getName());
		System.out.println(al.get(1));
		System.out.println(al.get(2));
		for(Symbol s : al){
			s.toString();
				
		}
	}
	
	/*
	 * 由变量或常量名获得在ArrayList中位置
	 */
	public static int getNoByName(String name,String layer){
		for(int i=0;i<al.size();i++){
			if(al.get(i).getName().equals(name)&&al.get(i).getLayer().equals(layer)){
				return i+1;
			}
		}
		return -1;
	}
	
	
	
	
	
	public static void delete(Symbol symbol){
		al.remove(symbol);
	}
	
	
	

	
}
