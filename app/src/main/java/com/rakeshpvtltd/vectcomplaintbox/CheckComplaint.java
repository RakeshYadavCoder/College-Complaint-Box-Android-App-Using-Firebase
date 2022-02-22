package com.rakeshpvtltd.vectcomplaintbox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CheckComplaint extends AppCompatActivity {

    ListView list;
    ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<String> keysList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_complaint);

        list = (ListView) findViewById(R.id.list_item);

         arrayAdapter = new ArrayAdapter<String>(CheckComplaint.this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Complaint");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String value = snapshot.getValue(String.class);
                arrayList.add(value);
                keysList.add(snapshot.getKey());
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String string = snapshot.getValue(String.class);

                arrayList.remove(string);
                keysList.remove(snapshot.getKey());
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(adapterView.getContext());
                builder.setTitle("Delete");
                builder.setMessage("Are You Sure To Delete This Complaint");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String key = keysList.get(position);
                        databaseReference.child(key).removeValue();

                    }
                });


                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });

                builder.create().show();

                return false;
            }
        });





//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                String key = keysList.get(position);
//                databaseReference.child(key).removeValue();
//            }
//        });



    }

    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(CheckComplaint.this, AdminPanel.class));
        finishAffinity();
        finish();

    }


}