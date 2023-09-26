package com.test.demo.Controller;
import com.test.demo.Services.AdoptionRequestService;
import com.test.demo.models.AdoptionRequest;
import com.test.demo.repository.AdoptionRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adoption-requests")
public class AdoptionRequestController {

    @Autowired
    private AdoptionRequestService adoptionRequestService;
    private final AdoptionRequestRepository adoptionRequestRepository;
    private final JavaMailSender javaMailSender;

    @GetMapping("/{id}")
    public AdoptionRequest getAdoptionRequestById(@PathVariable Long id) {
        return adoptionRequestService.getAdoptionRequestById(id);
    }

    @PutMapping("/{id}")
    public AdoptionRequest updateAdoptionRequest(@RequestBody AdoptionRequest adoptionRequest) {
        return adoptionRequestService.updateAdoptionRequest(adoptionRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteAdoptionRequest(@PathVariable Long id) {
        adoptionRequestService.deleteAdoptionRequestById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<String> addAdoptionRequest(@RequestBody AdoptionRequest adoptionRequest) {
        try {
            AdoptionRequest savedRequest = adoptionRequestRepository.save(adoptionRequest);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("votre_email@gmail.com");
            mailMessage.setTo("saielhaqhala@gmail.com");
            mailMessage.setSubject("Confirmation de soumission de formulaire");
            mailMessage.setText("Votre formulaire a été soumis avec succès.");

            javaMailSender.send(mailMessage);

            return ResponseEntity.status(HttpStatus.CREATED).body("AdoptionRequest enregistrée avec l'ID : " + savedRequest.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement de l'AdoptionRequest.");
        }
    }
}
