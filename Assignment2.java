
public class Assignment2 {

	public static void main(String[] args) {
		String filename = args[0];
		try {
			LexicalAnalyzer a = new LexicalAnalyzer(filename);
			Parser b = new Parser(a);
			b.Start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
