package br.pucrs.sicredi.dogs.web;

import br.pucrs.sicredi.dogs.model.DogDto;
import br.pucrs.sicredi.dogs.repo.Dog;
import br.pucrs.sicredi.dogs.service.DogsNotFoundException;
import br.pucrs.sicredi.dogs.service.DogsService;
import br.pucrs.sicredi.dogs.service.DogsServiceException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dogs")   // Ou poderiamos por o "/dogs" em cada anotação
@RequiredArgsConstructor   //do Lombok
@Setter                    //do Lombok
public class DogsController {
	@Autowired private final DogsService service;

	@GetMapping
	public ResponseEntity<List<Dog>> getDogs() {
		List<Dog> dogs;

		try {
			dogs = service.getDogs();
		} 
		catch (DogsServiceException ex) {
			return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		catch (DogsNotFoundException ex) {
			return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(dogs, HttpStatus.OK);
	}

	@PostMapping
	public void postDogs(@RequestBody DogDto dto) {
		service.add(dto);
	}

	@GetMapping("/{id}")
	public Dog getById(@PathVariable(required = true) long id) {
		return service.getDogById(id);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) long id) {
		service.delete(id);
	}
}