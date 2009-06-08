package outputPCode;
import java.util.*;

public class PCodeFactory {
	private static int commandNum = 1;
	public static void output(String s){
		commandNum++;
		//解析字符串s，然后存入 Command类，加入PCode
		int position = commandNum;
		String operator = "";
		String data1 = "";
		String data2 = "";
		/*
		 * 为指定字符串构造一个 string tokenizer。tokenizer 使用默认的分隔符集 " \t\n\r\f"，
		 * 即：空白字符、制表符、换行符、回车符和换页符。分隔符字符本身不作为标记。 
		 */
		StringTokenizer st = new StringTokenizer(s);
		if(st.hasMoreTokens()){
			operator = st.nextToken();
			data1 = st.nextToken();
			data2 = st.nextToken();
		}
		Command command = new Command(position,operator,data1,data2);
		PCode.insertCommand(command);
	}
	
	public static int getCommandNum(){
		return commandNum;
	}	
	public static void insertCommand(Command command){
		PCode.insertCommand(command);
	}

}
