package com.superhepler;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidFragment;

import java.util.Calendar;


public class MainActivity extends FragmentActivity {
    float x_tmp1, x_tmp2, y_tmp1,y_tmp2;
    Button today;
    int layoutid=0;
    boolean a = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        today = (Button)findViewById(R.id.todayBtn);
        today.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CaldroidFragment caldroidFragment = new CaldroidFragment();
                Bundle args = new Bundle();
                Calendar cal = Calendar.getInstance();
                args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
                args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
                caldroidFragment.setArguments(args);

                android.support.v4.app.FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                //t.replace(R.id.cal, caldroidFragment);



                Bundle arg = new Bundle();
                arg.putBoolean(CaldroidFragment.ENABLE_CLICK_ON_DISABLED_DATES, true);
                caldroidFragment.setArguments(arg);
                Log.d("hide", String.valueOf(a));
                if(a) {
                    t.show(caldroidFragment);
                    t.replace(R.id.mainxml, caldroidFragment);
                    a=false;
                }
                else {
                    t.hide(caldroidFragment);
                    //t.commit();
                    Log.d("hide", String.valueOf(caldroidFragment.isHidden()));
                    t.replace(R.id.mainxml, caldroidFragment);
                    a=true;
                }
                t.commit();
            }
        });


    }

    public boolean onTouchEvent(MotionEvent event){
        //获取当前坐标
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x_tmp1 = x;
                y_tmp1 = y;
                break;
            case MotionEvent.ACTION_UP:
                x_tmp2 = x;
                y_tmp2 = y;
                Log.i("touch","滑动参值 x1="+ x_tmp1 +"; x2=" + x_tmp2);
                if(x_tmp1 != 0 && y_tmp1 != 0){
                    if(x_tmp1 - x_tmp2 > 8){
                        changeLayout();
                        Log.i("touch","向左滑动");
                    }
                    if(x_tmp2 - x_tmp1 > 8){
                        changeLayout();
                        Log.i("touch","向右滑动");
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void changeLayout() {
        switch (layoutid) {
            case 0:
                setContentView(R.layout.account_index);
                layoutid++;
                break;
            case 1:
                layoutid++;
                setContentView(R.layout.todolist);
                break;
            case 2:
                layoutid=0;
                setContentView(R.layout.activity_main);
                break;

        }
    }

}
