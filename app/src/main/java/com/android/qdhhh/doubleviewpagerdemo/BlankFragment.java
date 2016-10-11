package com.android.qdhhh.doubleviewpagerdemo;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.qdhhh.doubleviewpagerdemo.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.cpoopc.scrollablelayoutlib.ScrollableHelper;

import java.io.PipedOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements ScrollableHelper.ScrollableContainer {

    private RecyclerView lv_id;
    private View view;

    private List<String> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_blank, container, false);

        lv_id = (RecyclerView) view.findViewById(R.id.lv_id);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        setListView();

        super.onActivityCreated(savedInstanceState);
    }

    private void setListView() {

        getData();
        lv_id.setLayoutManager(new GridLayoutManager(getContext(), 1));
        lv_id.setAdapter(new MyAdapter());

    }

    private void getData() {

        list = new LinkedList<>();
        list.add("http://p3.so.qhmsg.com/sdr/720_1080_/t01d4b941b38b7cca7d.jpg");
        list.add("http://p0.so.qhmsg.com/sdr/720_1080_/t01c2345b0a461928af.jpg");
        list.add("http://p0.so.qhmsg.com/sdr/720_1080_/t0102e22db3f17f755a.jpg");
        list.add("http://p3.so.qhmsg.com/sdr/1620_1080_/t018650cc445938d97d.jpg");
        list.add("http://p3.so.qhmsg.com/sdr/719_1080_/t016027774780be365d.jpg");
        list.add("http://p1.so.qhmsg.com/sdr/719_1080_/t0100f16f9b1a9174fb.jpg");
        list.add("http://p2.so.qhmsg.com/sdr/1619_1080_/t019d150016e25a4ba2.jpg");
        list.add("http://p2.so.qhmsg.com/sdr/720_1080_/t01f1ad79da36f90057.jpg");
    }

    @Override
    public View getScrollableView() {
        return lv_id;
    }


    private final class MyAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(View.inflate(getContext(), R.layout.item_blank, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Glide.with(getContext()).load(list.get(position)).into(((MyViewHolder) holder).blank_iv_id);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private final class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView blank_iv_id = null;

        public MyViewHolder(View itemView) {
            super(itemView);
            blank_iv_id = (ImageView) itemView.findViewById(R.id.blank_iv_id);
        }
    }


}
