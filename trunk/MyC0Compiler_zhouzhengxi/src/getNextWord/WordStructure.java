package getNextWord;
/*
 * 设置词法分析结果的单词的结构，
 * 用以获取一个单词的各个属性
 */
public class WordStructure {
	public String wordName = "";
	public int code = 0;
	/*
	 *  address有时候充当的是变量或常量的值
	 */
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
