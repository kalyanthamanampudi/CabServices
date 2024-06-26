package cabservice.entity;

import java.sql.Time;
import java.util.Date;

public class History {
	
	private Date date;
	private Time time;
	private int customer_id;
	private int driver_id;

	public History() {
	}

	public History(Date date,Time time, int customer_id, int driver_id) {
		super();
		this.date = date;
		this.time = time;
		this.customer_id = customer_id;
		this.driver_id = driver_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
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
