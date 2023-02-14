package com.example.minorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity  extends MainActivity2 {
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        button=findViewById(R.id.verify);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task<Void> usertask= firebaseAuth.getCurrentUser().reload();
                usertask.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        FirebaseUser user=firebaseAuth.getCurrentUser();
                        if(user.isEmailVerified()){
                            SharedPreferences sharedPref = getSharedPreferences("IDvalue",0);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("email", email.getText().toString());
                            editor.putString("pass", pass.getText().toString());
                            editor.apply();
                            Intent intent=new Intent(StartActivity.this,Recordactivity.class);
                            startActivity(intent);
                            finish();

                        }
                    }
                });


            }
        });
    }
}