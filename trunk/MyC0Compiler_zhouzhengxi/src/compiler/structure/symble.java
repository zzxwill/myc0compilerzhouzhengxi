package compiler.structure;

/*
 * ���symble.txt���ؼ����ṩһ�����ݽṹ���Ƿ��ű��е�������
 * ���ű����
// int number;		// ���
//	int type;		// ����
//	string name;	// ����

 */
public class symble {
	private String name="";
	private int num=0;
	private int type=0;
	

	    public String getname() {
	    	
	        return name;
	    }

	    public int getnumber() {
	        return num;
	    }

	    public int gettype() {
	        return type;
	    }

	    public void setname(String name) {
	        this.name = name;
	    }

	    public void setnumber(int num) {
	        this.num = num;
	    }

	    public void settype(int type) {
	        this.type = type;
	    }
}
