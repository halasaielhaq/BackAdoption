package com.test.demo.Controller;
import com.test.demo.Dto.AnimalDto;
import com.test.demo.Services.AnimalService;
import com.test.demo.models.Animal;
import com.test.demo.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/animals")
public class AnimalController {
    private final AnimalRepository animalRepository;
    private final AnimalService animalService;

    @GetMapping("/allAnimals")
    public ResponseEntity<List<Animal>> getAnimals() {

        return ResponseEntity.ok(animalRepository.findAll());
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
    public ResponseEntity<?> addAnimal(@RequestBody AnimalDto animalDto) throws IOException {
         Animal newAnimal = animalService.addAnimal(animalDto);
        return ResponseEntity.ok(animalDto);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<String> getAnimalImage(@PathVariable Long id) {
        Animal animal = animalService.getAnimalById(id);
        if (animal != null && animal.getPhotoFile() != null) {
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<>(animal.getPhotoFile(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
