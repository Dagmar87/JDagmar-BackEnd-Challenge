package br.com.intertrack.backendchallenge.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.intertrack.backendchallenge.model.Vehicle;
import br.com.intertrack.backendchallenge.repository.VehicleRepository;

@RestController
@RequestMapping(path = "/vehicles")
public class VehicleController {
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	public VehicleController(VehicleRepository vehicleRepository) {
		
		super();
		this.vehicleRepository = vehicleRepository;
		
	}
	
	@PostMapping
	public ResponseEntity<Vehicle> save(@RequestBody Vehicle vehicle){
		
		vehicleRepository.save(vehicle);
		return new ResponseEntity<>(vehicle, HttpStatus.OK);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Vehicle>> getAll(){
		
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles = vehicleRepository.findAll();
		return new ResponseEntity<>(vehicles, HttpStatus.OK);
				
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Optional<Vehicle>> getById(@PathVariable Integer id){
		
		Optional<Vehicle> vehicle;
		try {
			
			vehicle = vehicleRepository.findById(id);
			return new ResponseEntity<Optional<Vehicle>>(vehicle, HttpStatus.OK);
			
		} catch (NoSuchElementException nsee) {
			
			return new ResponseEntity<Optional<Vehicle>>(HttpStatus.NOT_FOUND);
			
		}
		
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Optional<Vehicle>> deleteById(@PathVariable Integer id){
		
		try {
			
			vehicleRepository.deleteById(id);
			return new ResponseEntity<Optional<Vehicle>>(HttpStatus.OK);
			
		} catch (NoSuchElementException nsee) {
			
			return new ResponseEntity<Optional<Vehicle>>(HttpStatus.NOT_FOUND);
			
		}
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Vehicle> update(@PathVariable Integer id, @RequestBody Vehicle newVehicle){
		
		return vehicleRepository.findById(id)
				.map(vehicle ->{
					vehicle.setName(newVehicle.getName());
					vehicle.setDescription(newVehicle.getDescription());
					vehicle.setBrand(newVehicle.getBrand());
					vehicle.setModel(newVehicle.getModel());
					Vehicle vehicleUpdated = vehicleRepository.save(vehicle);
					return ResponseEntity.ok().body(vehicleUpdated);
				}).orElse(ResponseEntity.notFound().build());
		
	}
	
	

}
