package cabservice.entity;

public class Management {
	
	 private int Id;
	 private String name ;
	 private String password;
	 private Long phone ;
	 private String email ;
	 private String role ;
	 
	 
	 
	 
	public Management() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Management(int id, String name, String password, Long phone, String email, String role) {
		super();
		Id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.role = role;
	}

	public Management(int id, String name, Long phone, String email, String role) {
		super();
		Id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.role = role;
	}
	
	public Management( String name, String password, Long phone, String email) {
		super();
		this.name = name;
		this.password=password;
		this.phone = phone;
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	

	@Override
	public String toString() {
		return "Id=" + Id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", role=" + role;
	}
	
	
	 
	 

}
