package com.hms.josip.hospitalmanagementsystem.activities.Doctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.hms.josip.hospitalmanagementsystem.Config;
import com.hms.josip.hospitalmanagementsystem.R;
import com.hms.josip.hospitalmanagementsystem.model.Doctor;
import com.hms.josip.hospitalmanagementsystem.model.StatusOfEmployment;
import com.hms.josip.hospitalmanagementsystem.service.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddNewDoctorActivity extends AppCompatActivity {

    Doctor doctor;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText salary;
    EditText contactNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_doctor);

        doctor = new Doctor();
        contactNumber = (EditText)findViewById(R.id.editTextContactNumber);
        firstName = (EditText)findViewById(R.id.editTextFirstName);
        lastName = (EditText)findViewById(R.id.editTextLastName);
        email = (EditText)findViewById(R.id.editTextEmail);
        salary = (EditText)findViewById(R.id.editTextSalary);
    }

    public void submitNewDoctor(View view) {
        doctor.setFirstName(firstName.getText().toString());
        doctor.setLastName(lastName.getText().toString());
        doctor.setEmail(email.getText().toString());
        doctor.setContactNumber(contactNumber.getText().toString());
        doctor.setSalary(Float.parseFloat(salary.getText().toString()));

        addNewDoctor();

        Toast.makeText(getBaseContext(), "New doctor added :)", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    public void addNewDoctor() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(new Config().getURL()).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();
            Api service = retrofit.create(Api.class);
            Call<Doctor> call = service.addNewDoctor(doctor);
            call.enqueue(new Callback<Doctor>() {
                @Override
                public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                    Log.d("Success", response.toString());
                }

                @Override
                public void onFailure(Call<Doctor> call, Throwable t) {
                    Log.d("onFailure", t.toString());
                }
            });
        } catch (Exception e) {
            Log.d("onResponse", "There is an error");
            e.printStackTrace();
        }
    }

    public void onStatusClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.visiting:
                if (checked)
                    doctor.setStatus("Visiting");
                break;
            case R.id.permanent:
                if (checked)
                    doctor.setStatus("Permanent");
                break;
            case R.id.trainee:
                if (checked)
                    doctor.setStatus("Trainee");
                break;
        }
    }

    public void onSexClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.male:
                if (checked)
                    doctor.setSex("Male");
                    break;
            case R.id.female:
                if (checked)
                    doctor.setSex("Female");
                    break;
        }
    }

    public void goBack(View view) {
        this.finish();
    }

    public Doctor getDoctor(){
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
