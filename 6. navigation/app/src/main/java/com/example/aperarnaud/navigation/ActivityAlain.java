package com.example.aperarnaud.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityAlain extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alain);

        TextView tv = (TextView)this.findViewById(R.id.textView2);
        tv.setText(this.getIntent().getStringExtra("msg"));
    }
}
