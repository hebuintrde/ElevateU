package com.example.navigation_drawer.Doctor;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.navigation_drawer.MyDatabase;
import com.example.navigation_drawer.R;

import java.util.concurrent.Executors;

/**
 * DoctorDetailActivity displays the details of a specific doctor.
 * It retrieves the doctor's information from the database and displays it in the UI.
 */
public class DoctorDetailActivity extends AppCompatActivity {

    // TextViews to display the doctor's name and specialization
    private TextView doctorNameTextView;
    private TextView doctorSpecializationTextView;

    /**
     * Called when the activity is first created.
     * It initializes the UI components and loads the doctor details if a doctor ID is provided.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this contains the data it most recently supplied in onSaveInstanceState(Bundle).
     *                           Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);

        // Initialize the TextViews
        doctorNameTextView = findViewById(R.id.textView_doctor_name);
        doctorSpecializationTextView = findViewById(R.id.textView_doctor_specialization);

        // Get the doctor ID from the intent extras
        int doctorId = getIntent().getIntExtra("doctor_id", -1);
        if (doctorId != -1) {
            // Load the doctor details if a valid doctor ID is provided
            loadDoctorDetails(doctorId);
        }
    }

    /**
     * Loads the details of the doctor with the specified ID from the database.
     * This operation is performed on a background thread.
     *
     * @param doctorId the ID of the doctor to be loaded.
     */
    private void loadDoctorDetails(int doctorId) {
        // Get the database instance
        MyDatabase db = MyDatabase.getDatabase(this);

        // Perform the database query on a background thread
        Executors.newSingleThreadExecutor().execute(() -> {
            // Retrieve the doctor details from the database
            Doctor doctor = db.doctorDao().getDoctorById(doctorId);

            // Update the UI on the main thread
            runOnUiThread(() -> {
                if (doctor != null) {
                    doctorNameTextView.setText(doctor.getName());
                    doctorSpecializationTextView.setText(doctor.getSpecialization());
                }
            });
        });
    }
}
