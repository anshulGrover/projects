package com.example.anshulgrover.dabbawala;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        new progressExecute().execute();


    }
    class progressExecute extends AsyncTask<Void,Void,Void>{
        public progressExecute(){
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(SplashScreen.this, "Please wait...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent i=new Intent();
            i.setClass(SplashScreen.this,LoginActivity.class);
            startActivity(i);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(int i=0;i<=50;i++){
                try{
                    Thread.sleep(50);
                    publishProgress(voids);

                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
