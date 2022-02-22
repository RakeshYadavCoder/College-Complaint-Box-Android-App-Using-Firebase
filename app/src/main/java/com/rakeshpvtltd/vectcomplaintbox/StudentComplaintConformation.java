package com.rakeshpvtltd.vectcomplaintbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class StudentComplaintConformation extends AppCompatActivity {
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_complaint_conformation);

        b1 =(Button)findViewById(R.id.gotoComplaint);
        b2 =(Button)findViewById(R.id.logout);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentComplaintConformation.this, StudentComplaintActivity.class);
                startActivity(i);
                finish();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                firebaseAuth.signOut();
                Toast.makeText(getApplicationContext(), "Logout successfully! ", Toast.LENGTH_LONG).show();
                Intent i=new Intent(StudentComplaintConformation.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(StudentComplaintConformation.this, StudentComplaintActivity.class));
        finishAffinity();
        finish();

    }
}