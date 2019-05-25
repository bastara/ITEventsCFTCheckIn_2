package com.example.iteventscftcheck_in;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Events {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("cities")
    @Expose
    private List<City> cities = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("format")
    @Expose
    private Integer format;
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("cardImage")
    @Expose
    private String cardImage;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("iconStatus")
    @Expose
    private String iconStatus;
    @SerializedName("eventFormat")
    @Expose
    private String eventFormat;
    @SerializedName("eventFormatEng")
    @Expose
    private String eventFormatEng;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFormat() {
        return format;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIconStatus() {
        return iconStatus;
    }

    public void setIconStatus(String iconStatus) {
        this.iconStatus = iconStatus;
    }

    public String getEventFormat() {
        return eventFormat;
    }

    public void setEventFormat(String eventFormat) {
        this.eventFormat = eventFormat;
    }

    public String getEventFormatEng() {
        return eventFormatEng;
    }

    public void setEventFormatEng(String eventFormatEng) {
        this.eventFormatEng = eventFormatEng;
    }
}

