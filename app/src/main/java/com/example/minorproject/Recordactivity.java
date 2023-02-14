package com.example.minorproject;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.example.minorproject.ml.EmotionVoiceDetectionModel2;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Recordactivity extends AppCompatActivity {
  private static int MICROPHONE_PERMISSION_CODE=200;
  MediaRecorder mediaRecorder;
  MediaPlayer mediaPlayer;
  ImageButton record;
    ImageButton play;
    ImageButton stop;
    Boolean b=false;

  Chronometer chronometer;

LottieAnimationView lottieAnimationView;
ImageView signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordactivity);
        record=findViewById(R.id.button);
         int i=Loadmodel();
        String modelPath = "Emotion_Voice_Detection_Model (2).tflite";

        
        stop=findViewById(R.id.button3);
        chronometer=findViewById(R.id.timer);
        signout=findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences settings = getSharedPreferences("IDvalue",0);
                settings.edit().clear().commit();
                Intent intent =new Intent(Recordactivity.this,firstpage.class);


                startActivity(intent);

            }
        });
        lottieAnimationView=findViewById(R.id.animation_view);
        if(microphone()){
            getmicrophonepermission();
        }
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();

              mediaRecorder=new MediaRecorder();
              try{
                  b=true;
               lottieAnimationView.setAnimation("record2.json");
                  lottieAnimationView.playAnimation();
              mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
              mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
              mediaRecorder.setOutputFile(getRecodingFilePath());
              mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
              mediaRecorder.prepare();
              mediaRecorder.start();

                  Toast.makeText(Recordactivity.this,"Recording",Toast.LENGTH_LONG).show();
              }
              catch(Exception e){
                  e.printStackTrace();
              }
            }


        });


        stop.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                lottieAnimationView.setAnimation("record2.json");
                chronometer.stop();
          if(b){
         mediaRecorder.stop();
              mediaRecorder.release();

         mediaRecorder=null;
         byte[] s=fileToByteArray(getRecodingFilePath());

         Intent intent =new Intent(Recordactivity.this,remedies.class);

              loadmodel model=new loadmodel();
              int i1=model.load();
              intent.putExtra("value",i1);

         startActivity(intent);


               }
          else
              Toast.makeText(Recordactivity.this,"first record Record voice",Toast.LENGTH_LONG).show();
            }
        });

    }

    private int Loadmodel() {
return 1;
    }


    private String formatTime(int second, int min) {
        return String.format("%02d",min)+":"+String.format("%02d",second);

    }

    private String getRecodingFilePath() {
        ContextWrapper contextwrapper=new ContextWrapper(getApplicationContext());
       File musicDirectory = contextwrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC) ;
       File file=new File(musicDirectory,"textually"+".mp3");
       return file.getPath();
    }

    private boolean microphone(){
        if(this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            return true;
        }
        else{
            return false;
        }
    }


    void getmicrophonepermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},MICROPHONE_PERMISSION_CODE);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static byte[] fileToByteArray(String name){
        Path path = Paths.get(name);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public int Loadmodel(byte[] array) throws IOException {
        @NonNull EmotionVoiceDetectionModel2 model = EmotionVoiceDetectionModel2.newInstance(Recordactivity.this);


//        classifier inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 216, 1), DataType.FLOAT32);



        model.close();
        return 1;
    }


}