package com.hms.josip.hospitalmanagementsystem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.hms.josip.hospitalmanagementsystem.R;
import com.hms.josip.hospitalmanagementsystem.activities.Medicine.MedicineActivity;
import com.hms.josip.hospitalmanagementsystem.activities.Patient.PatientActivity;
import com.hms.josip.hospitalmanagementsystem.activities.Room.RoomActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickEmployees(View view) {
        Intent intent = new Intent(this, EmployeesActivity.class);
        startActivity(intent);
    }

    public void onClickPatients(View view) {
        Intent intent = new Intent(this, PatientActivity.class);
        startActivity(intent);
    }

    public void onClickRooms(View view) {
        Intent intent = new Intent(this, RoomActivity.class);
        startActivity(intent);
    }

    public void onClickMedicines(View view) {
        Intent intent = new Intent(this, MedicineActivity.class);
        startActivity(intent);
    }

    public void goExit(View view) {
        this.finish();
    }

}
