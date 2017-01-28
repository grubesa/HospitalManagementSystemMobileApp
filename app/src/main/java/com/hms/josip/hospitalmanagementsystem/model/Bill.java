package com.hms.josip.hospitalmanagementsystem.model;

import com.google.gson.annotations.SerializedName;
import com.hms.josip.hospitalmanagementsystem.JsonHelper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Josip on 21.1.2017..
 */
public class Bill implements Serializable {

    @SerializedName("ID")
    private Integer id;

    @SerializedName("Patient")
    private Patient patient;

    @SerializedName("Treatments")
    private List<Treatment> treatments;

    @SerializedName("ServiceCharge")
    private Float serviceCharge;

    @SerializedName("DateIssued")
    private String date;

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

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }

    public Float getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Float serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Date getDate() {
        JsonHelper jsonHelper = new JsonHelper();

        return jsonHelper.DeserializeDate(date);
    }

    public void setDate(String date) {
        this.date = date;
    }
}
