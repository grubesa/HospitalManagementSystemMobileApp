package com.hms.josip.hospitalmanagementsystem.activities.Medicine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.hms.josip.hospitalmanagementsystem.Config;
import com.hms.josip.hospitalmanagementsystem.R;
import com.hms.josip.hospitalmanagementsystem.model.Medicine;
import com.hms.josip.hospitalmanagementsystem.service.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddNewMedicineActivity extends AppCompatActivity {

    Medicine medicine;
    EditText price;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_medicine);

        medicine = new Medicine();
        price = (EditText)findViewById(R.id.editTextPrice);
        name = (EditText)findViewById(R.id.editTextMedicineName);
    }

    public void submitNewMedicine(View view) {
        medicine.setPrice(Float.parseFloat(price.getText().toString()));
        medicine.setName(name.getText().toString());

        addNewMedicine();

        Toast.makeText(getBaseContext(), "New medicine added :)", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    private void addNewMedicine() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(new Config().getURL()).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();
            Api service = retrofit.create(Api.class);
            Call<Medicine> call = service.addNewMedicine(medicine);
            call.enqueue(new Callback<Medicine>() {
                @Override
                public void onResponse(Call<Medicine> call, Response<Medicine> response) {
                    Log.d("Success", response.toString());
                }

                @Override
                public void onFailure(Call<Medicine> call, Throwable t) {
                    Log.d("onFailure", t.toString());
                }
            });
        } catch (Exception e) {
            Log.d("onResponse", "There is an error");
            e.printStackTrace();
        }
    }

    public void goBack(View view) {
        this.finish();
    }
}
