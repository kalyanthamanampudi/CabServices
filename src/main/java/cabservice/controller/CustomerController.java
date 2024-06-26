package cabservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cabservice.entity.AvailableCabs;
import cabservice.entity.Customer;
import cabservice.entity.CustomerDetails;
import cabservice.entity.History;
import cabservice.entity.Logindetails;
import cabservice.service.CustomerService;
import cabservice.serviceimpl.JwtServiceImpl;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	public static boolean activeController;

	@Autowired
	private CustomerService service;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtServiceImpl jwtservice;

	@PostMapping("/register")
	public int addCustomer(@RequestBody Customer cu) {
		return service.addCustomer(cu);
	}

	public static void act() {
		CustomerController.activeController = true;
		ManagementController.activeController = false;
		DriverController.activeController = false;
	}

	@PostMapping("/login")
	public String authenticateAndgenerateToken(@RequestBody Logindetails au) {
		Authentication auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(au.getName(), au.getPassword()));
		if (auth.isAuthenticated()) {
			return jwtservice.generateToken(au.getName());
		} else {
			throw new UsernameNotFoundException("invalid user request");
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateData(@RequestParam String field,String value) {
		if(service.updateData(field,value)==1) {
			return new ResponseEntity<String>("your "+field+" has been changed to "+value,HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("unable to update",HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/getavailablecabs")
	public List<AvailableCabs> getCabs() {
	return service.getAvailablecabs();
	}
	
	@GetMapping("/bookcab/{cabid}")
	public String bookCab(@PathVariable("cabid") int id) {
		if (getCabs().stream().map(AvailableCabs::getId).collect(Collectors.toList()).contains(id)) {
			if (service.bookcab(id) == 1) {
				return "booked";
			} else
				return "unable to book cab";
		} else
			return "cab is not available";
	}
	
	@GetMapping("/mydetails")
	public CustomerDetails mydetails() {
		return service.myDetails();
	}
	
	@GetMapping("/cancelride")
	public int cancelRide() {
		return service.cancelRide();
	}
	
	@DeleteMapping("/deleteaccount")
	public int deleteAccount() {
		return service.deleteAccount();	
	}
	
	@GetMapping("/history")
	public List<History> history(){
		return service.getHistory();
	}
}
