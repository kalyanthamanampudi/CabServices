package cabservice.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabservice.entity.AvailableCabs;
import cabservice.entity.Customer;
import cabservice.entity.CustomerDetails;
import cabservice.entity.History;
import cabservice.entity.User;
import cabservice.repository.CustomerRepository;
import cabservice.service.CustomerService;

@Service
public class CustomerServiceimpl implements CustomerService {

	@Autowired
	private CustomerRepository repo;

	@Override
	public int addCustomer(Customer cu) {

		return repo.addCustomer(cu);
	}

	@Override
	public Optional<User> getUser(String email) {
		return repo.getUser(email);
	}

	@Override
	public List<AvailableCabs> getAvailablecabs() {
		return repo.getAvailablecabs();
	}

	@Override
	public int bookcab(int id) {
		return repo.bookCab(id);
	}

	@Override
	public CustomerDetails myDetails() {
		return repo.myDetails();
	}

	@Override
	public int cancelRide() {

		return repo.cancelRide();
	}

	@Override
	public int deleteAccount() {
		return repo.deleteAccount();
	}

	@Override
	public List<History> getHistory() {
		return repo.getHistory();
	}

	@Override
	public int updateData(String field, String value) {
		return repo.updateData(field, value);
	}

}
