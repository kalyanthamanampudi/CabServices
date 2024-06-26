package cabservice.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cabservice.entity.BookedUserInfo;
import cabservice.entity.Driver;
import cabservice.entity.DriverDetails;
import cabservice.entity.History;
import cabservice.entity.User;
import cabservice.repository.DriverRepository;
import cabservice.service.DriverService;

@Service
public class DriverServiceimpl implements DriverService {

	@Autowired private DriverRepository repo;
	@Override
	public int addDriver(Driver d) {
		
		return repo.addDriver(d);
	}
	
	@Override
	public Optional<User> getUser(String email) {
		return repo.getUser(email);
	}

	@Override
	public BookedUserInfo bookedBy() {
		return repo.bookedBy();
	}

	@Override
	public DriverDetails myDetails() {
		return repo.myDetails();
	}

	@Override
	public int onduty() {
		return repo.onduty();
	}

	@Override
	public int offduty() {
		return repo.offduty();
	}

	@Override
	public int inservice() {
		return repo.inservice();
	}

	@Override
	public int droped() {
		return repo.droped();
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
