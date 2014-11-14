package com.detroitlabs.networkingdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener, OnDataReceived{

    private static final String TAG = MainActivity.class.getName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }


    @Override
    public void receivedData(String jsonResult) {
        Log.v(TAG, jsonResult);
    }

    @Override
    public void onClick(View view) {
        Log.v(TAG, "button was clicked");
        APIRequest request = new APIRequest();
        request.setOnDataReceivedListener(this);
        request.execute();
    }
}
