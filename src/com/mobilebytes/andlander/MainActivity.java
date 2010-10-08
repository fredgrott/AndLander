package com.mobilebytes.andlander;





import com.mobilebytes.andlander.animationutils.AnimUtils;

import android.app.Activity;
import android.content.Context;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;

public class MainActivity extends Activity {

	private Handler mblogoHandler = new Handler();
	private Handler andlanderHandler = new Handler();


	private final int mbDisplayLength = 15000;
	private final int andlanderDisplayLength = 25000;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        final MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.tranquility);
        mp.start();



        View root = findViewById(R.id.DashBoardBar);

        AnimUtils.setLayoutAnim_slidedownfromtop((ViewGroup) root, this);


        findViewById(R.id.Go).setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

            }
        });

        findViewById(R.id.Inf).setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

            }
        });
    }





}