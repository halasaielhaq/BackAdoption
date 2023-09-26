package com.test.demo.Controller;
import com.test.demo.Dto.AnimalDto;
import com.test.demo.Services.AnimalService;
import com.test.demo.models.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/animals")
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping("/listOfAnimals")
    public List<Animal> getAnimals() {
        return animalService.getAnimals();
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable Long id) {
        return animalService.getAnimalById(id);
    }

    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        return animalService.updateAnimal(animal);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Animal> addAnimal(@RequestBody @Valid Animal animalDto) {
        Animal newAnimal = animalService.addAnimal(animalDto);
        return new ResponseEntity<>(newAnimal, HttpStatus.CREATED);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getAnimalImage(@PathVariable Long id) {
        Animal animal = animalService.getAnimalById(id);
        if (animal != null && animal.getPhoto() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(animal.getImageMimeType()));
            return new ResponseEntity<>(animal.getPhoto(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
