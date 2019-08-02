package com.revature.boot.controller;

import java.util.List;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.boot.beans.Hotel;
import com.revature.boot.service.HotelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * \@RestController: combines \@Controller with \@ResponseBody
 * 				-\@ResponseBody - says we don't need the ViewResolver
 * 					this is a REST API
 * \@RequestMapping: we can annotate the controller with this
 * 				-allows us to specify the URI path that this controller handles
 * 				- we can also specify additional attributes
 * 					consumes-MediaType of the requestbody
 * 					produces - MediaType of the responsebody
 * @author kyles
 *
 */

@Api(value="Hotel API")
@RestController
@RequestMapping(value="/api/v1/rooms/")
public class HotelController {

	private static final Logger log =Logger.getLogger(HotelController.class);
	
	@Autowired
	private HotelService service;
	
	/*
	 * \@RequestBody says to read the httprequest body, parse it, and marshall it from
	 * JSON to a JAVA object
	 */
	
	@ApiOperation(value="Save new hotel room",consumes="application/json")
	@PostMapping("/room")
	public ResponseEntity<Hotel> save(@Valid@RequestBody Hotel room) {//we can do responseentity for the other methods even ResponseEntity<void>
		return new ResponseEntity<Hotel>(service.save(room), HttpStatus.CREATED);
		//returns a 201 if the record is successfully created
	}
	
	/*
	 * \@PathVariable says to search the URI path for the variable name
	 */
	
	@GetMapping("/room/{id}")
	public Hotel findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@GetMapping("/")
	public List<Hotel> findAll(){
		return service.findAll();
	}
	
	@DeleteMapping("/room/{id}")
	public void delete(@PathVariable int id) {
		service.delete(id);
	}
	
	@GetMapping("/price")
	public List<Hotel> findByPriceRange(@RequestParam("lo") double low, @RequestParam("hi") double high){
		return service.findByPriceBetween(low, high);
	}
}
