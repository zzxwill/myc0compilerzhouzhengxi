package errorDeal;

public class ErrorFactory {
	private int line;    //错误所在行
	private int row;     //错误所在列
	private Errors error;     //错误信息
	private static int num = 0;

	
	public ErrorFactory(Errors error){
		
	
		this.error = error;
		
	}
	
	public void display(){
		System.out.println("错误信息："+this.error.value());
	}

}
