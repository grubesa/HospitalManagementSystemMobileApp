package com.hms.josip.hospitalmanagementsystem.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Josip on 14.1.2017..
 */
public class Doctor extends Employee implements Serializable {

    @SerializedName("Status")
    private Integer status;

    @SerializedName("Attends")
    private List<Patient> attends;

    public String getStatus() {
        switch (status) {
            case 0:
                return "Visiting";
            case 1:
                return "Permanent";
            case 2:
                return "Trainee";
        }

        return "Don't have status yet.";
    }

    public void setStatus(String status) {
        switch (status) {
            case "Visiting":
                this.status = 0;
            case "Permanent":
                this.status = 1;
            case "Trainee":
                this.status = 2;
        }
    }

    public List<Patient> getAttends() {
        return attends;
    }

    public void setAttends(List<Patient> attends) {
        this.attends = attends;
    }
}
