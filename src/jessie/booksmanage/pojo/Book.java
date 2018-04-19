package jessie.booksmanage.pojo;

public class Book {
	private Integer book_id;
	private String book_num;
	private String book_name;
	private String book_author;
	private String book_press;
	private String book_summary;
	//private String picPath;
	private Integer book_state;
	
	public Book(String book_num, String book_name, String book_author, String book_press, String book_summary) {
		super();
		this.book_num = book_num;
		this.book_name = book_name;
		this.book_author = book_author;
		this.book_press = book_press;
		this.book_summary = book_summary;
	}
	public Book() {
		super();
	}
	public Integer getBook_id() {
		return book_id;
	}
	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}
	public String getBook_num() {
		return book_num;
	}
	public void setBook_num(String book_num) {
		this.book_num = book_num;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public String getBook_press() {
		return book_press;
	}
	public void setBook_press(String book_press) {
		this.book_press = book_press;
	}
	
	public String getBook_summary() {
		return book_summary;
	}
	public void setBook_summary(String book_summary) {
		this.book_summary = book_summary;
	}
	public Integer getBook_state() {
		return book_state;
	}
	public void setBook_state(Integer book_state) {
		this.book_state = book_state;
	}

}
