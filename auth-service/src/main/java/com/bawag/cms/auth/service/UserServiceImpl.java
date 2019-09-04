package com.bawag.cms.auth.service;


import com.bawag.cms.auth.exception.CmsException;
import com.bawag.cms.auth.model.Complaint;
import com.bawag.cms.auth.model.User;
import com.bawag.cms.auth.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class UserServiceImpl {

    @Autowired
    private UsersRepository supportUserRepository;

    @Autowired
    ComplaintsServiceImpl complaintsStorageService;

    Date date = new Timestamp(System.currentTimeMillis());

    public User userInfo(User updateUser) {

        User existingUser = findByUserEmail(updateUser.getUserEmail());
        if (null != existingUser) {
            if (existingUser.getUserEmail().equalsIgnoreCase(updateUser.getUserEmail())) {
                return existingUser;
            } else {
                return existingUser;
            }
        }
        return existingUser;
    }

    public User createUser(User createUser) {

        User existingUser = findByUserEmail(createUser.getUserEmail());
        if (null != existingUser) {
            if (existingUser.getUserEmail().equalsIgnoreCase(createUser.getUserEmail())) {
                existingUser.setLastModifiedDate(date);
                supportUserRepository.save(existingUser);
                return existingUser;
            } else {

                createUser.setLastModifiedDate(date);
                supportUserRepository.save(createUser);

                return createUser;
            }
        } else {

            createUser.setCreatedDate(date);
            supportUserRepository.save(createUser);
            return createUser;
        }

    }

    public User updateUser(User updateUser) {

        User existingUser = findByUserEmail(updateUser.getUserEmail());
        if (null != existingUser) {
            if (existingUser.getUserEmail().equalsIgnoreCase(updateUser.getUserEmail())) {
                existingUser.setId(existingUser.getId());
                existingUser.setFirstName(updateUser.getFirstName());
                existingUser.setUserEmail(updateUser.getUserEmail());
                existingUser.setLastName(updateUser.getLastName());
                existingUser.setStatus(updateUser.getStatus());
                existingUser.setUserAuthorities(updateUser.getUserAuthorities());
                existingUser.setLastModifiedDate(date);
                supportUserRepository.save(existingUser);
                return updateUser;
            } else {
                return existingUser;
            }
        }
        return existingUser;
    }

    public User deleteUser(User user) {
        User existingUser = findByUserEmail(user.getUserEmail());
        if (null != existingUser) {
            if (existingUser.getUserEmail().equalsIgnoreCase(user.getUserEmail())) {
                supportUserRepository.delete(existingUser);
                return existingUser;
            } else {
                return existingUser;
            }
        }
        return existingUser;
    }


    public List<User> getAllSupportUsers() {
        return supportUserRepository.findAll();
    }


    public User findByUserName(String userName) {
        Optional<User> optionalUser = supportUserRepository.findByFirstName(userName);
        if (null != optionalUser) {
            if (null != optionalUser.get()) {
                if (optionalUser.get().getFirstName().equalsIgnoreCase(userName)) {
                    return optionalUser.get();
                }

            } else {
                System.out.println("method findByUserName user not found :" + optionalUser);
                return null;
            }
        }
        return optionalUser.get();
    }


    public User findByUserEmail(String userEmail) {
        Optional<User> optionalUser = supportUserRepository.findByUserEmail(userEmail);
        if (optionalUser.isPresent()) {
            if (null != optionalUser.get()) {
                return optionalUser.get();
            } else {
                System.out.println("method findByUserName userEmail not found :" + optionalUser);
                return null;
            }
        }
        return null;

    }

    public User getById(String id) {
        return supportUserRepository.findById(id)
                .orElseThrow(() -> new CmsException(" user not found with id " + id));
    }

    public User createCompliant(String userName, Complaint regComplaint) {
        Complaint complaint = null;
        User existingUser = findByUserEmail(userName);
        String incidentNumber = creatNewInc();
        complaint = complaintsStorageService.getByIncidentNumber(incidentNumber);
        complaint = new Complaint();
        complaint.setIncidentNumber(incidentNumber);
        complaint.setCustomerName(regComplaint.getCustomerName());
        complaint.setCustomerFirstName(regComplaint.getCustomerFirstName());
        complaint.setCustomerLastName(regComplaint.getCustomerLastName());
        complaint.setCreatedDate(date.toString());
        complaint.setIncidentStatus(regComplaint.getIncidentStatus());
        complaint.setCustomerType(regComplaint.getCustomerType());
        complaint.setCustomerAccNumber(regComplaint.getCustomerAccNumber());
        complaint.setCustomerAccType(regComplaint.getCustomerAccType());
        complaint.setCustomerEmail(regComplaint.getCustomerEmail());
        complaint.setPhoneNumber(regComplaint.getPhoneNumber());
        complaint.setComplaintType(regComplaint.getComplaintType());
        complaint.setComplaintDescription(regComplaint.getComplaintDescription());
       /* if (null != complaint)
            if (null != complaint.getIncidentNumber())
                if (complaint.getIncidentNumber().equals(incidentNumber)) {
                    String newIncidentNumber = creatNewInc();
                    complaint.setIncidentNumber(newIncidentNumber);
                }*/

        Set<Complaint> userComplaints = existingUser.getComplaints();
        if (null != existingUser) {
            if (null != existingUser.getUserEmail()) {
                userComplaints.add(complaint);
                existingUser.setComplaints(userComplaints);
                existingUser.setId(existingUser.getId());
                existingUser.setCreatedDate(date);
                existingUser.setLastModifiedDate(date);
                supportUserRepository.save(existingUser);
                return existingUser;
            }
        }
        return existingUser;
    }

    public String creatNewInc() {
        Random random = new Random();
        int x = random.nextInt(900) + 100;
        String newIncidentNumber = "INC" + x;
        return newIncidentNumber;
    }

    public User assigningCompliant(String userName, String incidentNumber) {

        User user = findByUserEmail(userName);
        Complaint existingComplaint = complaintsStorageService.getByIncidentNumber(incidentNumber);
        Set<Complaint> existingComplaints = new HashSet<>();
        if (null != existingComplaint && null != user) {
            if (null != existingComplaint.getIncidentNumber() && null != user.getUserEmail()) {
                if (null != user.getComplaints()) ;
                {

                    Complaint complaint = new Complaint();
                    complaint.setIncidentStatus(existingComplaint.getIncidentStatus());
                    complaint.setIncidentNumber(existingComplaint.getIncidentNumber());
                    complaint.setComplaintDescription(existingComplaint.getComplaintDescription());
                    complaint.setAssignedDate(date.toString());
                    existingComplaints.add(complaint);
                    user.setId(user.getId());
                    user.setComplaints(existingComplaints);
                    supportUserRepository.save(user);
                }
                return user;
            }
        }
        return user;
    }

    public User assignedCompliant(String userName) {

        User user = findByUserEmail(userName);

        if (null != user) {
            if (null != user.getUserEmail()) {
                return user;
            }
        }
        return user;
    }
}
