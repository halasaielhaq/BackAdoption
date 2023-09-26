package com.test.demo.Services;

import com.test.demo.Dto.AnimalDto;
import com.test.demo.models.Animal;
import com.test.demo.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService  {
    @Autowired
    public AnimalRepository animalRepository;
    public List<Animal> getAnimals() {
        return animalRepository.findAll();
    }

    public  Animal addAnimal(AnimalDto animalDto) throws IOException {

        Animal animal=new Animal();
        animal.setStatus(animalDto.getStatus());
        animal.setName(animalDto.getName());
        animal.setAge(animalDto.getAge());
        animal.setDescription(animalDto.getDescription());
        animal.setSpecies(animalDto.getSpecies());

        if (animalDto.getImageFile()!=null) {
            MultipartFile multipartFile = new MockMultipartFile("pets",animalDto.getImageFile());

                    String filePath="petsPhoto"+System.currentTimeMillis()+"."+animalDto.getTypePhoto().substring(6);

            Path targetLocation = Path.of("C:\\Users\\ZZ01DJ784\\Desktop\\ADOPT\\src\\assets\\media\\"+filePath);

            Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            animal.setPhotoFile(filePath);
        }

        return  animalRepository.save(animal);
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
