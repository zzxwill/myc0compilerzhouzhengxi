package errorDeal;

public enum Errors {
	floatError("��������ʽ����ȷ"),
	integerError("������ʽ����ȷ"),
	charError("ȱ���ַ� ' "),
	stringError("ȱ���ַ� \" "),
	illegalError("�Ƿ����ַ�"),
	lostFH_Error("ȱ���ַ� ; "),
	lostMH_Error("ȱ�� ��"),
	lostEqual("ȱ�� ="),
	lostSymbol("ȱ�ٱ�ʶ��"),
	lostValue("û�и�ֵ�����Ͳ���ȷ"),
	lostMain("ȱ�ٹؼ��� main"),
	lostXKH1("ȱ�� ("),
	lostXKH2("ȱ�� )"),
	lostDKH1("ȱ�� {"),
	lostDKH2("ȱ�� }"),
	
	
	valueTypeNotCompare("�������Ͳ�ƥ��"),
	
	voidProCantInExp("�޷���ֵ�������ܳ����ڱ��ʽ��"),
	
	constIntroduceError("����˵������"),
	constDefineError("�����������"),
	consReDefine("�����ظ�����"),
	constCantEnvalue("�������ܱ���ֵ"),
	illegalTypeError("�Ƿ�������"),
	variableUndefined("����û������"),
	variableDefineError("�����������"),
	variableRedefine("�����ظ�����"),
	variableNotInitialize("����û�г�ʼ��"),
	paramError("������������"),
	unDeclare("��ʶ��δ����"),
	
	expressionError("���ʽ����"),
	factorError("��������"),
	notIOSentence("IO������"),
	
	if_Error("if�������"),
	while_Error("while�������"),
	
	noReturnSentence("�����÷���ֵ���"),
	mustReturnSentence("�����з���ֵ���"),
	caseError("������������Ϊ����"),
	
	
	
	
	procedure_Error("��������"),
	pricedureRedefine("������������"),
	mainProcedureError("����������"),
	
	
	
	sucessToCompile("����ɹ���"),
	failToCompile("����ʧ�ܣ�"),
	unknowError("δ֪����"),
	errorCommand("�����ָ��");
	
	private final String value;
	
	Errors(String e){
		this.value = e;
	}
	
	public String value(){
		return value;
	}
	

}
