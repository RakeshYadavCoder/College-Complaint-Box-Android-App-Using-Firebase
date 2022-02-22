package com.rakeshpvtltd.vectcomplaintbox;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {

    ImageView admin_view, teacher_view,student_view,parent_view;
    TextView t1;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        admin_view= (ImageView)findViewById(R.id.admin_imageview);
        teacher_view= (ImageView)findViewById(R.id.tacher_imageview);
        student_view= (ImageView)findViewById(R.id.student_imageview);
        parent_view= (ImageView)findViewById(R.id.parent_imageview);
        t1 = (TextView)findViewById(R.id.testing);
        ProgressDialog progressDialog =  new ProgressDialog(HomeActivity.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please Wait");


        admin_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressDialog.show();
                DatabaseReference databaseReference= firebaseDatabase.getReference("Admin");
                if(firebaseAuth.getCurrentUser()==null)
                {
                    progressDialog.hide();
                    Intent intent = new Intent(HomeActivity.this, AdminLogin.class);
                    startActivity(intent);
                }

                else
                {

                    String currentuser = firebaseAuth.getInstance().getCurrentUser().getUid();

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String EMAIL= firebaseAuth.getCurrentUser().getEmail();

                            if (snapshot.hasChild(currentuser)) {
                                String email = snapshot.child(currentuser).child("email").getValue().toString();
                                if (EMAIL.equals(email)){

                                    if (firebaseAuth.getCurrentUser().isEmailVerified()){
                                        progressDialog.hide();
                                        Intent intent = new Intent(HomeActivity.this, AdminPanel.class);
                                        startActivity(intent);

                                    }
                                    else
                                    {
                                        Intent intent = new Intent(HomeActivity.this, AdminLogin.class);
                                        startActivity(intent);

                                    }
                                }
                                else
                                {
                                    Intent intent = new Intent(HomeActivity.this, AdminLogin.class);
                                    startActivity(intent);

                                }

                            }

                            else {
                                progressDialog.hide();
                                Intent intent = new Intent(HomeActivity.this, AdminLogin.class);
                                startActivity(intent);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error)
                        {
                            progressDialog.hide();
                            Toast.makeText(HomeActivity.this, "Please Check Your Internet Connectivity", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        student_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                DatabaseReference databaseReference= firebaseDatabase.getReference("Student");
                if(firebaseAuth.getCurrentUser()==null)
                {
                    progressDialog.hide();
                    Intent intent = new Intent(HomeActivity.this, StudentLogin.class);
                    startActivity(intent);
                }

                else
                {

                    String currentuser = firebaseAuth.getInstance().getCurrentUser().getUid();

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String EMAIL= firebaseAuth.getCurrentUser().getEmail();

                            if (snapshot.hasChild(currentuser)) {
                                String email = snapshot.child(currentuser).child("email").getValue().toString();
                                if (EMAIL.equals(email)){

                                    if (firebaseAuth.getCurrentUser().isEmailVerified()){
                                        progressDialog.hide();
                                        Intent intent = new Intent(HomeActivity.this, StudentComplaintActivity.class);
                                        startActivity(intent);

                                    }
                                    else
                                    {
                                        Intent intent = new Intent(HomeActivity.this, StudentLogin.class);
                                        startActivity(intent);

                                    }
                                }
                                else
                                {
                                    Intent intent = new Intent(HomeActivity.this, StudentLogin.class);
                                    startActivity(intent);

                                }

                            }

                            else {
                                progressDialog.hide();
                                Intent intent = new Intent(HomeActivity.this, StudentLogin.class);
                                startActivity(intent);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error)
                        {
                            progressDialog.hide();
                            Toast.makeText(HomeActivity.this, "Please Check Your Internet Connectivity", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        teacher_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                DatabaseReference databaseReference= firebaseDatabase.getReference("Teacher");
                if(firebaseAuth.getCurrentUser()==null)
                {
                    progressDialog.hide();
                    Intent intent = new Intent(HomeActivity.this, TeacherLogin.class);
                    startActivity(intent);
                }

                else
                {

                    String currentuser = firebaseAuth.getInstance().getCurrentUser().getUid();

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String EMAIL= firebaseAuth.getCurrentUser().getEmail();

                            if (snapshot.hasChild(currentuser)) {
                                String email = snapshot.child(currentuser).child("email").getValue().toString();
                                if (EMAIL.equals(email)){

                                    if (firebaseAuth.getCurrentUser().isEmailVerified()){
                                        progressDialog.hide();
                                        Intent intent = new Intent(HomeActivity.this, TeacherComplaintActivity.class);
                                        startActivity(intent);

                                    }
                                    else
                                    {
                                        Intent intent = new Intent(HomeActivity.this, TeacherLogin.class);
                                        startActivity(intent);

                                    }
                                }
                                else
                                {
                                    Intent intent = new Intent(HomeActivity.this, TeacherLogin.class);
                                    startActivity(intent);

                                }

                            }

                            else {
                                progressDialog.hide();
                                Intent intent = new Intent(HomeActivity.this, TeacherLogin.class);
                                startActivity(intent);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error)
                        {
                            progressDialog.hide();
                            Toast.makeText(HomeActivity.this, "Please Check Your Internet Connectivity", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        student_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();
                DatabaseReference databaseReference= firebaseDatabase.getReference("Student");
                if(firebaseAuth.getCurrentUser()==null)
                {
                    progressDialog.hide();
                    Intent intent = new Intent(HomeActivity.this, StudentLogin.class);
                    startActivity(intent);
                }

                else
                {

                    String currentuser = firebaseAuth.getInstance().getCurrentUser().getUid();

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String EMAIL= firebaseAuth.getCurrentUser().getEmail();

                            if (snapshot.hasChild(currentuser)) {
                                String email = snapshot.child(currentuser).child("email").getValue().toString();
                                if (EMAIL.equals(email)){

                                    if (firebaseAuth.getCurrentUser().isEmailVerified()){
                                        progressDialog.hide();
                                        Intent intent = new Intent(HomeActivity.this, StudentComplaintActivity.class);
                                        startActivity(intent);

                                    }
                                    else
                                    {
                                        Intent intent = new Intent(HomeActivity.this, StudentLogin.class);
                                        startActivity(intent);

                                    }
                                }
                                else
                                {
                                    Intent intent = new Intent(HomeActivity.this, StudentLogin.class);
                                    startActivity(intent);

                                }

                            }

                            else {
                                progressDialog.hide();
                                Intent intent = new Intent(HomeActivity.this, StudentLogin.class);
                                startActivity(intent);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error)
                        {
                            progressDialog.hide();
                            Toast.makeText(HomeActivity.this, "Please Check Your Internet Connectivity", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        parent_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();
                DatabaseReference databaseReference= firebaseDatabase.getReference("Parent");
                if(firebaseAuth.getCurrentUser()==null)
                {
                    progressDialog.hide();
                    Intent intent = new Intent(HomeActivity.this, ParentLogin.class);
                    startActivity(intent);
                }

                else
                {

                    String currentuser = firebaseAuth.getInstance().getCurrentUser().getUid();

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String EMAIL= firebaseAuth.getCurrentUser().getEmail();

                            if (snapshot.hasChild(currentuser)) {
                                String email = snapshot.child(currentuser).child("email").getValue().toString();
                                if (EMAIL.equals(email)){

                                    if (firebaseAuth.getCurrentUser().isEmailVerified()){
                                        progressDialog.hide();
                                        Intent intent = new Intent(HomeActivity.this, ParentComplaintActivity.class);
                                        startActivity(intent);

                                    }
                                    else
                                    {
                                        Intent intent = new Intent(HomeActivity.this, ParentLogin.class);
                                        startActivity(intent);

                                    }
                                }
                                else
                                {
                                    Intent intent = new Intent(HomeActivity.this, ParentLogin.class);
                                    startActivity(intent);

                                }

                            }

                            else {
                                progressDialog.hide();
                                Intent intent = new Intent(HomeActivity.this, ParentLogin.class);
                                startActivity(intent);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error)
                        {
                            progressDialog.hide();
                            Toast.makeText(HomeActivity.this, "Please Check Your Internet Connectivity", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }
    @Override
    public void onBackPressed()
    {
        finishAffinity();
        finish();

    }
}