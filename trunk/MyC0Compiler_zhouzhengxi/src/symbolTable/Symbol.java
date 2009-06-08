package symbolTable;

public class Symbol {
	private String name;
	private int code;
	private int kind = 0;   
	/*
	 *如声明中
	 *const int a = 3;
	 *则value为3
	 */
	private String  value = "";   //表示标识符的值,同时可表示带返回值函数的返回值类型，"int"整数，"float"浮点数"char"字符
	private int addr = 0;
	/*
	 *  layer的类型改为String .以适应之前定义的
	 *  "global"
	 *  
	 */
	private String layer = "";
	private String belong = "";
	
//	private int isConst = 0;
//	private int isParam = 0;
	
	public Symbol(String name,int kind,String value,int addr,String layer,String belong){
		super();
		this.name = name;
		this.kind = kind;
		this.value=value;
		this.addr = addr;
		this.belong = belong;
		this.layer=layer;
		this.layer=layer;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Symbol(String name , int kind , String value , String belong, int addr ) {
		super();
		this.name = name;
		this.kind = kind;
		this.value = value;
		this.belong = belong;
		this.addr = addr;
	}

	public Symbol(){
		super();
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getAddr() {
		return addr;
	}
	public void setAddr(int addr) {
		this.addr = addr;
	}
	public String getLayer() {
		return layer;
	}
	public void setLayer(String layer) {
		this.layer = layer;
	}
	public String getBelong() {
		return belong;
	}
	public void setBelong(String belong) {
		this.belong = belong;
	}
//	String name,int kind,String value,int addr,String layer,String belong
	public String toString(){
//		return "标识符："+name+"  类型："+kind+"  值："+value+"  所属函数："+belong+"  地址："+addr+"  常量：";
		
		return name+" "+kind+" "+value+" "+addr+" "+layer+" "+belong;
	}
}
