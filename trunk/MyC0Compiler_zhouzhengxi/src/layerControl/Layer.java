package layerControl;

public class Layer {
	private static String curProcedureName = "global";
	private static String preProcedureName = "global";
	private static int layer = 0;
	private static int addr = 0;
	/*
	public static void addLayer(){
		layer++;
	}
	public static void delLayer(){
		layer--;
	}
	public static int getCurLayer(){
		return layer;
	}
	*/
	
	/*
	 * ���¹��������������е�s,����Ҫ��
	 * curProcedureName����Ϊs
	 * ��ȻҪ�ǵý�ԭ���ĸ��£�
	 * ԭ����preProcedureNameҲ�ͱ���˸ղŵ�����curProcedureName
	 */
	public static void setCurProcedureName(String s){
		preProcedureName = curProcedureName;
		curProcedureName = s;
	}
	public static String getCurProcedureName(){
		return curProcedureName;
	}
	public static void setToPreProcedureName(){
		curProcedureName = preProcedureName;
	}
	

}
