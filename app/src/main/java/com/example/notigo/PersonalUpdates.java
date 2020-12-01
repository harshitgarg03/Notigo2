package com.example.notigo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonalUpdates extends AppCompatActivity {

    Button buttonadd;

    RecyclerView mRecyclerView;
    MyPersonalAdapter mAdapter;
    String[] listersonalitem = new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_updates);

        buttonadd = findViewById(R.id.add);
        buttonadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //instantiate a collection in firebase collections
                    //new card in recycler view in personalUpdates
                }
            });

            //Add personal events on phone,and push to firebase so that its stored on phone everytime user clicks
            //These events are different for each indiv, hence we use firebase authentication to store these events under each user


        //start from event 1 is it.... save on firebase starting from index 1?
        //unlimted notes how to set
        for (int i = 1; i<=19; i++){
            listersonalitem[i]= "Event "+i;

        }

        mRecyclerView = (RecyclerView) findViewById(R.id.precyclerView);
        mAdapter = new MyPersonalAdapter(listersonalitem); //PersonalUpdates.this
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);


    }
}
