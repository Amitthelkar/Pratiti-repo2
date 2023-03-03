package com.pratiti.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.query.criteria.internal.expression.function.CurrentTimeFunction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.entity.Customer;
import com.pratiti.exception.CustomerServiceException;
import com.pratiti.model.LoginData;
import com.pratiti.output.Activation;
import com.pratiti.output.CustomerData;
import com.pratiti.output.LoginStatus;
import com.pratiti.output.RegistrationStatus;
import com.pratiti.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	RegistrationStatus status = new RegistrationStatus();
	int a = 0;
	
	
	@PostMapping(path="/register",consumes="multipart/form-data")
	public RegistrationStatus register( CustomerData customerData) throws IOException {		
		try {
//			System.out.println(customerData.getProfilepic().getOriginalFilename());
			
			String uploadDir="C:\\Users\\thelk\\OneDrive\\Documents\\uploads\\";
			
			InputStream f1= customerData.getProfilepic().getInputStream();
			FileOutputStream f2= new FileOutputStream((uploadDir + customerData.getProfilepic().getOriginalFilename()));
			FileCopyUtils.copy(f1,f2);
			
			Customer customer=new Customer();
			BeanUtils.copyProperties(customerData, customer);
			customer.setProfilePic(customerData.getProfilepic().getOriginalFilename());

			a = customerService.register(customer);
			// return "Customer Added Successfully";
			status.setCustid(a);
			status.setStatus(true);
			status.setMessage("oohhhh yeahhh.....");
			status.setLocalDate(LocalDateTime.now());
//			return status;

		} catch (CustomerServiceException e) {

//			status.setCustid(customer.getId());
			status.setStatus(false);
			status.setMessage(e.getMessage());
			status.setLocalDate(LocalDateTime.now());

			// return e.getMessage();

		}
		return status;

	}

//	@PostMapping("/register")
//	public RegistrationStatus register(@RequestBody Customer customer) {
//		try {
//
//			a = customerService.register(customer);
//			// return "Customer Added Successfully";
//			status.setCustid(a);
//			status.setStatus(true);
//			status.setMessage("oohhhh yeahhh.....");
//			status.setLocalDate(LocalDateTime.now());
////			return status;
//
//		} catch (CustomerServiceException e) {
//
//			status.setCustid(customer.getId());
//			status.setStatus(false);
//			status.setMessage(e.getMessage());
//			status.setLocalDate(LocalDateTime.now());
//
//			// return e.getMessage();
//
//		}
//		return status;
//
//	}

	Activation activate = new Activation();

	@GetMapping("/activate")
	public Activation activate(@RequestParam("id") int id) {
		try {
			customerService.activate(id);
			activate.setStatus(true);
			activate.setMess("successfully update");

		} catch (CustomerServiceException e) {
			activate.setStatus(false);
			activate.setMess(e.getMessage());

		}
		return activate;

	}
	
	@PostMapping("/login")
	public LoginStatus login(@RequestBody LoginData loginData){
		LoginStatus status=new LoginStatus();
		try {
			Customer cust=customerService.login(loginData.getEmail(), loginData.getPassword());
			status.setMes("succesfully login");
			status.setStatus(true);
			
			status.setName(cust.getName());
			status.setId(cust.getId());
			
		} catch (CustomerServiceException e) {
			status.setMes(e.getMessage());
			
			status.setStatus(false);
		}
		return status;
		
	}
			

}
