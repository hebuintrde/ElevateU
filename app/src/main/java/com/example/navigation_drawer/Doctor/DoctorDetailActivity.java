package com.example.navigation_drawer.Doctor;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.navigation_drawer.MyDatabase;
import com.example.navigation_drawer.R;

import java.util.concurrent.Executors;

public class DoctorDetailActivity extends AppCompatActivity {

    private TextView doctorNameTextView;
    private TextView doctorSpecializationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        doctorNameTextView = findViewById(R.id.textView_doctor_name);
        doctorSpecializationTextView = findViewById(R.id.textView_doctor_specialization);

        int doctorId = getIntent().getIntExtra("doctor_id", -1);
        if (doctorId != -1) {
            loadDoctorDetails(doctorId);
        }
    }

    private void loadDoctorDetails(int doctorId) {
        MyDatabase db = MyDatabase.getDatabase(this);

        Executors.newSingleThreadExecutor().execute(() -> {
            Doctor doctor = db.doctorDao().getDoctorById(doctorId);
            runOnUiThread(() -> {
                if (doctor != null) {
                    doctorNameTextView.setText(doctor.getName());
                    doctorSpecializationTextView.setText(doctor.getSpecialization());
                }
            });
        });
    }
}
