package com.hlju.lcc20.magictimer;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lcc20 on 2016/7/26.!
 */
public class StopWatchView extends LinearLayout {
    private static final String TAG = "StopWatchView";
    private Timer timer;
    private TimerTask timerTask;
    private TimerTask showTimeTask;
    private int tenMSec;
    private long lastUpTime;
    private static final String allZero = "00";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_WHAT_SHOW_TIME:
                    DecimalFormat mFormat = new DecimalFormat("00");
                    tvHour.setText(mFormat.format(tenMSec / 100 / 60 / 60));
                    tvMin.setText(mFormat.format(tenMSec / 100 / 60 % 60));
                    tvSec.setText(mFormat.format(tenMSec / 100 % 60));
                    tvMSec.setText(mFormat.format(tenMSec % 100));
                    break;
                default:
                    break;
            }
        }
    };
    private static final int MSG_WHAT_SHOW_TIME = 1;
    private TextView tvHour, tvMin, tvSec, tvMSec;

    private long downTime;
    private long upTime;

    private boolean isRunning;

    public StopWatchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        timer = new Timer();
        tenMSec = 0;
    }

    @Override
    protected void onFinishInflate() {
        tvHour = (TextView) findViewById(R.id.timeHour);
        tvHour.setText(allZero);
        tvMin = (TextView) findViewById(R.id.timeMin);
        tvMin.setText(allZero);
        tvSec = (TextView) findViewById(R.id.timeSec);
        tvSec.setText(allZero);
        tvMSec = (TextView) findViewById(R.id.timeMSec);
        tvMSec.setText(allZero);

        //显示时间
        showTimeTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(MSG_WHAT_SHOW_TIME);
            }
        };
        timer.schedule(showTimeTask, 200, 100);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downTime = event.getEventTime();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                long thisUpTime = event.getEventTime();
                long rs = thisUpTime - lastUpTime;
                lastUpTime = thisUpTime;
                if (rs < 200) {
                    resetTime();
                    Log.i(TAG, "onTouchEvent:這有問題");
                    
                }


                upTime = event.getEventTime();
                if (upTime - downTime < 200) {//单击事件
                    stopTime();
                } else {//长按事件
                    resetTime();
                    if (!isRunning) {
                        startTime();
                    } else {
                        resetTime();
                    }
                }
                break;
        }
        return true;
    }

    /**
     * 开始计时
     */
    private void startTime() {
        isRunning = true;
        if (timerTask == null) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    tenMSec++;
                }
            };
        }
        timer.schedule(timerTask, 10, 10);
    }

    /**
     * 停止计时
     */
    private void stopTime() {
        isRunning = false;
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    /**
     * 复位计时
     */
    private void resetTime() {
        stopTime();
        tenMSec = 0;
        tvHour.setText(allZero);
        tvMin.setText(allZero);
        tvSec.setText(allZero);
        tvMSec.setText(allZero);
    }


    public void onDestroy() {
    }
}
