package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.util.Log;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Activity2 extends AppCompatActivity {
    private EditText et;
    private Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        et = (EditText) findViewById(R.id.editText1);
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new MyClick2());
    }

    class MyClick2 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int i = (int) (Math.random() * 100);

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
                        et.setText (resultData) ;
                    } else {
                        et.setText ("Sorry,the content is null");
                    }
                } catch (IOException e) {
                    et.setText (e.getMessage () ) ;
                }
            } else {
                et.setText ("url is null") ;
            }


        }
    }
}