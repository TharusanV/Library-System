package librarySystem;

public class Book {
	
	private int id;
	private String name;
	private String author;
	private String genre;
	private boolean available;
	private int userID;
	
	public Book(int bookID,String bookName, String bookAuthor, String bookGenre, boolean bookAvailable, int bookUserID) {
		this.id = bookID;
		this.name = bookName;
		this.author = bookAuthor;
		this.genre = bookGenre;
		this.available = bookAvailable;
		this.userID = bookUserID;
	}
	
	public void setBookName(String changedBookName) {
		this.name = changedBookName;
	}
	
	public void setBookAuthor(String changedBookAuthor) {
		this.author = changedBookAuthor;
	}
	
	public void setBookGenre(String changedBookGenre) {
		this.genre = changedBookGenre;
	}
	
	public void setBookAvailable(boolean changedBookAvaliable) {
		this.available = changedBookAvaliable;
	}
	
	public void setBookUserID(int changedUserID) {
		this.userID = changedUserID;
	}
	
	
	
	public int getBookID() {
		return id;
	}
	
	public String getBookName() {
		return name;
	}
	
	public String getBookAuthor() {
		return author;
	}
	
	public String getBookGenre() {
		return genre;
	}
	
	public boolean getBookAvailable() {
		return available;
	}
	
	public int getBookUserID() {
		return userID;
	}
	
}
