package com.rakeshpvtltd.vectcomplaintbox;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ParentComplaintActivity extends AppCompatActivity {
    EditText e1;
    Button b1,b2;
    AwesomeValidation awesomeValidation;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_complaint);
        e1 = (EditText)findViewById(R.id.complaint_edit_text);
        e1.setGravity(Gravity.TOP);
        b1 = (Button)findViewById(R.id.save_complaint);
        b2 = (Button)findViewById(R.id.logout);


        ProgressDialog progressDialog =  new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please Wait");


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.til_title, RegexTemplate.NOT_EMPTY,R.string.invalid_complaint);
        awesomeValidation.addValidation(this,R.id.complaint_edit_text, RegexTemplate.NOT_EMPTY,R.string.invalid_complaint);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Complaint");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
                String comaplaint_text = e1.getText().toString();


                if(TextUtils.isEmpty(comaplaint_text)) {
                    progressDialog.hide();
                    Toast.makeText(getApplicationContext(), "Please Enter Your Complaint", Toast.LENGTH_LONG).show();
                }

                else
                {
                    if (awesomeValidation.validate())
                    {

                        databaseReference.push().setValue(comaplaint_text);
                        progressDialog.hide();
                        e1.setText("");
                        Toast.makeText(getApplicationContext(), "Complaint Send Successfully!", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(ParentComplaintActivity.this, ParentComplaintConformation.class);
                        startActivity(i);
                        finish();


                    }
                    else{
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), "Please Try Again With Valid Entery", Toast.LENGTH_LONG).show();
                    }
                }



            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                firebaseAuth.signOut();
                Toast.makeText(getApplicationContext(), "Logout successfully! ", Toast.LENGTH_LONG).show();
                Intent i=new Intent(ParentComplaintActivity.this, HomeActivity.class);
                startActivity(i);
                finish();

            }
        });
    }

    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(ParentComplaintActivity.this, HomeActivity.class));
        finishAffinity();
        finish();
    }
}