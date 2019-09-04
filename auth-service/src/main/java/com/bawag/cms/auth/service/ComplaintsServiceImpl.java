package com.bawag.cms.auth.service;

import com.bawag.cms.auth.exception.CmsException;
import com.bawag.cms.auth.model.Complaint;
import com.bawag.cms.auth.repository.ComplaintRepository;
import com.bawag.cms.auth.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintsServiceImpl {

    @Autowired
    private ComplaintRepository registerComplaintRepository;
    @Autowired
    private UsersRepository userRepository;


    public Complaint createNewComplaint(Complaint registerComplaint) {

        registerComplaintRepository.save(registerComplaint);

        return registerComplaint;
    }


    public Complaint updatComplaint(Complaint complaint) {

        Complaint existingComplaint = getByIncidentNumber(complaint.getIncidentNumber());

        if (existingComplaint.getIncidentNumber().equalsIgnoreCase(complaint.getIncidentNumber())) {
            existingComplaint.setId(existingComplaint.getId());
            existingComplaint.setIncidentStatus(complaint.getIncidentStatus());
            registerComplaintRepository.save(existingComplaint);
        }
        return existingComplaint;
    }

    public Complaint deleteComplaint(Complaint complaint) {

        Complaint existingComplaint = getByIncidentNumber(complaint.getIncidentNumber());
        if (null != existingComplaint) {
            if (existingComplaint.getIncidentNumber().equalsIgnoreCase(complaint.getIncidentNumber())) {
                registerComplaintRepository.delete(existingComplaint);
            }
        }
        return existingComplaint;
    }

    public Complaint getByIncidentNumber(String incidentNumber) {
        return registerComplaintRepository.findByIncidentNumber(incidentNumber);
    }


    public List<Complaint> getAllIncidents() {
        return registerComplaintRepository.findAll();
    }

    public Complaint getById(String incidentNumber) {
        return registerComplaintRepository.findById(incidentNumber)
                .orElseThrow(() -> new CmsException("Incident not found with id " + incidentNumber));
    }



}
