package jessie.booksmanage.pojo;

public class User{
	private Integer user_id;
	private String student_num;
	private String username;
	private String password;
	private String register_time;
	private Integer details_id;
	private String real_name;
	private String sex;
	private String email;
	private String tel;
	private String gradeClass;
	private String major;
	private Integer readBean;
	
	public User(String student_num, String username, String password) {
		super();
		this.student_num = student_num;
		this.username = username;
		this.password = password;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getStudent_num() {
		return student_num;
	}
	public void setStudent_num(String student_num) {
		this.student_num = student_num;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getRegister_time() {
		return register_time;
	}
	public void setRegister_time(String register_time) {
		this.register_time = register_time;
	}
	public Integer getDetails_id() {
		return details_id;
	}
	public void setDetails_id(Integer details_id) {
		this.details_id = details_id;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getGradeClass() {
		return gradeClass;
	}
	public void setGradeClass(String gradeClass) {
		this.gradeClass = gradeClass;
	}
	public Integer getReadBean() {
		return readBean;
	}
	public void setReadBean(Integer readBean) {
		this.readBean = readBean;
	}
	
}
