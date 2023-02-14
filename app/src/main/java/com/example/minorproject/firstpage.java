package com.example.minorproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class firstpage extends AppCompatActivity {
 Button logb,sign;


    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        logb=findViewById(R.id.login);
        firebaseAuth=FirebaseAuth.getInstance();
        sign=findViewById(R.id.signup);
        SharedPreferences sharedPref = getSharedPreferences("IDvalue", 0);
        if(sharedPref.contains("email")){
            String name = sharedPref.getString("email", "No name defined");
            if(sharedPref.contains("pass"))
            {
                String password = sharedPref.getString("pass", "No name defined");

                loginauto(name,password);}}
        else{
            Toast.makeText(getApplicationContext(), "email not present",Toast.LENGTH_LONG).show();
        }
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(firstpage.this,MainActivity2.class);
                startActivity(intent);
                finish();

            }
        });
        logb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(firstpage.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void loginauto(String name, String password) {

        firebaseAuth.signInWithEmailAndPassword(name,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Intent intent=new Intent(firstpage.this,Recordactivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "sign in not complete"+e.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

}