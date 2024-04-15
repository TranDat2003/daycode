package com.example.du_an_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.du_an_1.Model.Photo;
import com.example.du_an_1.R;

import java.util.ArrayList;

public class PhotoAdapter extends PagerAdapter {
    Context context;
    ArrayList<Photo> photoArrayList;

    public PhotoAdapter(Context context, ArrayList<Photo> photoArrayList) {
        this.context = context;
        this.photoArrayList = photoArrayList;
    }

    @Override
    public int getCount() {
        return photoArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo,container,false);
        ImageView imageView=view.findViewById(R.id.imageView12);
        Photo photo=photoArrayList.get(position);
        Glide.with(context).load(photo.getResourid()).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
