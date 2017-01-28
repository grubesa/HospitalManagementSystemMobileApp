package com.hms.josip.hospitalmanagementsystem.service;

import com.hms.josip.hospitalmanagementsystem.model.Doctor;
import com.hms.josip.hospitalmanagementsystem.model.Medicine;
import com.hms.josip.hospitalmanagementsystem.model.Nurse;
import com.hms.josip.hospitalmanagementsystem.model.Patient;
import com.hms.josip.hospitalmanagementsystem.model.Receptionist;
import com.hms.josip.hospitalmanagementsystem.model.Room;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Josip on 15.1.2017..
 */
public interface Api {

    //---------------------------  Doctors  ---------------------------
    @GET("AndroidService.svc/Doctors")
    Call<List<Doctor>> listOfDoctors();

    @POST("AndroidService.svc/Doctor")
    Call<Doctor> addNewDoctor(@Body Doctor doctor);

    @DELETE("/AndroidService.svc/Doctor/{id}")
    Call<ResponseBody> deleteDoctor(@Path("id") int deleteId);



    //---------------------------  Nurses  ---------------------------
    @GET("AndroidService.svc/Nurses")
    Call<List<Nurse>> listOfNurses();

    @POST("AndroidService.svc/Nurse")
    Call<Nurse> addNewNurse(@Body Nurse nurse);

    @DELETE("/AndroidService.svc/Nurse/{id}")
    Call<ResponseBody> deleteNurse(@Path("id") int deleteId);



    //---------------------------  Receptionists  ---------------------------
    @GET("AndroidService.svc/Receptionists")
    Call<List<Receptionist>> listOfReceptionists();

    @POST("AndroidService.svc/Receptionist")
    Call<Receptionist> addNewReceptionist(@Body Receptionist receptionist);

    @DELETE("/AndroidService.svc/Receptionist/{id}")
    Call<ResponseBody> deleteReceptionist(@Path("id") int deleteId);



    //---------------------------  Patients  ---------------------------
    @GET("AndroidService.svc/Patients")
    Call<List<Patient>> listOfPatients();

    @POST("AndroidService.svc/Patient")
    Call<Patient> addNewPatient(@Body Patient patient);

    @DELETE("/AndroidService.svc/Patient/{id}")
    Call<ResponseBody> deletePatient(@Path("id") int deleteId);



    //---------------------------  Rooms  ---------------------------
    @GET("AndroidService.svc/Rooms")
    Call<List<Room>> listOfRooms();

    @POST("AndroidService.svc/Room")
    Call<Room> addNewRoom(@Body Room room);

    @DELETE("/AndroidService.svc/Room/{id}")
    Call<ResponseBody> deleteRoom(@Path("id") int deleteId);



    //---------------------------  Medicines  ---------------------------
    @GET("AndroidService.svc/Medicines")
    Call<List<Medicine>> listOfMedicines();

    @POST("AndroidService.svc/Medicine")
    Call<Medicine> addNewMedicine(@Body Medicine medicine);

    @DELETE("/AndroidService.svc/Medicine/{id}")
    Call<ResponseBody> deleteMedicine(@Path("id") int deleteId);

}
