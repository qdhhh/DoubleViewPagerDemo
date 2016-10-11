package com.android.qdhhh.doubleviewpagerdemo;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView cursor_iv_id;
    private ViewPager first_vp_id;

    private MyListener myListener;

    private int currIndex = 0;

    private int currentPostrion = 0;

    int oneFour;

    List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cursor_iv_id = (ImageView) findViewById(R.id.cursor_iv_id);
        first_vp_id = (ViewPager) findViewById(R.id.first_vp_id);

        myListener = new MyListener();

        findViewById(R.id.tv_1).setOnClickListener(myListener);
        findViewById(R.id.tv_2).setOnClickListener(myListener);
        findViewById(R.id.tv_3).setOnClickListener(myListener);
        findViewById(R.id.tv_4).setOnClickListener(myListener);

        initImageView();

        setViewPager();

    }

    private void setViewPager() {
        getFragmentsData();

        first_vp_id.setAdapter(new MyAdapter(getSupportFragmentManager()));
        first_vp_id.addOnPageChangeListener(new PageChangeListener());

    }

    private void getFragmentsData() {

        list = new LinkedList<>();

        list.add(new First_Fragment());
        list.add(new BlankFragment());
        list.add(new BlankFragment());
        list.add(new BlankFragment());
    }

    private void initImageView() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        oneFour = screenW / 4;
    }

    private final class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_1: {
                    first_vp_id.setCurrentItem(0);
                    break;
                }
                case R.id.tv_2: {
                    first_vp_id.setCurrentItem(1);
                    break;
                }
                case R.id.tv_3: {
                    first_vp_id.setCurrentItem(2);
                    break;
                }
                case R.id.tv_4: {
                    first_vp_id.setCurrentItem(3);
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }


    private final class PageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            Animation animation = new TranslateAnimation(currentPostrion, oneFour * (position), 0, 0);//显然这个比较简洁，只有一行代码。
            currIndex = position;
            currentPostrion = oneFour * (position);
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(1000);
            cursor_iv_id.startAnimation(animation);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    private final class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


}
