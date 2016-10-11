package com.android.qdhhh.doubleviewpagerdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.cpoopc.scrollablelayoutlib.ScrollableHelper;
import com.cpoopc.scrollablelayoutlib.ScrollableLayout;

import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class First_Fragment extends Fragment {

    private ScrollableLayout scroll_id;

    private ViewPager second_vp_id;
    private TabLayout tablayout_id;
    private ImageView img_iv_id;

    private View view;

    private List<String> titles;
    private List<Fragment> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first, container, false);

        second_vp_id = (ViewPager) view.findViewById(R.id.second_vp_id);
        tablayout_id = (TabLayout) view.findViewById(R.id.tablayout_id);
        img_iv_id = (ImageView) view.findViewById(R.id.img_iv_id);
        scroll_id = (ScrollableLayout) view.findViewById(R.id.scroll_id);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        setImageView();

        getData();

        setViewPager();

        super.onActivityCreated(savedInstanceState);
    }

    private void setImageView() {

        RelativeLayout.LayoutParams linearParams =
                (RelativeLayout.LayoutParams) img_iv_id.getLayoutParams();
        linearParams.width = getScreenMaxWidth(0);
        int maxHeight = (int) (getScreenMaxWidth(0) / 1.50);
        linearParams.height = maxHeight;
        img_iv_id.setLayoutParams(linearParams);
        img_iv_id.setImageResource(R.drawable.img);
        scroll_id.setClickHeadExpand(maxHeight);
    }

    private void setViewPager() {

        getFragmentData();

        second_vp_id.setAdapter(new MyAdapter(getChildFragmentManager()));

        tablayout_id.setupWithViewPager(second_vp_id);

        second_vp_id.setOverScrollMode(ViewPager.OVER_SCROLL_NEVER);
        second_vp_id.setOffscreenPageLimit(titles.size());
        second_vp_id.setCurrentItem(0);


        scroll_id.getHelper().setCurrentScrollableContainer((ScrollableHelper.ScrollableContainer) list.get(0));
        second_vp_id.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                scroll_id.getHelper().setCurrentScrollableContainer((ScrollableHelper.ScrollableContainer) list.get(arg0));
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }

    private void getFragmentData() {

        list = new LinkedList<>();

        for (int i = 0; i < titles.size(); i++) {
            list.add(new BlankFragment());
        }
    }


    private void getData() {

        titles = new LinkedList<>();

        titles.add("HOT");
        titles.add("纯色");
        titles.add("花卉");
        titles.add("线条感");
        titles.add("小清新");
        titles.add("黑白灰");
        titles.add("童趣");
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
            return titles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

    public int getScreenMaxWidth(int paramInt) {
        Object localObject = new DisplayMetrics();
        try {
            DisplayMetrics localDisplayMetrics =
                    getActivity().getApplicationContext().getResources().getDisplayMetrics();
            localObject = localDisplayMetrics;
            return ((DisplayMetrics) localObject).widthPixels - dip2px(getActivity(), paramInt);
        } catch (Exception localException) {
            while (true) {
                localException.printStackTrace();
                ((DisplayMetrics) localObject).widthPixels = 640;
            }
        }
    }

    public int dip2px(Context context, int dipValue) {
        if (context != null) {
            float reSize = context.getResources().getDisplayMetrics().density;
            return (int) ((dipValue * reSize) + 0.5);
        }
        return dipValue;
    }
}
