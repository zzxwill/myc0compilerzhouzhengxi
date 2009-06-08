package errorDeal;

public class ErrorOper {
	
	private Errors error;     //错误信息

	
	public ErrorOper(Errors error){
		
	
		this.error = error;
		
	}
	
	public void display(){
		System.out.println("错误信息："+this.error.value());
	}

}
