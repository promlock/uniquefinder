package com.example.uniquefinder.repository;

import com.example.uniquefinder.model.RequestDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestDetailsRepository extends JpaRepository<RequestDetails, Long>{
}
