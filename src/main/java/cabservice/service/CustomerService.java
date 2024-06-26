package cabservice.service;

import java.util.List;
import java.util.Optional;

import cabservice.entity.AvailableCabs;
import cabservice.entity.Customer;
import cabservice.entity.CustomerDetails;
import cabservice.entity.History;
import cabservice.entity.User;

public interface CustomerService {
	
	public int addCustomer(Customer cu);
	
	public Optional<User> getUser(String email);

	public List<AvailableCabs> getAvailablecabs();

	public int bookcab(int id);

	public CustomerDetails myDetails();

	public int cancelRide();

	public int deleteAccount();

	public List<History> getHistory();

	public int updateData(String field, String value);


}
