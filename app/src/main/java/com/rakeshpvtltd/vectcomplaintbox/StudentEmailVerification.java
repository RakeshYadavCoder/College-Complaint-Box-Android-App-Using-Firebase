package com.rakeshpvtltd.vectcomplaintbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StudentEmailVerification extends AppCompatActivity {
    TextView t1,t2;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_email_verification);


        t1 =(TextView)findViewById(R.id.email_id_text);
        b1=(Button)findViewById(R.id.gotoRegisterLogin);
        t2 =(TextView)findViewById(R.id.gotoLogin);

        Toast.makeText(getApplicationContext(), "Verification Email Sent to Your Email Id", Toast.LENGTH_LONG).show();

        Intent intent = getIntent();
        String S_Email = intent.getStringExtra("EMAIL");
        String S_Id = intent.getStringExtra("NUMBER");
        t1.setText("Email Id: "+S_Email+"\nStudent Id: "+S_Id);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentEmailVerification.this, StudentSignUp.class);
                startActivity(i);
                finish();
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(StudentEmailVerification.this, StudentLogin.class);
                startActivity(i);
                finish();
            }
        });

    }
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(StudentEmailVerification.this, StudentSignUp.class));
        finishAffinity();
        finish();

    }
}