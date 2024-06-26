package cabservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cabservice.entity.BookedUserInfo;
import cabservice.entity.Driver;
import cabservice.entity.DriverDetails;
import cabservice.entity.History;
import cabservice.entity.Logindetails;
import cabservice.service.DriverService;
import cabservice.serviceimpl.JwtServiceImpl;

@RestController
@RequestMapping("/driver")
public class DriverController {

	public static boolean activeController;

	@Autowired
	private DriverService service;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtServiceImpl jwtservice;

	@PostMapping("/register")
	public int addDriver(@RequestBody Driver d) {
		return service.addDriver(d);
	}

	public static void act() {
		DriverController.activeController = true;
		CustomerController.activeController = false;
		ManagementController.activeController = false;
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

	@GetMapping("/bookby")
	public ResponseEntity<?> bookedBy() {
		try {
			return new ResponseEntity<BookedUserInfo>(service.bookedBy(), HttpStatus.OK);
		} catch (Exception ex) {
			if (inservice() == 0)
				return new ResponseEntity<>("please ensure that your on duty", HttpStatus.OK);
			else
				return new ResponseEntity<>("No customer assigned to you at this movement", HttpStatus.OK);
		}

	}

	@GetMapping("/mydetails")
	public DriverDetails mydetails() {
		return service.myDetails();
	}

	@GetMapping("/onduty")
	public int onduty() {
		return service.onduty();
	}

	@GetMapping("/offduty")
	public int offduty() {
		return service.offduty();
	}

	@GetMapping("/inservice")
	public int inservice() {
		return service.inservice();
	}

	@GetMapping("/droped")
	public ResponseEntity<?> droped() {
			if (service.droped() == 1)
				return new ResponseEntity<String>("customer droped sucessfully", HttpStatus.OK);
			else if (service.bookedBy() == null)
					return new ResponseEntity<String>("no customer booked you yet", HttpStatus.BAD_REQUEST);
			else 
				return new ResponseEntity<String>("unable to remove customer assigned to you", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/deleteaccount")
	public ResponseEntity<?> deleteAccount() {
		if(service.deleteAccount()==1)
			return new ResponseEntity<String>("account deleted sucessfully", HttpStatus.OK);
		else if (service.bookedBy() != null)
			return new ResponseEntity<String>("you are currently working we can't delete your account now", HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<String>("unable to delete your account", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	@GetMapping("/history")
	public List<History> getHistory(){
		return service.getHistory();
	}
}
