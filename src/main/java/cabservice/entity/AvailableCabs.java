package cabservice.entity;

import org.springframework.stereotype.Component;

@Component
public class AvailableCabs {

	private int id;
	private String name;
	private Long phone;
	private String email;
	private String car_num;
	public AvailableCabs() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AvailableCabs(int id, String name, Long phone, String email, String car_num) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.car_num = car_num;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCar_num() {
		return car_num;
	}
	public void setCar_num(String car_num) {
		this.car_num = car_num;
	}
	
	


}
