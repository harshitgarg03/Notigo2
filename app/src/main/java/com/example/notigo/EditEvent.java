package com.example.notigo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditEvent extends AppCompatActivity {

    EditText edittextcontent;
    EditText edittextdate;
    EditText edittexttime;

    Button buttonconfirm;
    Button back;

    DatabaseReference mDatabase;
    String current_value;
    Integer current_value_int;
    String eventno;


    /*FireBaseDataMap obj = new FireBaseDataMap();
    HashMap<String, String> dataMap = obj.fireebaseMap();*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        edittextcontent = findViewById(R.id.EditTextcontent);
        edittextdate = findViewById(R.id.EditTextdate);
        edittexttime = findViewById(R.id.EditTexttime);

        //edittextcontent.setText(""); to clear


        buttonconfirm = findViewById(R.id.buttonconfirm);
        buttonconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edittextcontent.getText().toString().equals("")) {
                    // If name or password is not entered
                    Toast.makeText(getApplicationContext(), "Input is required", Toast.LENGTH_LONG).show();
                } else {
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Personal");

                    String content = edittextcontent.getText().toString();
                    String date = edittextdate.getText().toString();
                    String time = edittexttime.getText().toString();

                    Userhelperclass helperclass = new Userhelperclass(content, date, time);
                    eventno = "Event "+current_value;

                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            current_value_int = (int) snapshot.getChildrenCount();
                            current_value = Integer.toString(current_value_int);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });
                    mDatabase.child(eventno).setValue(helperclass);


                }


            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String xyz="Personal";
                Intent intent = new Intent(EditEvent.this,
                        Stories.class);
                intent.putExtra("Subject_code", xyz);
                intent.putExtra("buttonVisible", true);
                startActivity(intent);


            }
        });

        ///edit = findViewById();

        //to update values on database
        //when a user wants to edit previous on recycler view
/*        mDatabase.child("Event 1").child("content").setValue(edittextcontent.getText().toString());

        mDatabase.child("Event 1").child("date").setValue(edittextdate.getText().toString());

        mDatabase.child("Event 1").child("time").setValue(edittexttime.getText().toString());*/




    }


}


