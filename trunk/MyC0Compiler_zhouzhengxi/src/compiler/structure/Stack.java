package compiler.structure;

/*
 * ����Ķ�ջ�ṹ
 * ���ڴ��main����ǰ������
 */
public class Stack {
	private String name;
	private int code;
	private int addr;

	    public String getname() {
	        return name;
	    }

	    public int getcode() {
	        return code;
	    }

	    public int getaddr() {
	        return addr;
	    }

	    public void setname(String name) {
	        this.name = name;
	    }

	    public void setcode(int code) {
	        this.code = code;
	    }

	    public void setaddr(int addr) {
	        this.addr = addr;
	    }
}
