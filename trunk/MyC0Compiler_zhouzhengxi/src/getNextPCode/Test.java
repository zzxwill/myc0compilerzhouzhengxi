package getNextPCode;

public class Test {
	public static void main(String args[]) {
//		GetNext getNext = new GetNext();
		PCodeStructure pcode;
//		for (int i = 0; i < 15; i++) {
			pcode = GetNext.payBack();
			System.out.println(pcode.toString());
			System.out.println("pcode.getF():"+pcode.getF());
			System.out.println(pcode.getNum()+" "+pcode.getF()+" "+pcode.getL()+" "+pcode.getA());
			System.out.println("String--pcode.getA():"+pcode.getA());
			System.out.println("String--pcode.getA()+3:"+(pcode.getA()+3));
			System.out.println("int--pcode.getA()+3:"+(Integer.parseInt(pcode.getA())+3));


//		}
		
		
	}

}
