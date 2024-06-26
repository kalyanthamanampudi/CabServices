package cabservice.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import cabservice.controller.CustomerController;
import cabservice.controller.DriverController;
import cabservice.controller.ManagementController;
import cabservice.entity.User;
import cabservice.service.CustomerService;
import cabservice.service.DriverService;
import cabservice.service.ManagementService;

@Component
public class UserInfo implements UserDetailsService {

	@Autowired
	private ManagementService mservice;
	
	@Autowired
	private CustomerService cservice;
	
	@Autowired
	private DriverService dservice;	
	
	Optional<User> user ;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(CustomerController.activeController)
			user = cservice.getUser(username);
		else if (ManagementController.activeController)
			user = mservice.getUser(username);
		else if(DriverController.activeController)
			 user = dservice.getUser(username);
		
		return user.map(UserdetailInfo::new).orElseThrow(() -> new UsernameNotFoundException("no user with the given name"));

	}

}
