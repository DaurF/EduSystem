package kz.daur.edusystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnUpdate;

    Switch sw_g = null;
    EditText etFullName, etAge, etCity, etCountry, etDateOfBirth, etJob, etEdu, etBio;
    Boolean gender;
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btnUpdate = (ImageButton) findViewById(R.id.btnUpdate);

        sw_g = (Switch) findViewById(R.id.sw_g);

        etFullName = (EditText) findViewById(R.id.etFullName);
        etAge = (EditText) findViewById(R.id.etAge);
        etCity = (EditText) findViewById(R.id.etCity);
        etCountry = (EditText) findViewById(R.id.etCountry);
        etDateOfBirth = (EditText) findViewById(R.id.etDateOfBirth);
        etJob = (EditText) findViewById(R.id.etJob);
        etEdu = (EditText) findViewById(R.id.etEdu);
        etBio = (EditText) findViewById(R.id.etBio);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView etFullName = (TextView) findViewById(R.id.etFullName);
        final TextView etAge = (TextView) findViewById(R.id.etAge);

        btnUpdate.setOnClickListener(this);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String fullName = userProfile.fullName;
                    String age = userProfile.age;
                    String city = userProfile.city;
                    String country = userProfile.country;
                    String dateOfBirth = userProfile.dateOfBirth;
                    String job = userProfile.job;
                    String edu = userProfile.edu;
                    String biography = userProfile.biography;


                    etFullName.setText(fullName);
                    etAge.setText(age);
                    etCity.setText(city);
                    etCountry.setText(country);
                    etDateOfBirth.setText(dateOfBirth);
                    etJob.setText(job);
                    etEdu.setText(edu);
                    etBio.setText(biography);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EditProfile.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_edit:
                startActivity(new Intent(EditProfile.this, EditProfile.class));
                break;
            case R.id.btnUpdate:
                updateData();
                finish();
        }
    }



    private void updateData() {
        String fullName = etFullName.getText().toString().trim();
        String age = etAge.getText().toString().trim();
        String city = etCity.getText().toString().trim();
        String country = etCountry.getText().toString().trim();
        String dateOfBirth = etDateOfBirth.getText().toString().trim();
        String job = etJob.getText().toString().trim();
        String edu = etEdu.getText().toString().trim();
        String bio = etBio.getText().toString().trim();
        Boolean gender = sw_g.isChecked();

        if (fullName.isEmpty()) {
            etFullName.setError("Full name is required!");
            etFullName.requestFocus();
            return;
        }

        if (age.isEmpty()) {
            etAge.setError("Age is required!");
            etAge.requestFocus();
            return;
        }

        if (city.isEmpty()) {
            etCity.setError("City name is required!");
            etCity.requestFocus();
            return;
        }

        if (country.isEmpty()) {
            etCountry.setError("Country name is required!");
            etCountry.requestFocus();
            return;
        }

        if (dateOfBirth.isEmpty()) {
            etDateOfBirth.setError("Date of birth is required!");
            etDateOfBirth.requestFocus();
            return;
        }

        if (job.isEmpty()) {
            etJob.setError("Job name is required!");
            etJob.requestFocus();
            return;
        }

        if (edu.isEmpty()) {
            etEdu.setError("Education name is required!");
            etEdu.requestFocus();
            return;
        }

        if (bio.isEmpty()) {
            etCity.setError("Biography is required!");
            etCity.requestFocus();
            return;
        }

        User user = new User(fullName, age, gender, city, country, dateOfBirth, job, edu, bio);
        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(EditProfile.this, "Profile Info has been updated successfully!", Toast.LENGTH_LONG).show();

                    //
                } else {
                    Toast.makeText(EditProfile.this, "Failed to Edit! Try again next time!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}