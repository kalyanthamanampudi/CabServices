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

import cabservice.entity.Bookings;
import cabservice.entity.CustomerDetails;
import cabservice.entity.DriverDetails;
import cabservice.entity.History;
import cabservice.entity.Logindetails;
import cabservice.entity.Management;
import cabservice.service.ManagementService;
import cabservice.serviceimpl.JwtServiceImpl;

@RestController
@RequestMapping("/management")
public class ManagementController {

	public static boolean activeController;

	@Autowired
	private ManagementService service;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtServiceImpl jwtservice;

	@PostMapping("/register")
	public int addManager(@RequestBody Management manager) {
		return service.addManager(manager);
	}

	public static void act() {
		ManagementController.activeController = true;
		CustomerController.activeController = false;
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
	
	@GetMapping("/allClients")
	public List<CustomerDetails> allCustomers(){
		return service.allCustomers();
	}
	
	@GetMapping("/allemployees")
	public List<DriverDetails> allEmployees(){
		return service.allEmployees();
	}
	
	@DeleteMapping("/deleteclient/{id}")
	public ResponseEntity<String> deleteclientaccount(@PathVariable("id") int id) {
		if(viewBookings().stream().map(Bookings::getCustomer_id).collect(Collectors.toList()).contains(id))
			return  new ResponseEntity<String>("customer is in using state", HttpStatus.OK);
		else if(service.deleteclientaccount(id)==1)
			return new ResponseEntity<String>("customer deleted sucessfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("customer not deleted ",HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteemployee/{id}")
	public ResponseEntity<String> deleteemployeeaccount(@PathVariable("id") int id) {
		if(viewBookings().stream().map(Bookings::getDriver_id).collect(Collectors.toList()).contains(id))
			return  new ResponseEntity<String>("employee is in working state", HttpStatus.OK);
		else if(service.deleteemployeeaccount(id)==1)
			return new ResponseEntity<String>("Employee deleted sucessfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("Employee not deleted ",HttpStatus.OK);
	}
	
	@GetMapping("/viewbookings")
	public List<Bookings> viewBookings(){
		return service.viewBookings();
	}
	
	@GetMapping("/clientHistory/{id}")
	public List<History> clientHistory(@PathVariable("id") int id){
		return service.clientHistory(id);
	}
	
	@GetMapping("/employeeHistory/{id}")
	public List<History> employeeHistory(@PathVariable("id") int id){
		return service.employeeHistory(id);
	}

}
