package com.example.aperarnaud.test.Services;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;

import com.example.aperarnaud.test.Data.DataUtil;
import com.example.aperarnaud.test.Model.Playlist;
import com.example.aperarnaud.test.Model.Playlists;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadFileFromUrl extends AsyncTask<String, Integer, String> {
    private Context context;

    public DownloadFileFromUrl(Context context) {
        this.context = context;
    }

    protected String doInBackground(String... urls) {
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;

        String resultEnd = null;

        try {
            URL url = new URL(urls[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            input = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                result.append(line);
            }
            resultEnd = result.toString();
        } catch (Exception e) {
            return e.toString();
        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }

            if (connection != null)
                connection.disconnect();
        }
        return resultEnd;
    }

    protected void onPostExecute(String result) {
        if(result != null) {
            // parsing json
            final Gson gson = new Gson();
            DataUtil.playlists = gson.fromJson(result, Playlists.class);

            if(DataUtil.playlists.data == null) {
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
                Intent localIntent = new Intent("DL_FAILED");
                localBroadcastManager.sendBroadcast(localIntent);
                return;
            }

            // inform data received
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
            Intent localIntent = new Intent("DL_SUCCEED");
            localBroadcastManager.sendBroadcast(localIntent);
            return;

        } else {
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(context);
            Intent localIntent = new Intent("DL_FAILED");
            localBroadcastManager.sendBroadcast(localIntent);
            return;
        }
    }
}
