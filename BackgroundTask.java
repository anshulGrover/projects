package com.example.anshulgrover.dabbawala;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Anshul Grover on 13-Oct-17.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;
    String e,p;
    BackgroundTask(Context ctx) {
        this.ctx=ctx;
    }
    String json_url;
    String json_string;
    String json_login;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        json_url="http://192.168.0.106/dabbawala/register.php";
        json_login="http://192.168.0.106/dabbawala/login.php";
    }

    @Override
    protected void onPostExecute(String result) {

        System.out.println(result);


       if(result.equals("connection successfull inserted")){
           Toast.makeText(ctx, "registration successful", Toast.LENGTH_SHORT).show();
           Intent p=new Intent();
           p.setClass(ctx,LoginActivity.class);
           ctx.startActivity(p);

        }
        else if(result.equals("connection successfull user already exist")){

            Toast.makeText(ctx, "Username already registered, please try another username.", Toast.LENGTH_SHORT).show();


        }
        else if(result.equals("connection successfull login successful")){
           Toast.makeText(ctx, "Login sucessfull", Toast.LENGTH_SHORT).show();
           Intent intent=new Intent(ctx,MainActivity.class);
           intent.putExtra("email",e);
           intent.putExtra("password",p);
           ctx.startActivity(intent);

       }
       else if(result.equals("connection successfull wrong username and password")){
           Toast.makeText(ctx, "Please check username and password", Toast.LENGTH_SHORT).show();
       }
       else if(result.equals("connection successfull user not exist")){
           Toast.makeText(ctx, "Username not exist,please register first!", Toast.LENGTH_SHORT).show();
           Intent q=new Intent(ctx,Register.class);
           ctx.startActivity(q);
       }

     else {
            Toast.makeText(ctx, "There is some problem please try again later....", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url="http://192.168.0.106/dabbawala/register.php";
        String log_url="http://192.168.0.106/dabbawala/login.php";
        String method= params[0];
        if(method.equals("register")){
            String name=params[1];
            String email=params[2];
            String password=params[3];
            String mobile=params[4];
            String pickup_add=params[5];
            String drop_add=params[6];
            String drop_time=params[7];
            try {
                URL url =new URL(reg_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                        URLEncoder.encode("mobile","UTF-8")+"="+URLEncoder.encode(mobile,"UTF-8")+"&"+
                        URLEncoder.encode("pickup_add","UTF-8")+"="+URLEncoder.encode(pickup_add,"UTF-8")+"&"+
                        URLEncoder.encode("drop_add","UTF-8")+"="+URLEncoder.encode(drop_add,"UTF-8")+"&"+
                        URLEncoder.encode("drop_time","UTF-8")+"="+URLEncoder.encode(drop_time,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                inputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                URL url=new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while((json_string=bufferedReader.readLine())!=null){
                    stringBuilder.append(json_string+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    else if(method.equals("login")){

            String email=params[1];
            String password=params[2];
            try {
                URL url=new URL(log_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os));
                String data=URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();
                while((json_string=bufferedReader.readLine())!=null){
                    stringBuilder.append(json_string+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                e=email;
                p=password;
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
