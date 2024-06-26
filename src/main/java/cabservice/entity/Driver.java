package cabservice.entity;

public class Driver {

	private int id;
	private String name;
	private String password;
	private Long phone;
	private String email;
	private String gender;
	private int age;
	private String licence_num;
	private String car_num;
	private int onduty;
	private String role;

	public Driver() {
		super();
	}

	public Driver(int id, String name, String password, Long phone, String email, String gender, int age,
			String licence_num, String car_num, int onduty, String role) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.age = age;
		this.licence_num = licence_num;
		this.car_num = car_num;
		this.onduty = onduty;
		this.role = role;
	}

	public Driver(String name, String password, Long phone, String email, String gender, int age, String licence_num,
			String car_num) {
		super();
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.age = age;
		this.licence_num = licence_num;
		this.car_num = car_num;
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

	public String getLicence_num() {
		return licence_num;
	}

	public void setLicence_num(String licence_num) {
		this.licence_num = licence_num;
	}

	public String getCar_num() {
		return car_num;
	}

	public int getOnduty() {
		return onduty;
	}

	public void setOnduty(int onduty) {
		this.onduty = onduty;
	}

	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}

	public String getRole() {
		return role;
	}
	

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", gender=" + gender + ", age="
				+ age + ", licence_num=" + licence_num + ", car_num=" + car_num + ", role=" + role;
	}

}
