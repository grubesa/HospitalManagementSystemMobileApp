package com.hms.josip.hospitalmanagementsystem.activities.Nurse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.hms.josip.hospitalmanagementsystem.Config;
import com.hms.josip.hospitalmanagementsystem.R;
import com.hms.josip.hospitalmanagementsystem.model.Nurse;
import com.hms.josip.hospitalmanagementsystem.service.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddNewNurseActivity extends AppCompatActivity {

    Nurse nurse;
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText salary;
    EditText contactNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_nurse);

        nurse = new Nurse();
        contactNumber = (EditText) findViewById(R.id.editTextContactNumber);
        firstName = (EditText)findViewById(R.id.editTextFirstName);
        lastName = (EditText)findViewById(R.id.editTextLastName);
        email = (EditText)findViewById(R.id.editTextEmail);
        salary = (EditText)findViewById(R.id.editTextSalary);
    }

    public void submitNewNurse(View view) {
        nurse.setFirstName(firstName.getText().toString());
        nurse.setLastName(lastName.getText().toString());
        nurse.setEmail(email.getText().toString());
        nurse.setContactNumber(contactNumber.getText().toString());
        nurse.setSalary(Float.parseFloat(salary.getText().toString()));

        addNewNurse();

        Toast.makeText(getBaseContext(), "New nurse added :)", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    private void addNewNurse() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(new Config().getURL()).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();
            Api service = retrofit.create(Api.class);
            Call<Nurse> call = service.addNewNurse(nurse);
            call.enqueue(new Callback<Nurse>() {
                @Override
                public void onResponse(Call<Nurse> call, Response<Nurse> response) {
                    Log.d("Success", response.message());
                }

                @Override
                public void onFailure(Call<Nurse> call, Throwable t) {
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
                    nurse.setSex("Male");
                break;
            case R.id.female:
                if (checked)
                    nurse.setSex("Female");
                break;
        }
    }

    public void goBack(View view) {
        this.finish();
    }
}
