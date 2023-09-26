package com.test.demo.Services;

import com.test.demo.models.AdoptionRequest;
import com.test.demo.repository.AdoptionRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdoptionRequestService  {
    @Autowired
    private AdoptionRequestRepository adoptionRequestRepository;
    public AdoptionRequest createAdoptionRequest(AdoptionRequest adoptionRequest) {
        return adoptionRequestRepository.save(adoptionRequest);
    }
    public AdoptionRequest getAdoptionRequestById(Long id) {
        return adoptionRequestRepository.findById(id).orElse(null);
    }
    public AdoptionRequest updateAdoptionRequest(AdoptionRequest updatedAdoptionRequest) {
        return adoptionRequestRepository.save(updatedAdoptionRequest);
    }
    public void deleteAdoptionRequestById(Long id) {
        adoptionRequestRepository.deleteById(id);
    }


}
