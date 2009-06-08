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
	 * 更新过程名，现在运行的s,所以要将
	 * curProcedureName更新为s
	 * 当然要记得将原来的更新，
	 * 原来的preProcedureName也就变成了刚才的现在curProcedureName
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
