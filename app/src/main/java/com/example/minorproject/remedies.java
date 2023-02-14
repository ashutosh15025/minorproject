package com.example.minorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

public class remedies extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    TextView emotion;
    TextView remede,remede1,remede2;
    String musi1,musi2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedies);
        Intent intent=getIntent();
        int i=intent.getIntExtra("value",0);
        lottieAnimationView=findViewById(R.id.animation_view);
        remede=findViewById(R.id.remedi);
        remede1=findViewById(R.id.remedi1);
        remede2=findViewById(R.id.remedi2);
        emotion=findViewById(R.id.emotion);
        Random r = new Random();

        if(i==0){
        lottieAnimationView.setAnimation("angryLottie.json");
        emotion.setText("Why are you so Angry.....");
        remede.setText("PLease take deap breath");
            musi1="https://www.youtube.com/watch?v=fSS_R91Nimw";
            musi2="https://www.youtube.com/watch?v=5mpq_4rzB1U";
//            remede.setText("Breathe through panic....\n" + "Face your fears....\n" +"Imagine the worst. ...\n" +"Look at the evidence. ...\n" +"Don't try to be perfect. ...\n" +"Visualise a happy place. ...\n");
        }
        else if(i==1){
            lottieAnimationView.setAnimation("fearLottie.json");
            emotion.setText("Feeling Scared ....");
            musi1="https://www.youtube.com/watch?v=AETFvQonfV8";
            musi2="https://www.youtube.com/watch?v=MxGItnRhsBI";
//            remede.setText("Breathe through panic....\n" + "Face your fears....\n" +"Imagine the worst. ...\n" +"Look at the evidence. ...\n" +"Don't try to be perfect. ...\n" +"Visualise a happy place. ...\n");
        }
        else if(i==2){
            lottieAnimationView.setAnimation("happyLottie.json");
            emotion.setText("Feeling Happy....");
            remede.setText("Being happy never goes out of style..");
            musi1="https://www.youtube.com/watch?v=09R8_2nJtjg";
            musi2="https://www.youtube.com/watch?v=OllXb98Zn2g";
//            remede.setText("Breathe through panic....\n" + "Face your fears....\n" +"Imagine the worst. ...\n" +"Look at the evidence. ...\n" +"Don't try to be perfect. ...\n" +"Visualise a happy place. ...\n");
        }
        else{
            lottieAnimationView.setAnimation("sadLottie.json");
            emotion.setText("Why are you so sad.....");
            remede.setText("PLease take deap breath");
            musi1="https://www.youtube.com/watch?v=jnk8Bwk3r_M";
            musi2="https://www.youtube.com/watch?v=wMmmbJlWhtk";
            remede.setText("Breathe through panic....\n" + "Face your fears....\n" +"Imagine the worst. ...\n" +"Look at the evidence. ...\n" +"Don't try to be perfect. ...\n" +"Visualise a happy place. ...\n");
        }

    }

    public void music2(View view) {
        Uri uri = Uri.parse(musi2);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void music1(View view) {
        Uri uri = Uri.parse(musi1);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}