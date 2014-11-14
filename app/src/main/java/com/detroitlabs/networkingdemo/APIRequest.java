package com.detroitlabs.networkingdemo;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tyndallm on 11/13/14.
 */
public class APIRequest extends AsyncTask<Void, Integer, String> {

    private static final String TAG = APIRequest.class.getName();

    private OnDataReceived onDataReceivedlistener;

    public void setOnDataReceivedListener(OnDataReceived onDataReceived) {
        this.onDataReceivedlistener = onDataReceived;
    }

    protected String doInBackground(Void... voids) {
        String result = "";
        try {
            URL url = new URL("http://www.bike-1-1.com/phones");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = readStream(in);
            urlConnection.disconnect();
        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String readStream(InputStream in) {
        String result = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while((line = reader.readLine()) != null) {
                result = result + line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

//    public Event createEventFromJson(String jsonResult){
//
//    }

    @Override
    protected void onPostExecute(String jsonResult) {
        onDataReceivedlistener.receivedData(jsonResult);
    }
}
