package com.bawag.cms.auth.controller;

import com.bawag.cms.auth.model.Complaint;
import com.bawag.cms.auth.model.User;
import com.bawag.cms.auth.service.ComplaintsServiceImpl;
import com.bawag.cms.auth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    ComplaintsServiceImpl complaintsService;
    @Autowired
    UserServiceImpl userService;

    //tested working fine
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/getAllUsers")
    public ResponseEntity getAllSupportUsers() {
        List<User> supportUsers = userService.getAllSupportUsers();


        return ResponseEntity.ok().body(supportUsers);
    }

    //tested working fine
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/user/create")
    public ResponseEntity create(@RequestBody User createUser) {

        if (null != createUser.getUserEmail()) {
            if (!createUser.getUserEmail().isEmpty()) {
                User createdUser = userService.createUser(createUser);
                return ResponseEntity.ok().body(createdUser);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("user/update")
    public ResponseEntity update(@RequestBody User user) {

        if (null != user.getUserEmail()) {
            if (!user.getUserEmail().isEmpty()) {
                User updatedUser = userService.updateUser(user);
                if (null != updatedUser)
                    return ResponseEntity.ok().body(updatedUser);
            }
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/user/delete")
    public ResponseEntity deleteSupportUser(@RequestBody User user) {


        if (null != user.getUserEmail()) {
            if (!user.getUserEmail().isEmpty()) {
                User deletedUser = userService.deleteUser(user);
                if (null != deletedUser)
                    return ResponseEntity.ok().body(deletedUser);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    //tested working fine
    @PreAuthorize("hasAnyRole('ADMIN','SUPPORT')")
    @PostMapping("/complaint/update")
    public ResponseEntity update(@RequestBody Complaint complaint) {

        if (null != complaint.getIncidentNumber()) {
            if (!complaint.getIncidentNumber().isEmpty()) {
                Complaint updatedComplaint = complaintsService.updatComplaint(complaint);
                if (null != updatedComplaint)
                    return ResponseEntity.ok().body(updatedComplaint);
            }
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/complaint/delete") //delete only unassigned complaints
    public ResponseEntity delete(@RequestBody Complaint complaint) {
        if (null != complaint.getIncidentNumber()) {
            if (!complaint.getIncidentNumber().isEmpty()) {
                Complaint updatedComplaint = complaintsService.deleteComplaint(complaint);
                if (null != updatedComplaint)
                    return ResponseEntity.ok().body(updatedComplaint);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    //tested working fine
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/user/complaint/create")
    public ResponseEntity createComlaint(@RequestHeader("username") String userName, @RequestBody Complaint complaint) {
        if (null != userName) {

            User userComplaints = userService.createCompliant(userName, complaint);

            if (null != userComplaints)
                return ResponseEntity.ok().body(userComplaints);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    //tested working fine
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/assign/complaint/{incidentNumber}")
    public ResponseEntity assigningCompliant(@RequestHeader("username") String userName, @PathVariable String incidentNumber) {
        if (null != userName && null != incidentNumber) {

            User assignedComplaints = userService.assigningCompliant(userName, incidentNumber);
            if (null != assignedComplaints)
                return ResponseEntity.ok().body(assignedComplaints);

        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }


}