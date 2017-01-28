package com.hms.josip.hospitalmanagementsystem.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Josip on 15.1.2017..
 */
public class Employee extends Person implements Serializable {

    @SerializedName("Salary")
    private Float salary;

    @SerializedName("Email")
    private String email;

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
