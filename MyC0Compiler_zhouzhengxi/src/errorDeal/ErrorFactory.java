package errorDeal;

public class ErrorFactory {
	private int line;    //����������
	private int row;     //����������
	private Errors error;     //������Ϣ
	private static int num = 0;

	
	public ErrorFactory(Errors error){
		
	
		this.error = error;
		
	}
	
	public void display(){
		System.out.println("������Ϣ��"+this.error.value());
	}

}
