package getNextWord;

public class Test {
	public static void main(String args[]) {
//		GetNext getNext = new GetNext();
		WordStructure wordStructure;
		for (int i = 0; i < 400; i++) {
			wordStructure = GetNext.payBack();
			System.out.println(wordStructure.toString());
			System.out.println("wordStructure.getWordName():"+wordStructure.getWordName());
		}
	}

}
