package com.bawag.cms.auth.repository;


import com.bawag.cms.auth.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, String> {

    public Complaint findByIncidentNumber(String incidentNumber);
}
