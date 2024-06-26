package cabservice.entity;

public class BookedUserInfo {
	
	private String name;
	private Long phone;
	private String email;
	
	public BookedUserInfo() {
	}
	
	public BookedUserInfo(String name, Long phone, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
