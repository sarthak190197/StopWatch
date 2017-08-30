//package com.example.android.stopwatch;
//
//import android.content.BroadcastReceiver;
//import android.os.AsyncTask;
//import android.provider.Settings;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//public class MainActivity extends AppCompatActivity {
//    public static final String TAG = "StopWatch";
//    TextView min;
//    TextView sec;
//    TextView msec;
//    Button btnStart;
//    Button btnStop;
//    Button btnRestart;
//    boolean flag = true;
//    int f=1,f1=1;
//    long startTime ,lastTime = 0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        min = (TextView) findViewById(R.id.min);
//        sec = (TextView) findViewById(R.id.sec);
//        msec = (TextView) findViewById(R.id.msec);
//        btnStart = (Button) findViewById(R.id.btnStart);
//        btnStop = (Button) findViewById(R.id.btnStop);
//        btnRestart = (Button) findViewById(R.id.btnRes);
//
//
//        btnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                flag = true;
//                Log.d(TAG, "onClick: Start");
//                myTask task;
//
//                if(f1==1) {
//                    startTime = System.currentTimeMillis();
//                    f1=0;
//                }
//                Log.d(TAG, "onClick: starttime : "+startTime);
////                startTime = System.currentTimeMillis();
//                task = new myTask();
//                task.execute(startTime);
//
//            }
//        });
//        btnStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                flag = false;
//
////                if(f==1) {
////                    btnStop.setText("Reset");
////                    btnStart.setText("Resume");
////                    f=0;
////                }
////                else
////                {
////                    btnStop.setText("Stop");
////                    btnStart.setText("Start");
////                    f=1;
////                }
//                btnStart.setText("Resume");
//                Log.d(TAG, "onClick: Stop");
//
//            }
//        });
//        btnRestart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                min.setText("00");
//                sec.setText("00");
//                msec.setText("00");
//                flag=true;
//                f=1;
//                f1=1;
//            }
//        });
//    }
//
//    class myTask extends AsyncTask<Long, Long, Long> {
//
//        @Override
//        protected Long doInBackground(Long... params) {
//            Log.d(TAG, "doInBackground: Entered");
//            long time = 0, minutes = 0, seconds = 0, tempseconds = 0, milliseconds = 0,i;
//
//            while (flag == true) {
//
//
//                time = System.currentTimeMillis() - startTime;
//                Log.d(TAG, "doInBackground: start: "+startTime);
//                Log.d(TAG, "doInBackground: time: "+time);
//                if ((time % 10) == 0)
//                {
//                    milliseconds = (time % 1000) / 10;
//                    tempseconds = time / 1000;
//                    if (tempseconds >= 60)
//
//                   {
//                        seconds = tempseconds - minutes * 60;
//                    }
//                    else seconds = tempseconds;
//                    minutes = tempseconds / 60;
//                    publishProgress(minutes, seconds, milliseconds);
//                }
//
//
//            }
//            return System.currentTimeMillis();
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Log.d(TAG, "onPreExecute: ");
//        }
//
//        @Override
//        protected void onPostExecute(Long aLong) {
//            super.onPostExecute(aLong);
//            Log.d(TAG, "onPostExecute: "+aLong);
//
//            startTime=aLong;
//        }
//
//        @Override
//        protected void onProgressUpdate(Long... values) {
//            super.onProgressUpdate(values);
//
//            Log.d(TAG, "onProgressUpdate: "+values[0]+":"+values[1]+":"+values[2]);
//            if (values[0] < 10) {
//                min.setText("0" + String.valueOf(values[0]));
//            } else min.setText(String.valueOf(values[0]));
//            if (values[1] < 10) {
//                sec.setText(String.valueOf("0" + values[1]));
//            } else sec.setText(String.valueOf(values[1]));
//            if (values[2] < 10) {
//                msec.setText("0" + String.valueOf(values[2]));
//            } else msec.setText(String.valueOf(values[2]));
//
//
//        }
//    }
//}
//
package com.example.android.stopwatch;

import android.content.BroadcastReceiver;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "StopWatch";
    TextView min;
    TextView sec;
    TextView msec;
    Button btnStart;
    Button btnStop;
    Button btnRestart;
    boolean flag = false;
    long time = 0, minutes = 0, seconds = 0, tempseconds = 0, milliseconds = 0;
    long startTime ,lastTime = 0, i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        min = (TextView) findViewById(R.id.min);
        sec = (TextView) findViewById(R.id.sec);
        msec = (TextView) findViewById(R.id.msec);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnRestart = (Button) findViewById(R.id.btnRes);
        btnStop.setVisibility(View.INVISIBLE);
        btnRestart.setVisibility(View.INVISIBLE);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
                Log.d(TAG, "onClick: Start");
                myTask task;
                btnStart.setText("Start");
                btnStart.setVisibility(View.INVISIBLE);
                btnStop.setVisibility(View.VISIBLE);
                btnRestart.setVisibility(View.VISIBLE);


                Log.d(TAG, "onClick: starttime : "+startTime);
                task = new myTask();
                task.execute(1000L);

            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                btnStart.setText("Resume");
                btnStart.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.INVISIBLE);
                btnRestart.setVisibility(View.VISIBLE);
                Log.d(TAG, "onClick: Stop");

            }
        });
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                min.setText("00");
                sec.setText("00");
                msec.setText("00");
                btnStart.setText("Start");
                btnStart.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.INVISIBLE);
                btnRestart.setVisibility(View.INVISIBLE);
                minutes = 0;
                seconds = 0;
                tempseconds = 0;
                milliseconds = 0;
                lastTime=0;
                flag=true;

            }
        });
    }

    class myTask extends AsyncTask<Long, Long, Long> {

        @Override
        protected Long doInBackground(Long... params) {
            Log.d(TAG, "doInBackground: Entered");


            while (flag == false) {
                for(i=lastTime;i<=params[0];++i)
                {
                    if(flag==true)
                    {
                        lastTime = i;
                        return 9l;

                    }
                    lastTime = 0;
                    oneLoop();
                    if (i==999){
                        seconds++;
                    }
                    if (seconds==60){
                        minutes++;
                        seconds=0;
                    }
                    publishProgress(minutes,seconds,i/10);

                }



                }


            return 999l;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);

            startTime=aLong;
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);

            Log.d(TAG, "onProgressUpdate: "+values[0]+":"+values[1]+":"+values[2]);
            if (values[0] < 10) {
                min.setText("0" + String.valueOf(values[0]));
            } else min.setText(String.valueOf(values[0]));
            if (values[1] < 10) {
                sec.setText(String.valueOf("0" + values[1]));
            } else sec.setText(String.valueOf(values[1]));
            if (values[2] < 10) {
                msec.setText("0" + String.valueOf(values[2]));
            } else msec.setText(String.valueOf(values[2]));


        }
    }
    void oneLoop(){
        startTime = System.currentTimeMillis();
        while (System.currentTimeMillis()-startTime<1);
    }
}


