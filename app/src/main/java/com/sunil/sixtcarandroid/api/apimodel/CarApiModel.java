package com.sunil.sixtcarandroid.api.apimodel;

/**
 * Created by sunil on 31-08-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarApiModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("modelIdentifier")
    @Expose
    private String modelIdentifier;
    @SerializedName("modelName")
    @Expose
    private String modelName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("make")
    @Expose
    private String make;
    @SerializedName("group")
    @Expose
    private String group;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("series")
    @Expose
    private String series;
    @SerializedName("fuelType")
    @Expose
    private String fuelType;
    @SerializedName("fuelLevel")
    @Expose
    private Double fuelLevel;
    @SerializedName("transmission")
    @Expose
    private String transmission;
    @SerializedName("licensePlate")
    @Expose
    private String licensePlate;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("innerCleanliness")
    @Expose
    private String innerCleanliness;
    @SerializedName("carImageUrl")
    @Expose
    private String carImageUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelIdentifier() {
        return modelIdentifier;
    }

    public void setModelIdentifier(String modelIdentifier) {
        this.modelIdentifier = modelIdentifier;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Double getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(Double fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getInnerCleanliness() {
        return innerCleanliness;
    }

    public void setInnerCleanliness(String innerCleanliness) {
        this.innerCleanliness = innerCleanliness;
    }

    public String getCarImageUrl() {
        return carImageUrl;
    }

    public void setCarImageUrl(String carImageUrl) {
        this.carImageUrl = carImageUrl;
    }

}