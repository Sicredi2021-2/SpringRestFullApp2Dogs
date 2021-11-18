package br.pucrs.sicredi.dogs.service;

import br.pucrs.sicredi.dogs.model.DogDto;
import br.pucrs.sicredi.dogs.repo.Dog;
import br.pucrs.sicredi.dogs.repo.MockDogProvider;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor    // anotação do Lombok
@Setter                     // anotação do Lombok
public class DogsService {
    @Autowired private final MockDogProvider mockDogProvider;

    public void add(DogDto dto) {
        mockDogProvider.add(dto);
    }

    public void delete(long id) {
        mockDogProvider.delete(id);
    }

    public List<Dog> getDogs()  {
    	if (mockDogProvider == null)
    		throw new DogsServiceException();
    	else {
    		List<Dog> res = mockDogProvider.getDogs();
    		if (res.isEmpty())
    			throw new DogsNotFoundException(); 
    		else 
    			return res;
    	}
    }

    public Dog getDogById(long id) {
        return mockDogProvider.findDogById(id);
    }
}