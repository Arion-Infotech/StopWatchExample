package com.arioninfotech.stopwatchexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int seconds=0;
    private boolean startRun;

    TextView lblTime;

    Button btnStart,btnStop,btnReset;

    java.util.Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblTime = findViewById(R.id.lblTime);

        btnStart=findViewById(R.id.btnStart);
        btnStop=findViewById(R.id.btnStop);
        btnReset=findViewById(R.id.btnReset);


        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            startRun=savedInstanceState.getBoolean("startRun");
        }

        startTimer();


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRun=true;
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRun=false;
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRun=false;
                seconds=0;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        saveInstanceState.putInt("seconds", seconds);
        saveInstanceState.putBoolean("startRun", startRun);
        super.onSaveInstanceState(saveInstanceState);
    }


    public void startTimer() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        int hours = seconds/3600;
                        int minutes = (seconds%3600)/60;
                        int secs = seconds%60;

                        String time = String.format("%d:%02d:%02d", hours, minutes, secs);

                        lblTime.setText(time);

                        if(startRun){
                            seconds++;
                        }
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
}
