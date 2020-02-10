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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText user_email,user_password,user_name;
    TextView login_btn_on_signup;
    Button signup_btn;
    String uname,name,pwd;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    DatabaseReference mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mref= FirebaseDatabase.getInstance().getReference("Users");

        user_email = (EditText)findViewById(R.id.user_email);
        user_password = (EditText)findViewById(R.id.user_password);
        user_name = (EditText)findViewById(R.id.user_name);
        signup_btn = (Button) findViewById(R.id.register_btn);
        login_btn_on_signup = (TextView) findViewById(R.id.login_btn_on_signup);
        login_btn_on_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,Auth.class));
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpUser(user_email.getText().toString(),user_password.getText().toString());
            }
        });

        mAuth=FirebaseAuth.getInstance();
    }

    private void signUpUser(String email, String password) {
        if(email.equals("")){
            Toast.makeText(SignUpActivity.this, "Enter Email!!",
                    Toast.LENGTH_SHORT).show();
        }
        else if(password.equals("")){
            Toast.makeText(SignUpActivity.this, "Enter Password!!",
                    Toast.LENGTH_SHORT).show();
        }
        else if((user_name.getText().toString()).equals("")){
            Toast.makeText(SignUpActivity.this, "Enter Name!!",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            uname=user_email.getText().toString().trim();
            pwd=user_password.getText().toString().trim();
            name=user_name.getText().toString().trim();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                userProfile();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUpActivity.this, "Sign up failed: " + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void userProfile() {
        user = mAuth.getCurrentUser();
        if(user != null){
            verifyEmailRequest();
        }
    }
    private void verifyEmailRequest() {
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            User ob=new User(uname,pwd);
                            mref.child(name).setValue(ob);
                            Toast.makeText(SignUpActivity.this,"Email verification sent on\n"+user_email.getText().toString(),Toast.LENGTH_LONG).show();
                            mAuth.signOut();
                            startActivity(new Intent(SignUpActivity.this,Auth.class));
                        }
                        else {
                            Toast.makeText(SignUpActivity.this,"Sign up Success But Failed to send verification email.",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}
