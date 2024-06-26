package cabservice.entity;

public class Customer {
	
	private int id ;
	private String name ;
	private String password ;
	private Long phone;
	private String email ;
	private String gender ;
	private int age;
	private String role ;
	
	public Customer(int id, String name,String password, Long phone, String email, String gender, int age,
			String role) {
		super();
		this.id = id;
		this.name = name;
		this.password=password;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.age = age;
		this.role = role;
	}
	
	public Customer(int id, String name, Long phone, String email, String gender, int age,
			String role) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.age = age;
		this.role = role;
	}
	
	public Customer(String name, String password, Long phone, String email, String gender, int age) {
		super();
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.age = age;
	}
	
	public Customer() {
		super();
	}

	public int getId() {
		return id;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getRole() {
		return role;
	}
	
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", gender=" + gender
				+ ", age=" + age + ", role=" + role ;
	}
	
	
	
	
	

}
