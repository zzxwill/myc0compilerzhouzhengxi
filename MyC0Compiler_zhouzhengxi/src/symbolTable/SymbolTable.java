package symbolTable;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

import errorDeal.ErrorFactory;
import errorDeal.Errors;


public class SymbolTable {
	private static int addr;
	private static ArrayList<Symbol> al = new ArrayList<Symbol>();
	private static FileOutputStream fos = null;
	private static PrintWriter pw = null; 
	
	static{		
		try {
			fos = new FileOutputStream("symbolTable.txt");
			pw = new PrintWriter(fos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SymbolTable(){
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
	public static boolean isProcedure(String name){
		Symbol symbol = getSymbol( name, "global" );                      //����C0�ķ����亯��������������㣬��0��
		if(symbol.getKind()==5 || symbol.getKind()==6){
			return true;
		}
		return false;
	}
	
	/*
	 * ��ӡarrayList�����ݡ�
	 */
	public static void  printSymbol(){
		System.out.println("������"+" "+"����"+" "+"ֵ"+" "+"��Ե�ַ"+" "+"����"+" "+"������");
		
		System.out.println(al.size());
		System.out.println(al.get(0));
		System.out.println(al.get(0).getName());
//		System.out.println(al.);
		System.out.println(al.get(1));
		System.out.println(al.get(2));
//		System.out.println(al.indexOf());
		for(Symbol s : al){
			s.toString();
				
		}
	}
	
	/*
	 * �ɱ��������������ArrayList��λ��
	 */
	public static int getNoByName(String name,String layer){
		for(int i=0;i<al.size();i++){
			if(al.get(i).getName().equals(name)&&al.get(i).getLayer().equals(layer)){
				return i+1;
			}
		}
		return -1;
	}
	
	/*
	///�˷���ȡ�øñ�����������Ĳ��
	public static int getLevel(String name,int curLayer){
		int layer = 0;
		for(Symbol s : al){
			if(s.getName().equals(name) && s.getLevel() <= curLayer){
				if(s.getLevel() >= layer){
					layer = s.getLevel();
				}
			}			
		}
		
		return layer;
	}
	*/
	
	/**�˷���ȡ�ñ�����Ϊname�����������Symbol����*/
	/*public static Symbol getTheSymbol(String name, int curLayer){
		int layer = 0;
		for(Symbol s : al){
			if(s.getName().equals(name) && s.getLevel() <= curLayer){
				if(s.getLevel() >= layer){
					layer = s.getLevel();
				}
			}			
		}
		return getSymbol(name,layer);
	}
	*/
	public static void display(){
		if(al != null){
			for(Symbol s : al){
				System.out.println(s.toString());
				pw.println(s);
				pw.close();
			}
		}else{
			System.out.println("���ű�Ϊ��");
		}
	}
	public static void setValue(String name,String belong){
		Symbol symbol = getSymbol(name,belong);
		symbol.setValue("#");   // #����������ʵֵ���˴�ֻ��Ϊ��˵�����Ѿ�����ʼ��
	}
	public static void delete(Symbol symbol){
		al.remove(symbol);
	}
	public static void delete_var_in_pro(String belong){
		int len = al.size();
		//System.out.print(al);
		Symbol s;
		for(int i =0 ; i < len ; i++ ){
			s = al.get(i);
			if(s!= null && s.getBelong().equals(belong)){
				al.remove(s);
				i--;
				len--;
			}
		}
	}
	
	public static int getParamNum(int procAddr){
		int num = 0;
		for(Symbol s : al){
			if(s.getBelong().equals(getProcNameByAddr(procAddr)))
				num++;			
		}
		return num;
	}
	public static String getProcNameByAddr(int procAddr){
		for(Symbol s : al){
			if((s.getKind() == 5||s.getKind() == 6) && s.getAddr() == procAddr )
				return s.getName();
		}
		return null;
	}
	
	public static Symbol getLocalVariableInProc(int procAddr,int variableAddr){
		String procName = getProcNameByAddr(procAddr);
		for(Symbol s : al){
			if(s.getBelong().equals(procName) && s.getAddr() == variableAddr)
				return s;						
		}
		return null;
	}
	
}
