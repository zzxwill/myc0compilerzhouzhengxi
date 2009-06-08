package outputPCode;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;
import symbolTable.*;

public class PCode {
	private static TreeMap<Integer,Command> al = new TreeMap<Integer,Command>();
	private  static int nextCommandPosition = 1;     //�൱������ַ�Ĵ����������һ��Ҫִ�е�ָ���ַ
	private static FileOutputStream fos = null;
	private static PrintWriter pw = null; 
	
	static{		
		try {
			fos = new FileOutputStream("pcode.txt");
			pw = new PrintWriter(fos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public PCode(){
		super();
	}
	public static void insertCommand(Command command){
		al.put(command.getPosition(), command);
	}
	
	public static void display(){
		System.out.println("------------------------------------------- ��ӡPCode------------------------------------------------");
		for(Command c : al.values()){
			System.out.println(c);
			pw.println(c);
		}
		pw.close();
		System.out.println("-------------------------------------------��ӡPCode-------------------------------------------");
	}
	
	public static boolean hasMoreCommand(){
		if(nextCommandPosition <= al.size())
			return true;
		else return false;
	}
	
	public static Command getNextCommand(){
		return al.get(nextCommandPosition++);
	}
	public static void setNextCommandPosition(int nextCP){
		nextCommandPosition = nextCP;
	}
	
	
	
	
		
		
	
	
}
