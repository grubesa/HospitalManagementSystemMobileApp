package com.hms.josip.hospitalmanagementsystem.activities.Nurse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hms.josip.hospitalmanagementsystem.Config;
import com.hms.josip.hospitalmanagementsystem.R;
import com.hms.josip.hospitalmanagementsystem.model.Nurse;
import com.hms.josip.hospitalmanagementsystem.service.Api;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NurseActivity extends AppCompatActivity {

    List<Nurse> nurses;
    ListView listOfNurses;
    ArrayAdapter<String> adapter;
    boolean deleteState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse);

        nurses = new ArrayList<Nurse>();
        listOfNurses = (ListView) findViewById(R.id.listOfNurses);
        adapter = new ArrayAdapter<>(this, R.layout.check_list, R.id.code);
        listOfNurses.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listOfNurses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Nurse n = nurses.get(position);
                personDetails(n);
            }
        });

        getAllNurses();
    }

    public void personDetails(Nurse nurse) {
        Intent intent = new Intent(this, NurseDetailsActivity.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable("nurse", nurse);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void addNewNurse() {
        Intent intent = new Intent(this, AddNewNurseActivity.class);
        startActivity(intent);
    }

    public void onDeleteClick(View view) {
        if (deleteState){
            List<Nurse> deleteNurses = new ArrayList<Nurse>();
            ListView listview = (ListView) findViewById(R.id.listOfNurses);
            for (int i=0; i<adapter.getCount(); i++) {
                RelativeLayout relativeLayout = (RelativeLayout)adapter.getView(i, listview.getChildAt(i), listview);
                CheckBox checkBox = (CheckBox) relativeLayout.findViewById(R.id.checkBox);
                if (checkBox.isChecked()) {
                    deleteNurses.add(nurses.get(i));
                }
            }
            deleteNurses(deleteNurses);
        }
        deleteStateVisibility(View.INVISIBLE);
        refresh();
    }
    private void refresh() {
        adapter.clear();
        getAllNurses();
    }

    private void deleteNurses(List<Nurse> nurses) {
        for (Nurse nurse : nurses) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(new Config().getURL()).
                                addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api service = retrofit.create(Api.class);
                Call<ResponseBody> call = service.deleteNurse(nurse.getId());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("onSuccess", response.toString());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("onFailure", t.toString());
                    }
                });
            } catch (Exception e) {
                Log.d("onResponse", "There is an error");
                e.printStackTrace();
            }
        }
    }

    private void getAllNurses() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(new Config().getURL()).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();
            Api service = retrofit.create(Api.class);
            Call<List<Nurse>> call = service.listOfNurses();
            call.enqueue(new Callback<List<Nurse>>() {
                @Override
                public void onResponse(Call<List<Nurse>> call, Response<List<Nurse>> response) {
                    nurses = response.body();
                    for (Nurse nur : nurses) {
                        adapter.add(nur.getFirstName() + " " + nur.getLastName());
                    }
                }

                @Override
                public void onFailure(Call<List<Nurse>> call, Throwable t) {
                    Log.d("onFailure", t.toString());
                }
            });
        } catch (Exception e) {
            Log.d("onResponse", "There is an error");
            e.printStackTrace();
        }
    }

    public void goBack(View view) {
        if (deleteState) {
            deleteStateVisibility(View.INVISIBLE);
            deleteState = false;
        }else {
            this.finish();
        }
    }

    private void deleteStateVisibility(int visibility) {
        ListView listview = (ListView) findViewById(R.id.listOfNurses);
        for (int i=0; i<adapter.getCount(); i++) {
            RelativeLayout relativeLayout = (RelativeLayout)adapter.getView(i, listview.getChildAt(i), listview);
            CheckBox checkBox = (CheckBox) relativeLayout.findViewById(R.id.checkBox);
            checkBox.setVisibility(visibility);
        }
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        ImageView imageViewDeleteButton = (ImageView) findViewById(R.id.deleteButtonImageView);
        deleteButton.setVisibility(visibility);
        imageViewDeleteButton.setVisibility(visibility);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add) {
            addNewNurse();
            return true;
        }
        if (id == R.id.delete) {
            deleteStateVisibility(View.VISIBLE);
            deleteState = true;
            return true;
        }
        if (id == R.id.refresh) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
