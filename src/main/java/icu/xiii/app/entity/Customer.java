package icu.xiii.app.entity;

import icu.xiii.app.dto.customer.CustomerDtoRequest;

public class Customer {
    private Long id;
    private String fullName;
    private String email;
    private String socialSecurityNumber;

    public Customer() {
    }

    public Customer(Long id, String fullName, String email, String socialSecurityNumber) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Customer(CustomerDtoRequest request) {
        this(request.id(), request.fullName(), request.email(), request.socialSecurityNumber());
    }

    public Customer(Long id, CustomerDtoRequest request) {
        this(id, request.fullName(), request.email(), request.socialSecurityNumber());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                '}';
    }
}
