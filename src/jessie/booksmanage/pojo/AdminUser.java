package jessie.booksmanage.pojo;

public class AdminUser  {
	private Integer aduser_id;
	private String ad_username;
	private String ad_password;
	
	public AdminUser() {
		super();
		
	}
	public AdminUser(Integer aduser_id, String ad_username, String ad_password) {
		super();
		this.aduser_id = aduser_id;
		this.ad_username = ad_username;
		this.ad_password = ad_password;
	}
	public Integer getAduser_id() {
		return aduser_id;
	}
	public void setAduser_id(Integer aduser_id) {
		this.aduser_id = aduser_id;
	}
	public String getAd_username() {
		return ad_username;
	}
	public void setAd_username(String ad_username) {
		this.ad_username = ad_username;
	}
	public String getAd_password() {
		return ad_password;
	}
	public void setAd_password(String ad_password) {
		this.ad_password = ad_password;
	}
	
	
}
