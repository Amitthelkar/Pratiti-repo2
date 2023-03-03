package com.pratiti.controller;

import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.model.Passenger;
import com.pratiti.model.Passenger.Gender;
import com.pratiti.model.Passenger.Status;
import com.pratiti.model.Pnr;

@RestController
@CrossOrigin
public class PnrController {

	@RequestMapping("/pnrcheck")
	public Pnr checkstatus( int pnrNo){
		
		Pnr pnr =new Pnr();
		pnr.setPnrNo(pnrNo);
		pnr.setTrainNo(19368);
		pnr.setTravelDate(LocalDate.of(2023,03, 01));
		
		List<Passenger> passengers=new ArrayList<>();
		
		Passenger passenger1=new Passenger();
		passenger1.setName("Amit");
		passenger1.setGender(Gender.MAlE);
		passenger1.setStatus(Status.CONFIRMED);
		passengers.add(passenger1);
		
		Passenger passenger2=new Passenger();
		passenger2.setName("Rohan");
		passenger2.setGender(Gender.MAlE);
		passenger2.setStatus(Status.CONFIRMED);
		passengers.add(passenger2);
		
		pnr.setPassengers(passengers);
		
//		map.put("pnrlist", pnr);
		return pnr;
	}
}
