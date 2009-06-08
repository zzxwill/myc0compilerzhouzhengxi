package getNextWord;

public class WordStructure {
	public String wordName = "";
	public int code = 0;
	public int address;

	public WordStructure() {
		super();
	}

	public WordStructure( String wordName,int code,int address) {
		super();
		this.code = code;
		this.wordName = wordName;
		this.address = address;
	}

	public String getWordName() {
		return wordName;
	}

	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}
	
	public String toString() {
		return "单词名：" + wordName + " 单词编码：" +code  + " 单词地址：" +address;
	}

	
}
