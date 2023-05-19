package com.maestro.truckIT.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Ordered {
    @Id
    @SequenceGenerator(
            name = "order_id_sequence",
            sequenceName = "order_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_id_sequence"
    )
    private Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime orderDateTime;

    private String customerName;

    private String item;
    private String deliveryAddress;

    private String contactNumber;
    private String email;

    @Column(length = 64)
    private String photo;
    @Transient
    public String getPhotoImagePath(){
        if (photo == null) return null;
        return "/oredered-photo/" + id + "/" + photo;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "truck_id")
    private Truck truck;

    public Ordered() {
    }

    public Ordered(Long id, LocalDateTime orderDateTime, String customerName, String item, String deliveryAddress, String contactNumber, String email, String photo, Driver driver, Truck truck) {
        this.id = id;
        this.orderDateTime = orderDateTime;
        this.customerName = customerName;
        this.item = item;
        this.deliveryAddress = deliveryAddress;
        this.contactNumber = contactNumber;
        this.email = email;
        this.photo = photo;
        this.driver = driver;
        this.truck = truck;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    @Override
    public String toString() {
        return "Ordered{" +
                "id=" + id +
                ", orderDateTime=" + orderDateTime +
                ", customerName='" + customerName + '\'' +
                ", item='" + item + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", driver=" + driver +
                ", truck=" + truck +
                '}';
    }
}

