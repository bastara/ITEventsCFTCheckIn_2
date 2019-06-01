package com.example.iteventscftcheck_in.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Members {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("addition")
    @Expose
    private String addition;
    @SerializedName("registeredDate")
    @Expose
    private String registeredDate;
    @SerializedName("isVisited")
    @Expose
    private Boolean isVisited;
    @SerializedName("agreedByManager")
    @Expose
    private String agreedByManager;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("patronymic")
    @Expose
    private String patronymic;
    @SerializedName("email")
    @Expose
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Boolean getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(Boolean isVisited) {
        this.isVisited = isVisited;
    }

    public String getAgreedByManager() {
        return agreedByManager;
    }

    public void setAgreedByManager(String agreedByManager) {
        this.agreedByManager = agreedByManager;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
