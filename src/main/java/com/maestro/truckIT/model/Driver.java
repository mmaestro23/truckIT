package com.maestro.truckIT.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
public class Driver {

    @Id
    @SequenceGenerator(
            name = "driver_id_sequence",
            sequenceName = "driver_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "driver_id_sequence"
    )
    private Long id;

    private String name;

    private String phoneNumber;

    private String email;

    private String licenseNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date licenseExpiryDate;

    private String address;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Ordered> order;

    public Driver() {
    }

    public Driver(Long id, String name, String phoneNumber, String email, String licenseNumber, Date licenseExpiryDate, String address, List<Ordered> order) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.licenseNumber = licenseNumber;
        this.licenseExpiryDate = licenseExpiryDate;
        this.address = address;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Date getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public void setLicenseExpiryDate(Date licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Ordered> getOrder() {
        return order;
    }

    public void setOrder(List<Ordered> order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", licenseExpiryDate=" + licenseExpiryDate +
                ", address='" + address + '\'' +
                ", order=" + order +
                '}';
    }
}

