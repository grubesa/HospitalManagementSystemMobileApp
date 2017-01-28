package com.hms.josip.hospitalmanagementsystem.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hms.josip.hospitalmanagementsystem.R;
import com.hms.josip.hospitalmanagementsystem.activities.Doctor.DoctorActivity;
import com.hms.josip.hospitalmanagementsystem.activities.Nurse.NurseActivity;
import com.hms.josip.hospitalmanagementsystem.activities.Receptionist.ReceptionistActivity;

public class EmployeesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);
    }

    public void goBack(View view) {
        this.finish();
    }

    public void onClickDoctors(View view) {
        Intent intent = new Intent(this, DoctorActivity.class);
        startActivity(intent);
    }

    public void onClickNurses(View view) {
        Intent intent = new Intent(this, NurseActivity.class);
        startActivity(intent);
    }

    public void onClickReceptionists(View view) {
        Intent intent = new Intent(this, ReceptionistActivity.class);
        startActivity(intent);
    }
}
