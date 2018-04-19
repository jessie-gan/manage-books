package jessie.booksmanage.pojo;

public class BorrowRecord {
	private Integer borrow_id;
	private String borrow_bookNum;
	private String borrow_bookName;
	private String borrow_stuNum;
	private String borrow_stuName;
	private String borrow_time;
	private String shouldReturn_time;
	private String Return_time;
	private Integer borrow_state;
	
	public String getBorrow_bookName() {
		return borrow_bookName;
	}
	public void setBorrow_bookName(String borrow_bookName) {
		this.borrow_bookName = borrow_bookName;
	}
	public String getBorrow_stuName() {
		return borrow_stuName;
	}
	public void setBorrow_stuName(String borrow_stuName) {
		this.borrow_stuName = borrow_stuName;
	}
	public Integer getBorrow_id() {
		return borrow_id;
	}
	public void setBorrow_id(Integer borrow_id) {
		this.borrow_id = borrow_id;
	}
	public String getBorrow_bookNum() {
		return borrow_bookNum;
	}
	public void setBorrow_bookNum(String borrow_bookNum) {
		this.borrow_bookNum = borrow_bookNum;
	}
	public String getBorrow_stuNum() {
		return borrow_stuNum;
	}
	public void setBorrow_stuNum(String borrow_stuNum) {
		this.borrow_stuNum = borrow_stuNum;
	}
	public String getBorrow_time() {
		return borrow_time;
	}
	public void setBorrow_time(String borrow_time) {
		this.borrow_time = borrow_time;
	}
	public String getShouldReturn_time() {
		return shouldReturn_time;
	}
	public void setShouldReturn_time(String shouldReturn_time) {
		this.shouldReturn_time = shouldReturn_time;
	}
	public Integer getBorrow_state() {
		return borrow_state;
	}
	public void setBorrow_state(Integer borrow_state) {
		this.borrow_state = borrow_state;
	}
	public String getReturn_time() {
		return Return_time;
	}
	public void setReturn_time(String return_time) {
		Return_time = return_time;
	}
	
}
