package errorDeal;

public class ErrorOper {
	
	private Errors error;     //������Ϣ

	
	public ErrorOper(Errors error){
		
	
		this.error = error;
		
	}
	
	public void display(){
		System.out.println("������Ϣ��"+this.error.value());
	}

}
