package cabservice.entity;

public class Bookings {
	
	private int id;
	private int customer_id;
	private int driver_id;
	
	public Bookings() {
		super();
	}
	
	public Bookings(int id, int customer_id, int driver_id) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.driver_id = driver_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}
	
	
	

}
