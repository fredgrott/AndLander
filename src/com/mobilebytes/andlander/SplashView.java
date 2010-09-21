package com.mobilebytes.andlander;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import roboguice.inject.InjectResource;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Region;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.widget.ImageView;

public class SplashView extends ImageView {

    @InjectResource(R.drawable.android_honeycomb)
    protected int mDrawAndroid = R.drawable.android_honeycomb;
    @InjectResource(R.drawable.icon_iphone)
    protected int mDrawIphone = R.drawable.icon_iphone;

	 /** The m handset count. */
    private int mHandsetCount;

    /** The Constant MSG_RECALC_HANDSET_POSITIONS. */
    private static final int MSG_RECALC_HANDSET_POSITIONS = 1;

    /** The Constant HANDSET_UPDATE_TICKTIME. */
    private static final int HANDSET_UPDATE_TICKTIME = 200;

    /** The m paint. */
    private final Paint mPaint = new Paint();

    /** The m bm android. */
    private Bitmap mBmAndroid;

    /** The m bm handset. */
    private Bitmap mBmHandset;

    /** The m android x pos. */
    private int mAndroidXPos = -1;

    /** The m android y pos. */
    private int mAndroidYPos = -1;

    /** The m handset positions. */
    private List<int[]> mHandsetPositions = new ArrayList<int[]>();

	/**
	 * Instantiates a new and party splash.
	 *
	 * @param context the context
	 */
	public SplashView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setFocusable(true);

        // Create the bitmaps
        // BitmapFactory.decodeResource(getResources(),
        //R.drawable.android_honeycomb);
        mBmAndroid = BitmapFactory.decodeResource(getResources(),
                mDrawAndroid);
        //BitmapFactory.decodeResource(getResources(),
        //R.drawable.icon_iphone);
        mBmHandset = BitmapFactory.decodeResource(getResources(),
                mDrawIphone);

        /**
         * place iphone randomly
         */
        Random generator = new Random();
        mHandsetCount = 5 + generator.nextInt(3);
        for (int i = 0; i < mHandsetCount; i++) {
            int[] position = { 50 + generator.nextInt(250),
                    50 + generator.nextInt(350) };
            mHandsetPositions.add(position);
        }
        /**
         * place android randomly
         */
        Random generatorMe = new Random();
        int aXpos = (50 + generatorMe.nextInt(250));
        mAndroidXPos = aXpos;
        int aYpos = (50 + generatorMe.nextInt(125));
        mAndroidYPos = aYpos;
        Message msg = Message.obtain();
        msg.arg1 = MSG_RECALC_HANDSET_POSITIONS;
        handler.sendMessageDelayed(msg, HANDSET_UPDATE_TICKTIME);
	}

	/* (non-Javadoc)
	 * @see android.widget.ImageView#onDraw(android.graphics.Canvas)
	 */
	@Override
    protected void onDraw(Canvas canvas) {

		canvas.drawColor(Color.WHITE);

        if (!(mAndroidXPos < 0 && mAndroidYPos < 0)) {
            canvas.drawBitmap(mBmAndroid, mAndroidXPos, mAndroidYPos,
                    mPaint);
        }


        for (int[] position: mHandsetPositions) {
            canvas.drawBitmap(mBmHandset, position[0], position[1],
                            mPaint);
        }


    }

	/**
	 * Handle the finger press events.
	 *
	 * @param event the event
	 * @return true, if successful
	 */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (mAndroidXPos < 0 && mAndroidYPos < 0) {


                    mAndroidXPos = (int) event.getX() -
                        (mBmAndroid.getWidth() / 2);
                    mAndroidYPos = (int) event.getY() -
                        (mBmAndroid.getHeight() / 2);
                }
                invalidate();
                break;
            }

            case MotionEvent.ACTION_MOVE: {


                if (!(mAndroidXPos < 0 && mAndroidYPos < 0)) {
                    Region r = new Region(mAndroidXPos, mAndroidYPos,
                            mAndroidXPos + mBmAndroid.getWidth(),
                            mAndroidYPos + mBmAndroid.getHeight());
                    if (r.contains((int) event.getX(), (int) event.getY())) {
                        mAndroidXPos = (int) event.getX() -
                            (mBmAndroid.getWidth() / 2);
                        mAndroidYPos = (int) event.getY() -
                            (mBmAndroid.getHeight() / 2);
                    }
                    Message msg = Message.obtain();
                    msg.arg1 = MSG_RECALC_HANDSET_POSITIONS;
                    handler.sendMessageDelayed(msg, HANDSET_UPDATE_TICKTIME);
                }
                invalidate();
                break;
            }
		default:
			break;
        }
        return true;
    }

    /** Handle incoming messages. */
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.arg1) {
                case MSG_RECALC_HANDSET_POSITIONS: {


                    boolean allInPosition = true;
                    boolean thisHandsetHasMoved;
                    int handsetCount = 0;
                    int finalHandsetXPos;
                    int finalHandsetYPos;
                    int newHandsetXPos;
                    int newHandsetYPos;

                    for (int[] position: mHandsetPositions) {
                        thisHandsetHasMoved = false;


                        finalHandsetXPos = mAndroidXPos
                                + mBmAndroid.getWidth()
                                + (handsetCount * mBmHandset.getWidth());
                        finalHandsetYPos = mAndroidYPos
                                + (mBmAndroid.getHeight() / 2);


                        if (finalHandsetXPos != position[0]) {
                            thisHandsetHasMoved = true;
                            newHandsetXPos = position[0];
                            if (finalHandsetXPos < position[0]) {
                                newHandsetXPos--;
                            } else {
                                newHandsetXPos++;
                            }
                            position[0] = newHandsetXPos;
                        }


                        if (finalHandsetYPos != position[1]) {
                            thisHandsetHasMoved = true;
                            newHandsetYPos = position[1];
                            if (finalHandsetYPos < position[1]) {
                                newHandsetYPos--;
                            } else {
                                newHandsetYPos++;
                            }
                            position[1] = newHandsetYPos;
                        }
                        if (thisHandsetHasMoved) {
                            mHandsetPositions.set(handsetCount, position);
                            allInPosition = false;
                        }
                        handsetCount++;
                    }

                    if (!allInPosition) {
                        Message msgUpdate = Message.obtain();
                        msgUpdate.arg1 = MSG_RECALC_HANDSET_POSITIONS;
                        handler.sendMessageDelayed(msgUpdate,
                                HANDSET_UPDATE_TICKTIME);
                        invalidate();
                    }
                    break;
                }
			default:
				break;
            }
        }
    };



}
