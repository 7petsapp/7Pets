package com.noon.a7pets.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.noon.a7pets.R;
import com.noon.a7pets.models.ClinicReservation;
import com.noon.a7pets.networksync.CheckInternetConnection;

public class ClinicAppoinmentActivity extends AppCompatActivity {

    private TextView tVReasonOfAppointment, tVAppointmentDate, tVAppointmentTime;
    private String userId;
    private FirebaseAuth mAuth;
    private LinearLayout appointment, noAppointments;
    private Button btnCancelAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_appoinment);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        tVReasonOfAppointment = findViewById(R.id.tVReasonOfAppointment);
        tVAppointmentDate = findViewById(R.id.tVAppointmentDate);
        tVAppointmentTime = findViewById(R.id.tVAppointmentTime);

        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();

        btnCancelAppointment = findViewById(R.id.btnCancelAppointment);
        btnCancelAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAppointment();
            }
        });

        appointment = findViewById(R.id.appointment);
        noAppointments = findViewById(R.id.noAppointments);

        fetch();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Clinic Appointment");
    }

    private void cancelAppointment() {

        btnCancelAppointment.setVisibility(View.INVISIBLE);
        DatabaseReference appointmentDb = FirebaseDatabase.getInstance().getReference().child("appointments").child(userId);
        appointmentDb.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ClinicAppoinmentActivity.this, "Appointment has been canceled", Toast.LENGTH_LONG).show();
                appointment.setVisibility(View.GONE);
                noAppointments.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                btnCancelAppointment.setVisibility(View.VISIBLE);
                Toast.makeText(ClinicAppoinmentActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void fetch() {

        appointment.setVisibility(View.GONE);
        noAppointments.setVisibility(View.VISIBLE);

        DatabaseReference appointmentDb = FirebaseDatabase.getInstance().getReference().child("appointments").child(userId);
        appointmentDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    appointment.setVisibility(View.VISIBLE);
                    noAppointments.setVisibility(View.GONE);
                    ClinicReservation reservation = dataSnapshot.getValue(ClinicReservation.class);
                    tVReasonOfAppointment.setText(reservation.getReason());
                    tVAppointmentDate.setText(reservation.getDate());
                    tVAppointmentTime.setText(reservation.getTime());
                } else {
                    appointment.setVisibility(View.GONE);
                    noAppointments.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //To support reverse transition when user clicks the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
