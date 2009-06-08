package errorDeal;

public enum Errors {
	floatError("浮点数格式不正确"),
	integerError("整数格式不正确"),
	charError("缺少字符 ' "),
	stringError("缺少字符 \" "),
	illegalError("非法的字符"),
	lostFH_Error("缺少字符 ; "),
	lostMH_Error("缺少 ："),
	lostEqual("缺少 ="),
	lostSymbol("缺少标识符"),
	lostValue("没有赋值或类型不正确"),
	lostMain("缺少关键字 main"),
	lostXKH1("缺少 ("),
	lostXKH2("缺少 )"),
	lostDKH1("缺少 {"),
	lostDKH2("缺少 }"),
	
	
	valueTypeNotCompare("数据类型不匹配"),
	
	voidProCantInExp("无返回值函数不能出现在表达式中"),
	
	constIntroduceError("常量说明错误"),
	constDefineError("常量定义错误"),
	consReDefine("常量重复定义"),
	constCantEnvalue("常量不能被赋值"),
	illegalTypeError("非法的类型"),
	variableUndefined("变量没有声明"),
	variableDefineError("变量定义错误"),
	variableRedefine("变量重复定义"),
	variableNotInitialize("变量没有初始化"),
	paramError("参数定义有误"),
	unDeclare("标识符未声明"),
	
	expressionError("表达式有误"),
	factorError("因子有误"),
	notIOSentence("IO语句错误"),
	
	if_Error("if语句有误"),
	while_Error("while语句有误"),
	
	noReturnSentence("不能用返回值语句"),
	mustReturnSentence("必须有返回值语句"),
	caseError("情况语句有误，需为常量"),
	
	
	
	
	procedure_Error("函数有误"),
	pricedureRedefine("函数定义重名"),
	mainProcedureError("主函数有误"),
	
	
	
	sucessToCompile("编译成功！"),
	failToCompile("编译失败！"),
	unknowError("未知错误"),
	errorCommand("错误的指令");
	
	private final String value;
	
	Errors(String e){
		this.value = e;
	}
	
	public String value(){
		return value;
	}
	

}
