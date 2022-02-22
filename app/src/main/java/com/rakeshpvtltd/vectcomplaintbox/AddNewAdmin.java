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
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class AddNewAdmin extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    Button b1;
    TextView t1;

    AwesomeValidation awesomeValidation;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])"+       // 1 number
                    "(?=.*[a-z])"+         // 1 lower case
                    "(?=.*[A-Z])"+          // at least 1 uper case
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{8,}" +                // at least 8 characters
                    "$");
    Pattern pattern =
            Pattern. compile ( "[Aa123456789_]*" ) ;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_admin);
        ProgressDialog progressDialog =  new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please Wait");


        e1= (EditText)findViewById(R.id.inputEmail);
        e2= (EditText)findViewById(R.id.input_id);
        e3= (EditText)findViewById(R.id.inputPassword);
        e4= (EditText)findViewById(R.id.Re_inputPassword);

        b1=(Button)findViewById(R.id.btnLogin);

        t1=(TextView) findViewById(R.id.gotoLogin);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        awesomeValidation.addValidation(this,R.id.inputEmail, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(this,R.id.input_id, pattern,R.string.invalid_id);
        awesomeValidation.addValidation(this,R.id.inputPassword,PASSWORD_PATTERN,R.string.invalid_password_);
        awesomeValidation.addValidation(this,R.id.Re_inputPassword,R.id.inputPassword,R.string.invalid_confirm_password);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Admin");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();

                if (awesomeValidation.validate())
                {

                    String Admin_email = e1.getText().toString();
                    String Admin_id = e2.getText().toString();
                    String Admin_pass = e3.getText().toString();
                    String Admin_re_pass = e4.getText().toString();

                    Query query = FirebaseDatabase.getInstance().getReference().child("Admin").orderByChild("id").equalTo(Admin_id);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.getChildrenCount()>0){
                                progressDialog.hide();
                                Toast.makeText(getApplicationContext(), "User Already Exists", Toast.LENGTH_LONG).show();
                                e1.setText("");
                                e2.setText("");
                                e3.setText("");
                                e4.setText("");

                            }

                            else {

                                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                                firebaseAuth.createUserWithEmailAndPassword(Admin_email,Admin_re_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful())
                                        {

                                            firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task)
                                                {

                                                    if (task.isSuccessful())
                                                    {
//                                                         Log.d("myTag", "Email sent");

                                                        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                                        AdminData data = new AdminData(Admin_email,Admin_id);
                                                        databaseReference.child(currentuser).setValue(data);
                                                        progressDialog.hide();
                                                        e1.setText("");
                                                        e2.setText("");
                                                        e3.setText("");
                                                        e4.setText("");



                                                        Intent i=new Intent(AddNewAdmin.this, AdminEmailVerfication.class);
                                                        i.putExtra("EMAIL",Admin_email);
                                                        i.putExtra("NUMBER",Admin_id);
                                                        startActivity(i);
                                                        finish();
                                                    }
                                                    else {
//                                                         Log.d("myTag", "Email not sent");
                                                        progressDialog.hide();
                                                        Toast.makeText(getApplicationContext(), "Please Try Again", Toast.LENGTH_LONG).show();
                                                        e1.setText("");
                                                        e2.setText("");
                                                        e3.setText("");
                                                        e4.setText("");

                                                    }

                                                }
                                            });

                                        }
                                        else{
                                            progressDialog.hide();
                                            Toast.makeText(getApplicationContext(), "This Email is Already Use by Someone Else", Toast.LENGTH_LONG).show();

                                            e1.setText("");
                                            e2.setText("");
                                            e3.setText("");
                                            e4.setText("");

                                        }
                                    }
                                });



                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            progressDialog.hide();
                            e1.setText("");
                            e2.setText("");
                            e3.setText("");
                            e4.setText("");
                            Toast.makeText(getApplicationContext(), "Please Check Your Internet Connectivity", Toast.LENGTH_LONG).show();

                        }
                    });
                }

                else
                {
                    progressDialog.hide();
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    e4.setText("");
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Details", Toast.LENGTH_LONG).show();
                }
            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(AddNewAdmin.this, AdminLogin.class);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(AddNewAdmin.this, AdminLogin.class));
        finishAffinity();
        finish();

    }
}