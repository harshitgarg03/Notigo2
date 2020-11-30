package com.example.notigo;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Stories extends AppCompatActivity {

    private RecyclerView recyclerView;

    MyAdapter adapter;
    Button buttonback;
    Button buttonrefresh;

    DatabaseReference reference;
    ArrayList<Listitem> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);



        buttonback = findViewById(R.id.backbutt);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stories.this,
                        Homepage.class);
                startActivity(intent);
            }
        });





        //every update to firebase may produce duplicates in app
        //press button to refresh everytime firebase is updated
        buttonrefresh = findViewById(R.id.refresh);
        buttonrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true); //every item of recycler view has fixed size
                recyclerView.setLayoutManager(new LinearLayoutManager(Stories.this));
                list = new ArrayList<Listitem>();

                //never orderbyChild - recorded date as String + assume firebase takes in data daily so data alr sorted based on date
                reference = FirebaseDatabase.getInstance().getReference().child("ComStruc");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshotl : dataSnapshot.getChildren()){
                            Listitem l = dataSnapshotl.getValue(Listitem.class);
                            list.add(l);

                            adapter = new MyAdapter(Stories.this , list);
                            recyclerView.setAdapter(adapter);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Stories.this, "Something is wrong", Toast.LENGTH_LONG);

                    }
                });

                //set code to delete a RecyclerView item upon swiping (once dateline off then student can swipe to delete)
                //make manual cause there may be tasks student havent finish by datelines
                //deletion on appp must reflect on firebase also, click update button!!
                ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override


                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        MyAdapter.ViewHolder charaViewHolder = (MyAdapter.ViewHolder) viewHolder;
                        int position = charaViewHolder.getAdapterPosition();
                        list.remove(position);
                        Toast.makeText(Stories.this, "Event deleted", Toast.LENGTH_LONG).show();

                        //remove space after deletion of one recycler object
                        adapter.notifyItemRemoved(position);


                        //firebase deletion
                        //check is deletion complete through OnCompleteListener
                        reference.child("Event "+(position+1)).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("Delete", "Event has been deleted");
                                    Toast.makeText(Stories.this, "Firebase updated", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.d("Delete", "Event couldn't be deleted");
                                    Toast.makeText(Stories.this, "Firebase not updated", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }

                };
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
                itemTouchHelper.attachToRecyclerView(recyclerView);



            }

        });


    }


}