package com.example.uniquefinder.repository;

import com.example.uniquefinder.model.RequestDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing and accessing request details stored in a database.
 */
@Repository
public interface RequestDetailsRepository extends JpaRepository<RequestDetails, Long>{
}
