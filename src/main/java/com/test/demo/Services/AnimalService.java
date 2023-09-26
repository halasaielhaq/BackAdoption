package com.test.demo.Services;

import com.test.demo.Dto.AnimalDto;
import com.test.demo.models.Animal;
import com.test.demo.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService  {
    @Autowired
    public  AnimalRepository animalRepository;
    public List<Animal> getAnimals() {
        return animalRepository.findAll();
    }

    public  Animal addAnimal(Animal animalDto){
        return  animalRepository.save(animalDto);
    }
    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id).orElse(null);
    }
    public Animal updateAnimal(Animal updatedAnimal) {
        return animalRepository.save(updatedAnimal);
    }
    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }




}
