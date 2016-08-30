package com.hlju.lcc20.magictimer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost= (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tabStopWatch").setIndicator("计时").setContent(R.id.tabStopWatch));
        tabHost.addTab(tabHost.newTabSpec("tabDate").setIndicator("记录").setContent(R.id.tabDate));
        tabHost.addTab(tabHost.newTabSpec("tabExpression").setIndicator("公式").setContent(R.id.tabExpression));


    }
    @Override
    protected void onDestroy() {
        stopWatchView.onDestroy();
        super.onDestroy();
    }
    private StopWatchView stopWatchView;
    private TabHost tabHost;


}
