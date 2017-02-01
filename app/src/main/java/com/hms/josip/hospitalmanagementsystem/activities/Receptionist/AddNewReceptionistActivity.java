package com.hms.josip.hospitalmanagementsystem.activities.Receptionist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.hms.josip.hospitalmanagementsystem.Config;
import com.hms.josip.hospitalmanagementsystem.R;
import com.hms.josip.hospitalmanagementsystem.model.Receptionist;
import com.hms.josip.hospitalmanagementsystem.service.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddNewReceptionistActivity extends AppCompatActivity {

    Receptionist receptionist;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText salary;
    EditText contactNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_receptionist);

        receptionist = new Receptionist();
        contactNumber = (EditText) findViewById(R.id.editTextContactNumber);
        firstName = (EditText)findViewById(R.id.editTextFirstName);
        lastName = (EditText)findViewById(R.id.editTextLastName);
        email = (EditText)findViewById(R.id.editTextEmail);
        salary = (EditText)findViewById(R.id.editTextSalary);
    }

    public void submitNewReceptionist(View view) {
        receptionist.setFirstName(firstName.getText().toString());
        receptionist.setLastName(lastName.getText().toString());
        receptionist.setEmail(email.getText().toString());
        receptionist.setContactNumber(contactNumber.getText().toString());
        receptionist.setSalary(Float.parseFloat(salary.getText().toString()));

        addNewReceptionist();

        Toast.makeText(getBaseContext(), "New receptionist added :)", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    private void addNewReceptionist() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(new Config().getURL()).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();
            Api service = retrofit.create(Api.class);
            Call<Receptionist> call = service.addNewReceptionist(receptionist);
            call.enqueue(new Callback<Receptionist>() {
                @Override
                public void onResponse(Call<Receptionist> call, Response<Receptionist> response) {
                    Log.d("Success", response.message());
                }

                @Override
                public void onFailure(Call<Receptionist> call, Throwable t) {
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
                    receptionist.setSex("Male");
                break;
            case R.id.female:
                if (checked)
                    receptionist.setSex("Female");
                break;
        }
    }

    public void goBack(View view) {
        this.finish();
    }
}
