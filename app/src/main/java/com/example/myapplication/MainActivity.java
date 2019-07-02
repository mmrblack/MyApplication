package com.example.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.content.Intent;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MainActivity extends AppCompatActivity {
    private Button anniu;
    private Button btn2;
    private TextView textview;
    private TextView textview4;
    private EditText edittext;
    private int i=0;//记录点击次数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO
        anniu=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        edittext=(EditText)findViewById(R.id.editText) ;
        textview=(TextView) findViewById(R.id.textView2);
        textview4=(TextView)findViewById(R.id.textView4) ;
        anniu.setOnClickListener(new MyClick());
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Activity2.class);
                startActivity(i);
            }
        });

    }
    class MyClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            new Thread(runnable).start();
            new Thread(runnable2).start();

        }
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("MESSAGE","请求结果:" + val);
            JsonObject jsonObject = (JsonObject) new JsonParser().parse(val);
            Log.i("MESSAGE",jsonObject.get("author").getAsString());
            textview.setText(val);
        }
    };

    Runnable runnable = new Runnable(){
        @Override
        public void run() {
            // TODO: http request.
            Message msg = new Message();
            Bundle data = new Bundle();

            String httpUrl="http://ggggs.top/api_songci";
            String resultData ="";
            URL url=null;
            try {
                url=new URL (httpUrl) ;
            } catch (MalformedURLException e) {
                System.out.println (e.getMessage());
            }
            if (url !=null) {
                try {
                    HttpURLConnection urlConn= (HttpURLConnection) url .openConnection();
                    InputStreamReader in=new InputStreamReader (urlConn.getInputStream()) ;
                    BufferedReader buffer=new BufferedReader (in) ;
                    String inputLine=null;
                    while (((inputLine=buffer.readLine()) !=null)) {
                        resultData+=inputLine+"\n";

                    }
                    in.close ();
                    urlConn.disconnect();
                    if (resultData !=null) {
                        data.putString("value",resultData);
                    } else {

                    }
                } catch (IOException e) {

                }
            } else {

            }


            msg.setData(data);
            handler.sendMessage(msg);
        }
    };
    Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg2) {
            super.handleMessage(msg2);
            Bundle data2 = msg2.getData();
            String val2 = data2.getString("value");
            Log.i("MESSAGE","请求结果:" + val2);
            JsonObject jsonObject = (JsonObject) new JsonParser().parse(val2);
            Log.i("MESSAGE",jsonObject.get("author").getAsString());
            textview4.setText(val2);
        }
    };

    Runnable runnable2 = new Runnable(){
        @Override
        public void run() {
            // TODO: http request.
            Message msg2 = new Message();
            Bundle data2 = new Bundle();

            String httpUrl="http://ggggs.top/api_songci";
            String resultData ="";
            URL url=null;
            try {
                url=new URL (httpUrl) ;
            } catch (MalformedURLException e) {
                System.out.println (e.getMessage());
            }
            if (url !=null) {
                try {
                    HttpURLConnection urlConn= (HttpURLConnection) url .openConnection();
                    InputStreamReader in=new InputStreamReader (urlConn.getInputStream()) ;
                    BufferedReader buffer=new BufferedReader (in) ;
                    String inputLine=null;
                    while (((inputLine=buffer.readLine()) !=null)) {
                        resultData+=inputLine+"\n";

                    }
                    in.close ();
                    urlConn.disconnect();
                    if (resultData !=null) {
                        data2.putString("value",resultData);
                    } else {

                    }
                } catch (IOException e) {

                }
            } else {

            }


            msg2.setData(data2);
            handler2.sendMessage(msg2);
        }
    };


}