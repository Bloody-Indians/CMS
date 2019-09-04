package com.bawag.cms.auth.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Complaint")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "incident_number")
    private String incidentNumber;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "created_date")
    private String createdDate;
    @Column(name = "incident_status")
    private String incidentStatus;
    @Column(name = "customer_type")
    private String customerType;
    @Column(name = "customer_account_number")
    private String customerAccNumber;
    @Column(name = "customer_account_type")
    private String customerAccType;
    @Column(name = "customer_email")
    private String customerEmail;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "complaint_type")
    private String complaintType;
    @Column(name = "assigned_date")
    private String assignedDate;
    @Column(name = "customer_first_name")
    private String customerFirstName;
    @Column(name = "customer_last_name")
    private String customerLastName;
    @Column(name = "complaint_description")
    private String complaintDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIncidentNumber() {
        return incidentNumber;
    }

    public void setIncidentNumber(String incidentNumber) {
        this.incidentNumber = incidentNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getIncidentStatus() {
        return incidentStatus;
    }

    public void setIncidentStatus(String incidentStatus) {
        this.incidentStatus = incidentStatus;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerAccNumber() {
        return customerAccNumber;
    }

    public void setCustomerAccNumber(String customerAccNumber) {
        this.customerAccNumber = customerAccNumber;
    }

    public String getCustomerAccType() {
        return customerAccType;
    }

    public void setCustomerAccType(String customerAccType) {
        this.customerAccType = customerAccType;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getComplaintDescription() {
        return complaintDescription;
    }

    public void setComplaintDescription(String complaintDescription) {
        this.complaintDescription = complaintDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complaint complaint = (Complaint) o;
        return Objects.equals(id, complaint.id) &&
                Objects.equals(incidentNumber, complaint.incidentNumber) &&
                Objects.equals(customerName, complaint.customerName) &&
                Objects.equals(createdDate, complaint.createdDate) &&
                Objects.equals(incidentStatus, complaint.incidentStatus) &&
                Objects.equals(customerType, complaint.customerType) &&
                Objects.equals(customerAccNumber, complaint.customerAccNumber) &&
                Objects.equals(customerAccType, complaint.customerAccType) &&
                Objects.equals(customerEmail, complaint.customerEmail) &&
                Objects.equals(phoneNumber, complaint.phoneNumber) &&
                Objects.equals(complaintType, complaint.complaintType) &&
                Objects.equals(assignedDate, complaint.assignedDate) &&
                Objects.equals(customerFirstName, complaint.customerFirstName) &&
                Objects.equals(customerLastName, complaint.customerLastName) &&
                Objects.equals(complaintDescription, complaint.complaintDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, incidentNumber, customerName, createdDate, incidentStatus, customerType, customerAccNumber, customerAccType, customerEmail, phoneNumber, complaintType, assignedDate, customerFirstName, customerLastName, complaintDescription);
    }
}