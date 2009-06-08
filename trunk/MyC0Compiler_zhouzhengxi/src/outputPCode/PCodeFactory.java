package outputPCode;
import java.util.*;

public class PCodeFactory {
	private static int commandNum = 1;
	public static void output(String s){
		commandNum++;
		//�����ַ���s��Ȼ����� Command�࣬����PCode
		int position = commandNum;
		String operator = "";
		String data1 = "";
		String data2 = "";
		/*
		 * Ϊָ���ַ�������һ�� string tokenizer��tokenizer ʹ��Ĭ�ϵķָ����� " \t\n\r\f"��
		 * �����հ��ַ����Ʊ�������з����س����ͻ�ҳ�����ָ����ַ�������Ϊ��ǡ� 
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
