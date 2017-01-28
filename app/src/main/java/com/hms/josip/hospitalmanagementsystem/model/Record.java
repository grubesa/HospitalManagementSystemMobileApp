package com.hms.josip.hospitalmanagementsystem.model;

import com.google.gson.annotations.SerializedName;
import com.hms.josip.hospitalmanagementsystem.JsonHelper;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Josip on 16.1.2017..
 */
public class Record implements Serializable {

    @SerializedName("ID")
    private Integer id;

    @SerializedName("Patient")
    private Patient patient;

    @SerializedName("Appointment")
    private String appointment;

    @SerializedName("Description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getAppointment() {
        JsonHelper jsonHelper = new JsonHelper();

        return jsonHelper.DeserializeDate(appointment);
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
