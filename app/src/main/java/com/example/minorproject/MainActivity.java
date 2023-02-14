package com.example.minorproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
     Button button;

     EditText email;
     EditText pass;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.login);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        firebaseAuth=FirebaseAuth.getInstance();



        SharedPreferences sharedPref = getSharedPreferences("IDvalue",0);



        if(sharedPref.contains("email")){
            String name = sharedPref.getString("email", "No name defined");
            if(sharedPref.contains("pass"))
            {
                String password = sharedPref.getString("pass", "No name defined");

        loginauto(name,password);}}

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        email.getText().toString(),
                        Toast.LENGTH_LONG)
                        .show();
                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){


                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("email", email.getText().toString());
                                editor.putString("pass", pass.getText().toString());
                                editor.apply();
                                Intent intent=new Intent(MainActivity.this,Recordactivity.class);
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
        });

    }

    private void loginauto(String name, String password) {

        firebaseAuth.signInWithEmailAndPassword(name,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Intent intent=new Intent(MainActivity.this,Recordactivity.class);
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