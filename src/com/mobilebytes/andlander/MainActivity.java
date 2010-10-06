package com.mobilebytes.andlander;



import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);




        findViewById(R.id.Go).setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

            }
        });

        findViewById(R.id.Info).setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

            }
        });
    }




}