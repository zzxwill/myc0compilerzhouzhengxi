package getNextPCode;

import outputPCode.Command;
import outputPCode.PCode;

public class PCodeStructure {
	/*
	 * PCode的结构
	 * 7.f l,a
	 */
	public int num;
	public String f = "";
	public int l = 0;
	public String a;

	public PCodeStructure() {
		super();
	}

	public PCodeStructure( int num,String f,int l,String a) {
		super();
		this.num=num;
		this.f=f;
		this.l=l;
		this.a=a;
	}

	
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String toString() {
		return "PCode编号" + num + " 操作：" +f  + " 第一操作数：" +l+ " 第二操作数：" +a;
	}
	
	
	/*
	 * 通过Num得到pcode
	 */
	public static PCodeStructure getPCodeByNO(int a){
		PCodeStructure pcode;

		pcode=GetNext.payBack();
		while(pcode.getNum()!=a){
			pcode=GetNext.payBack();
			if(pcode.getNum()==a){
				break;
			}
		}
		return pcode;

		
	}

	
}
