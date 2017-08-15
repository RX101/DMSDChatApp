package com.example.a15041867.dmsdchatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.text.format.DateFormat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;

public class MainPageActivity extends AppCompatActivity {

    TextView tvWeather;
    EditText etMessage;
    Button btnAddMessage;
    String name;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    // TODO: Task 1 - Declare Firebase variables
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference messageListRef, nameRef, mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        tvWeather = (TextView)findViewById(R.id.tvWeather);
        etMessage = (EditText)findViewById(R.id.etMessage);
        btnAddMessage = (Button)findViewById(R.id.btnAddMessage);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        String uid = firebaseUser.getUid();

        // TODO: Task 2: Get Firebase database instance and reference
        firebaseDatabase = FirebaseDatabase.getInstance();
        nameRef = firebaseDatabase.getReference("profiles/");
        messageListRef = firebaseDatabase.getReference("/messages");
        mName = nameRef.child(uid);

        mName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = dataSnapshot.getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        HttpRequest request = new HttpRequest("https://api.data.gov.sg/v1/environment/2-hour-weather-forecast");
        request.setMethod("GET");
        request.setAPIKey("api-key","ymGJMsVjut4L2vnMfWhaAL33orJfJbag");
        request.execute();

        try{
            String jsonString = request.getResponse();
            Log.d("info", jsonString);
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray itemArray = jsonObject.getJSONArray("items");
            JSONObject itemObject = itemArray.getJSONObject(0);
            JSONArray forecastArray = itemObject.getJSONArray("forecasts");
            // Populate the arraylist personList
            for (int i = 0; i < forecastArray.length(); i++) {
                JSONObject obj = forecastArray.getJSONObject(i);
                if(obj.getString("area").equalsIgnoreCase("woodlands")){
                    tvWeather.setText("Weather Forecast @ Woodlands:" + obj.getString("forecast"));
                }

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        btnAddMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = etMessage.getText().toString();
                Calendar now = Calendar.getInstance(); //Create a Calendar object with current date/time
                String messageTime = now.get(Calendar.DAY_OF_MONTH)+ "-"+
                        (now.get(Calendar.MONTH)+1) + "-" +
                        now.get(Calendar.YEAR) + now.get(Calendar.HOUR_OF_DAY)+":"+
                        now.get(Calendar.MINUTE)+":"+now.get(Calendar.SECOND);
//                DateFormat.format("dd-MM-yyyy (HH:mm:ss)",now);

                Messages messages = new Messages(msg,messageTime,name);
                messageListRef.push().setValue(messages);
            }
        });

    }
}
