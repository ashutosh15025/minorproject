package com.example.minorproject;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {
    Button button;
    EditText email;
    EditText pass;
    EditText cfpass;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        button=(Button)findViewById(R.id.signin);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        cfpass=findViewById(R.id.conpass);
        firebaseAuth=FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail,pas,cpas;

                mail=email.getText().toString().trim();
                pas=pass.getText().toString().trim();
                cpas=cfpass.getText().toString().trim();
                if(pas.equals(cpas)){
                    if (!mail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                        oncreate(mail,pas);
                    } else {
                        Toast.makeText(MainActivity2.this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
                    }
          }
                else{
                   Toast.makeText(MainActivity2.this,"Email is not valid ",Toast.LENGTH_LONG).show();
                }
            }

            private void oncreate(String smail, String pas) {

                        firebaseAuth.createUserWithEmailAndPassword(smail, pas)
                        .addOnCompleteListener(MainActivity2.this,new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    FirebaseUser user=firebaseAuth.getCurrentUser();
                                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(getApplicationContext(),
                                                    "Please verify email from"+smail,
                                                    Toast.LENGTH_LONG)
                                                    .show();
                                            Intent intent=new Intent(MainActivity2.this,StartActivity.class);
                                            startActivity(intent);
                                            finish();


                                        }

                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), "email not sent"+e.getMessage(),
                                                    Toast.LENGTH_LONG)
                                                    .show();
                                        }
                                    });
                                    Toast.makeText(getApplicationContext(),
                                            "Registration successful!",
                                            Toast.LENGTH_LONG)
                                            .show();


                                   }

                        else{
                                    Toast.makeText(MainActivity2.this, "User Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


        });
    }
}