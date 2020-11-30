package com.example.notigo;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.net.Uri;
import android.content.Intent;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import javax.annotation.Nullable;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);



        Button buttonedim;
        Button buttonmyportal;

        Button Comstruc;
        Button Infosys;

        Button buttonBac;

        //wanna make the student id into edit text profile name?





        Comstruc=findViewById(R.id.comstruc);
        Comstruc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this,
                        Stories.class);
                startActivity(intent);

            }
        });





        buttonedim = findViewById(R.id.edim);
        buttonedim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri webpage = Uri.parse("https://edimension.sutd.edu.sg");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

                if (webIntent.resolveActivity(  getPackageManager()  ) != null){
                    startActivity(webIntent);
                }

            }
        });


        buttonmyportal = findViewById(R.id.myportal);
        buttonmyportal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri webpage = Uri.parse("https://myportal.sutd.edu.sg/psp/EPPRD/EMPLOYEE/EMPL/h/?tab=DEFAULT&cmd=login&errorCode=106&languageCd=ENG");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

                if (webIntent.resolveActivity(  getPackageManager()  ) != null){
                    startActivity(webIntent);
                }

            }
        });

        buttonBac = findViewById(R.id.backtoprofile);
        buttonBac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //havent import main activity
                Intent intent = new Intent(Homepage.this,
                        MainActivity.class);
                startActivity(intent);

            }
        });












    }
}