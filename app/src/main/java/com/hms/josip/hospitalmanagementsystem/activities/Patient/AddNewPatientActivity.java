package com.hms.josip.hospitalmanagementsystem.activities.Patient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.hms.josip.hospitalmanagementsystem.Config;
import com.hms.josip.hospitalmanagementsystem.R;
import com.hms.josip.hospitalmanagementsystem.model.Patient;
import com.hms.josip.hospitalmanagementsystem.service.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddNewPatientActivity extends AppCompatActivity {

    Patient patient;
    EditText firstName;
    EditText lastName;
    EditText contactNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_patient);

        patient = new Patient();
        contactNumber = (EditText)findViewById(R.id.editTextContactNumber);
        firstName = (EditText)findViewById(R.id.editTextFirstName);
        lastName = (EditText)findViewById(R.id.editTextLastName);
    }

    public void submitNewPatient(View view) {
        patient.setFirstName(firstName.getText().toString());
        patient.setLastName(lastName.getText().toString());
        patient.setContactNumber(contactNumber.getText().toString());

        addNewPatient();

        Toast.makeText(getBaseContext(), "New patient added :)", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    private void addNewPatient() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(new Config().getURL()).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();
            Api service = retrofit.create(Api.class);
            Call<Patient> call = service.addNewPatient(patient);
            call.enqueue(new Callback<Patient>() {
                @Override
                public void onResponse(Call<Patient> call, Response<Patient> response) {
                    Log.d("Success", response.toString());
                }

                @Override
                public void onFailure(Call<Patient> call, Throwable t) {
                    Log.d("onFailure", t.toString());
                }
            });
        } catch (Exception e) {
            Log.d("onResponse", "There is an error");
            e.printStackTrace();
        }
    }

    public void onSexClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.male:
                if (checked)
                    patient.setSex("0");
                break;
            case R.id.female:
                if (checked)
                    patient.setSex("1");
                break;
        }
    }

    public void goBack(View view) {
        this.finish();
    }
}
