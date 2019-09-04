package com.bawag.cms.auth.controller;


import com.bawag.cms.auth.model.Complaint;
import com.bawag.cms.auth.model.User;
import com.bawag.cms.auth.service.ComplaintsServiceImpl;
import com.bawag.cms.auth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CmsController {

    @Autowired
    ComplaintsServiceImpl complaintsService;

    @Autowired
    UserServiceImpl userService;

    //tested working fine
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/getAllComplaints")
    public ResponseEntity getComplaints() {

        List<Complaint> complaints = complaintsService.getAllIncidents();

        return ResponseEntity.ok().body(complaints);
    }

    //tested working fine
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/complaint/{incidentNumber}")
    public ResponseEntity getIncident(@PathVariable String incidentNumber) {

        Complaint complaint = complaintsService.getByIncidentNumber(incidentNumber);

        return ResponseEntity.ok().body(complaint);
    }

    //tested working fine
    @PreAuthorize("hasAnyRole('ADMIN','SUPPORT')")
    @RequestMapping("/assigned/complaints")
    public ResponseEntity getUserRegisteredComplaints(@RequestBody User user) {
        if (null != user.getUserEmail()) {

            User assignedComplaints = userService.assignedCompliant(user.getUserEmail());
            if (null != assignedComplaints)
                return ResponseEntity.ok().body(assignedComplaints);

        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }


}