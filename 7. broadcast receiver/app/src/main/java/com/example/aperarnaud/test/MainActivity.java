package com.example.aperarnaud.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.aperarnaud.test.Data.DataUtil;
import com.example.aperarnaud.test.Model.Playlist;
import com.example.aperarnaud.test.Services.DownloadFileFromUrl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    List<Playlist> playlists = new ArrayList<Playlist>();
    private RecyclerView recyclerView;
    private PlaylistAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new PlaylistAdapter(playlists);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        new DownloadFileFromUrl(this.getApplicationContext()).execute("https://api.deezer.com/user/100/playlists");
    }

    private BroadcastReceiver dlfailed = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent ) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Your Alert")
                    .setMessage("Your Message")
                    .setCancelable(false)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Whatever...
                        }
                    }).show();
        }
    };

    private BroadcastReceiver dlok = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent ) {
            playlists.clear();
            for (Playlist playlist : DataUtil.playlists.data ) {
                playlists.add(playlist);
            }

            mAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this.getApplicationContext());
        localBroadcastManager.registerReceiver(dlok, new IntentFilter("DL_SUCCEED"));
        localBroadcastManager.registerReceiver(dlfailed, new IntentFilter("DL_FAILED"));
    }

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this.getApplicationContext());
        localBroadcastManager.unregisterReceiver(dlok);
        localBroadcastManager.unregisterReceiver(dlfailed);
    }

    public void onClick(View view) {
        int alain = 0;
    }
}
