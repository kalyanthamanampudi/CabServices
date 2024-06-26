package cabservice.service;

import java.util.List;
import java.util.Optional;

import cabservice.entity.BookedUserInfo;
import cabservice.entity.Driver;
import cabservice.entity.DriverDetails;
import cabservice.entity.History;
import cabservice.entity.User;

public interface DriverService {
	
	public int addDriver(Driver d);
	
	public Optional<User> getUser(String email);

	public BookedUserInfo bookedBy();

	public DriverDetails myDetails();

	public int onduty();

	public int offduty();

	public int inservice();

	public int droped();

	public int deleteAccount();

	public List<History> getHistory();

	public int updateData(String field, String value);

}
