package com.maestro.truckIT.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Truck {
    @Id
    @SequenceGenerator(
            name = "truck_id_sequence",
            sequenceName = "truck_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "truck_id_sequence"
    )
    private Long id;
    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private String mileage;
    private String gearBox;
    private String fuelType;
    private int maxCapacity;

    @OneToMany(mappedBy = "truck", cascade = CascadeType.REMOVE)
    //private Set<Ordered> order = new HashSet<>();
    private List<Ordered> order = new ArrayList<>();

    public Truck() {
    }

    public Truck(Long id, String make, String model, int year, String licensePlate, String mileage, String gearBox, String fuelType, int maxCapacity, List<Ordered> order) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.mileage = mileage;
        this.gearBox = gearBox;
        this.fuelType = fuelType;
        this.maxCapacity = maxCapacity;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public List<Ordered> getOrder() {
        return order;
    }

    public void setOrder(List<Ordered> order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", licensePlate='" + licensePlate + '\'' +
                ", mileage='" + mileage + '\'' +
                ", gearBox='" + gearBox + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", order=" + order +
                '}';
    }
}
