package com.example.a15041867.dmsdchatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetDisplayNameActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference nameListRef;
    private FirebaseDatabase firebaseDatabase;

    EditText etSubmitDisplayName;
    Button btnSubmitDisplayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_display_name);

        etSubmitDisplayName = (EditText)findViewById(R.id.etSubmitDisplayName);
        btnSubmitDisplayName = (Button)findViewById(R.id.btnSubmitDisplayName);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        // TODO: Task 2: Get Firebase database instance and reference
        firebaseDatabase = FirebaseDatabase.getInstance();
        nameListRef = firebaseDatabase.getReference("profiles/");
        Toast.makeText(getBaseContext(),firebaseUser.getUid() +"", Toast.LENGTH_LONG).show();

        btnSubmitDisplayName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = firebaseUser.getUid();
                String displayName = etSubmitDisplayName.getText().toString();
                nameListRef.child(uid).setValue(displayName);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            firebaseAuth.signOut();
            Intent i = new Intent(getBaseContext(), MainActivity.class);
            startActivity(i);

        }

        return super.onOptionsItemSelected(item);
    }
}
