package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class BookListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list_item);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("/biblio");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
            }
                Map<String, String> map = (Map<String, String>) dataSnapshot.getValue();
                Toast.makeText(BookListActivity.this,String.valueOf(map),Toast.LENGTH_LONG).show();
                String  mdocument= map.get("document");
                String mliv_emp=map.get("liv_emp");
                String mnbchaise=map.get("nb_chaise");
                String mnbetud=map.get("nb_etud");
                String mnbetudexiste=map.get("nb_etud_existe");
                String mtemp=map.get("temp");


                Toast.makeText(BookListActivity.this,String.valueOf(mdocument),Toast.LENGTH_LONG).show();
                Toast.makeText(BookListActivity.this,String.valueOf(mliv_emp),Toast.LENGTH_LONG).show();
                Toast.makeText(BookListActivity.this,String.valueOf(mnbchaise),Toast.LENGTH_LONG).show();
                Toast.makeText(BookListActivity.this,String.valueOf(mnbetud),Toast.LENGTH_LONG).show();
                Toast.makeText(BookListActivity.this,String.valueOf(mnbetudexiste),Toast.LENGTH_LONG).show();
                Toast.makeText(BookListActivity.this,String.valueOf(mtemp),Toast.LENGTH_LONG).show();



            }


            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });}}
