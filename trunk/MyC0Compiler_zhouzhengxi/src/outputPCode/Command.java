package outputPCode;

public class Command {

	private int position = 0;
	private String operator;
	private String data1;
	private String data2;
	public Command(){
		super();
	}
	
	public Command(int position, String operator, String data1, String data2) {
		super();
		this.position = position;
		this.operator = operator;
		this.data1 = data1;
		this.data2 = data2;
	}
	public String getData1() {
		return data1;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	public String toString(){
		
		return position+"  "+operator+"  "+data1+"  "+data2;
	}
	
	
}
