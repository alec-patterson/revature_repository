package inputSplit;

public class splitter {
	public static void main(String[] args) {
		
		String s = "Apple Orange Grapes";
		parser p = new parseByBar();
		String[] par = p.parse(s);

		for(String pn: par) {
			System.out.println(pn);
		}
		
	}
}

interface parser {
	String[] parse(String data);
}

class parseByBar implements parser{
	@Override
	public String[] parse(String data) {
		String[] r = data.split(" ");
		return r;
	}
}
