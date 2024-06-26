package cabservice.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabservice.entity.Bookings;
import cabservice.entity.CustomerDetails;
import cabservice.entity.DriverDetails;
import cabservice.entity.History;
import cabservice.entity.Management;
import cabservice.entity.User;
import cabservice.repository.ManagementRepository;
import cabservice.service.ManagementService;

@Service
public class ManagemantServiceImpl implements ManagementService {

	@Autowired 
	private ManagementRepository repo;
	@Override
	public int addManager(Management manager) {
		
		return repo.addManager(manager);
	}
	@Override
	public Optional<User> getUser(String email) {
		return repo.getUser(email);
	}
	@Override
	public int deleteclientaccount(int id) {
		return repo.deleteclientaccount( id);
	}
	@Override
	public int deleteemployeeaccount(int id) {
		return repo.deleteemployeeaccount( id);
	}
	@Override
	public List<Bookings> viewBookings() {
		return repo.viewBookings();
	}
	@Override
	public List<History> clientHistory(int id) {
		return repo.clientHistory(id);
	}
	@Override
	public List<History> employeeHistory(int id) {
		return repo.employeeHistory(id);
	}
	@Override
	public List<CustomerDetails> allCustomers() {
		return repo.allCustomers();
	}
	@Override
	public List<DriverDetails> allEmployees() {
		return repo.allEmployees();
	}
	@Override
	public int updateData(String field, String value) {
		return repo.updateData(field, value);
	}

}
