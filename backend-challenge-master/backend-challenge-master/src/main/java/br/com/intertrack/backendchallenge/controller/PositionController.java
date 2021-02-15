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

import br.com.intertrack.backendchallenge.model.Position;
import br.com.intertrack.backendchallenge.repository.PositionRepository;

@RestController
@RequestMapping(path = "/position")
public class PositionController {

	@Autowired
	private PositionRepository positionRepository;

	public PositionController(PositionRepository positionRepository) {

		super();
		this.positionRepository = positionRepository;

	}

	@PostMapping
	public ResponseEntity<Position> save(@RequestBody Position position) {

		positionRepository.save(position);
		return new ResponseEntity<>(position, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<Position>> getAll() {

		List<Position> positions = new ArrayList<>();
		positions = positionRepository.findAll();
		return new ResponseEntity<>(positions, HttpStatus.OK);

	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Optional<Position>> getById(@PathVariable Integer id){
		
		Optional<Position> position;
		try {
			
			position = positionRepository.findById(id);
			return new ResponseEntity<Optional<Position>>(position, HttpStatus.OK);
			
		} catch (NoSuchElementException nsee) {
			
			return new ResponseEntity<Optional<Position>>(HttpStatus.NOT_FOUND);
			
		}
		
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Optional<Position>> deleteById(@PathVariable Integer id){
		
		try {
			
			positionRepository.deleteById(id);
			return new ResponseEntity<Optional<Position>>(HttpStatus.OK);
			
		} catch (NoSuchElementException nsee) {
			
			return new ResponseEntity<Optional<Position>>(HttpStatus.NOT_FOUND);
			
		}
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Position> update(@PathVariable Integer id, @RequestBody Position newPosition){
		
		return positionRepository.findById(id)
				.map(position ->{
					position.setVehicle(newPosition.getVehicle().getId());
					position.setDatetime(newPosition.getDatetime());
					position.setLatitude(newPosition.getLatitude());
					position.setLongitude(newPosition.getLongitude());
					position.setAddress(newPosition.getAddress());
					position.setIgnition(newPosition.isIgnition());
					position.setHodometro(newPosition.getHodometro());
					Position positionUpdated = positionRepository.save(position);
					return ResponseEntity.ok().body(positionUpdated);
				}).orElse(ResponseEntity.notFound().build());	
		
	}	

}
