package com.test.demo.repository;
import com.test.demo.models.AdoptionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


public interface AdoptionRequestRepository  extends JpaRepository<AdoptionRequest, Long>{



}
