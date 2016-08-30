package com.hlju.lcc20.magictimer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by lcc20 on 2016/7/26.
 */
public class ExpressionView extends LinearLayout {
    int[] drawableIds={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};
//    int[] msgIds={R.string.app_name,R.string.app_name,R.string.app_name,R.string.app_name,R.string.app_name};
    private Context mcontext;
    public ExpressionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mcontext=context;
    }

    @Override
    protected void onFinishInflate() {
        ListView lv=(ListView)findViewById(R.id.ListView01);
        BaseAdapter ba=new BaseAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int arg0, View arg1, ViewGroup arg2) {
                LinearLayout ll=new LinearLayout(mcontext);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                ll.setPadding(5,5,5,5);
                ImageView ii=new ImageView(mcontext);
                ii.setImageDrawable(getResources().getDrawable(drawableIds[arg0],null));
                ii.setLayoutParams(new Gallery.LayoutParams(100,98));
                ll.addView(ii);
//                TextView tv=new TextView(mcontext);
//                tv.setText(getResources().getText(msgIds[arg0]));
//                tv.setTextSize(24);
//                tv.setPadding(5,5,5,5);
//                tv.setGravity(Gravity.LEFT);
//                ll.addView(tv);
                return ll;
            }
        };
        lv.setAdapter(ba);
//        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
////                TextView tv=(TextView)findViewById(R.id.TextView01);
//                LinearLayout ll=(LinearLayout)arg1;
////                TextView tvn=(TextView)ll.getChildAt(1);
//                StringBuffer sb=new StringBuffer();
//                sb.append(getResources().getText(R.string.app_name));
//                sb.append(":");
////                sb.append(tvn.getText());
////                String stemp=sb.toString();
////                tv.setText(stemp.split("\n")[0]);
//            }

//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
////                TextView tv=(TextView)findViewById(R.id.TextView01);
//                LinearLayout ll=(LinearLayout)arg1;
////                TextView tvn=(TextView)ll.getChildAt(1);
//                StringBuffer sb=new StringBuffer();
//                sb.append(getResources().getText(R.string.app_name));
//                sb.append(":");
////                sb.append(tvn.getText());
////                String stemp=sb.toString();
////                tv.setText(stemp.split("\n")[0]);
//            }
//        });
    }
}
