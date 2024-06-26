package cabservice.service;

import java.util.List;
import java.util.Optional;

import cabservice.entity.Bookings;
import cabservice.entity.CustomerDetails;
import cabservice.entity.DriverDetails;
import cabservice.entity.History;
import cabservice.entity.Management;
import cabservice.entity.User;


public interface ManagementService {
	
	public int addManager(Management manager);

	public Optional<User> getUser(String email);

	public int deleteclientaccount(int id);

	public int deleteemployeeaccount(int id);

	public List<Bookings> viewBookings();

	public List<History> clientHistory(int id);

	public List<History> employeeHistory(int id);

	public List<CustomerDetails> allCustomers();

	public List<DriverDetails> allEmployees();

	public int updateData(String field, String value);

}
