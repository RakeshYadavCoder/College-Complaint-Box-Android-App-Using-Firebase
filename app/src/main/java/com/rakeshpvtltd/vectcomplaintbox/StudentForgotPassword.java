package com.rakeshpvtltd.vectcomplaintbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class StudentForgotPassword extends AppCompatActivity {

    EditText e1;
    Button b1;
    TextView t1;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_forgot_password);

        e1=(EditText)findViewById(R.id.inputEmail);
        b1=(Button)findViewById(R.id.btnLogin);
        t1=(TextView) findViewById(R.id.gotoRegister);

        ProgressDialog progressDialog =  new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please Wait");

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.inputEmail, Patterns.EMAIL_ADDRESS,R.string.invalid_email);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if(awesomeValidation.validate())
                {
                    progressDialog.show();
                    String email = e1.getText().toString();
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                progressDialog.hide();
                                Toast.makeText(StudentForgotPassword.this, "Forgot Password Link Send To Your Email Id", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(StudentForgotPassword.this, StudentLogin.class);
                                startActivity(i);
                                finish();

                            }

                            else
                            {
                                progressDialog.hide();
                                Toast.makeText(StudentForgotPassword.this, "Email Id is Not Found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                else
                {
                    progressDialog.hide();
                    Toast.makeText(StudentForgotPassword.this, "Please Enter Valid Detail", Toast.LENGTH_SHORT).show();
                }


            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(StudentForgotPassword.this, StudentSignUp.class);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(StudentForgotPassword.this, StudentLogin.class));
        finishAffinity();
        finish();

    }
}