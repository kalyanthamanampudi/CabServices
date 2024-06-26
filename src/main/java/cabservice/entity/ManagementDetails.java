package cabservice.entity;

public class ManagementDetails {
	
	 private int Id;
	 private String name ;
	 private Long phone ;
	 private String email ;
	 private String role ;
	 
	 
	 
	 
	public ManagementDetails() {
		}

	public ManagementDetails(int id, String name, Long phone, String email, String role) {
		super();
		Id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.role = role;
	}

	public int getId() {
		return Id;
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

	public String getRole() {
		return role;
	}

}
