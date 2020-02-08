package com.example.scorecard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Auth extends AppCompatActivity {

    EditText input_email,input_password;
    TextView signUp_btn_on_login,forgot_password_on_login,welcome;
    Button login_btn;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    String ss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        welcome=(TextView)findViewById(R.id.welcome);
        input_email = (EditText)findViewById(R.id.input_email);
        input_password = (EditText)findViewById(R.id.input_password);
        login_btn = (Button) findViewById(R.id.login_btn);
        signUp_btn_on_login = (TextView) findViewById(R.id.signup_btn_on_login);
        forgot_password_on_login = (TextView)findViewById(R.id.forgot_password_on_login);

        Intent intent=getIntent();
        ss=intent.getStringExtra("game");

        welcome.setText("Welcome "+ss+" Admin");
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(input_email.getText().toString(),input_password.getText().toString());
            }
        });
        signUp_btn_on_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Auth.this,SignUpActivity.class));
            }
        });
        forgot_password_on_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Auth.this,ForgotPasswordActivity.class));
            }
        });
        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        user=mAuth.getCurrentUser();
        updateUI(user);
    }

    /*------------ Below Code is for successful login process -----------*/

    private void loginUser(String email, String password) {
        if(email.equals("")){
            Toast.makeText(Auth.this, "Enter Email!!",
                    Toast.LENGTH_SHORT).show();
        }
        else if(password.equals("")){
            Toast.makeText(Auth.this, "Enter Password!!",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Auth.this, "Authentication failed: " + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        }
    }

    private void updateUI(FirebaseUser user1) {
        /*-------- Check if user is already logged in or not--------*/
        user1=mAuth.getCurrentUser();
        if (user1 != null) {
            /*------------ If user's email is verified then access login -----------*/
            if(user1.isEmailVerified()){
                Toast.makeText(Auth.this, "Login Success.",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Auth.this,Cricket.class));
            }
            else {
                Toast.makeText(Auth.this, "Your Email is not verified.",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }
}

