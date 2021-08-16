package fixtures;

public class Books extends Items implements Interactables{
	String bookName;
	
	/*
	 * The book class just prints out what the book title is
	 * As the interaction is just you looking at the book
	 */
	public Books(String bookName) {
		super("book");
		this.bookName = bookName;
	}
	
	@Override
	public void interact() {
		System.out.println("Oh look its the book: \"" + bookName + "\".\n");
	}
}
