package com.example.smartwe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.smartwe.Fragment.HomeFragment;
import com.example.smartwe.Model.UserDataModel;
import com.example.smartwe.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding signUpBinding;
    private FirebaseAuth auth;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater()); //binding, so it wont require findviewbyid,
                                                                            // we can use any view by using signupbinding. .
        setContentView(signUpBinding.getRoot());

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        signUpBinding.registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = signUpBinding.emailEditText.getText().toString();
                String password = signUpBinding.passwordEditText.getText().toString();
                String name = signUpBinding.nameEditText.getText().toString();
                String profession = signUpBinding.professionEditText.getText().toString();

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            UserDataModel userModel = new UserDataModel(name, profession, email, password);
                            String currUserId = task.getResult().getUser().getUid(); //TODO: have to deal with the not null value
                            db.getReference().child("User").child(currUserId).setValue(userModel);
                            Toast.makeText(SignUpActivity.this, "User Data Saved", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(SignUpActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        signUpBinding.goToLoginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}