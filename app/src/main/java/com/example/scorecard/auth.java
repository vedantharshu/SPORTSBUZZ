package com.example.scorecard;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class auth extends AppCompatActivity {

    EditText uname;
    EditText pwd;
    Button submit;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        uname=(EditText)findViewById(R.id.unmae);
        pwd=(EditText)findViewById(R.id.pwd);
        submit=(Button)findViewById(R.id.submit);

        mAuth = FirebaseAuth.getInstance();
    }

    

}
