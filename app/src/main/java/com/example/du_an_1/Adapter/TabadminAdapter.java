package com.example.du_an_1.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.du_an_1.Fragment.Fragment_choxacnhan;
import com.example.du_an_1.Fragment.Fragment_dagiao;
import com.example.du_an_1.Fragment.Fragment_dahuy;
import com.example.du_an_1.Fragment.Fragment_danggiao;
import com.example.du_an_1.Fragment.Fragment_daxacnhan;

public class TabadminAdapter extends FragmentStateAdapter {


    public TabadminAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0){
            return new Fragment_choxacnhan();
        }if(position==1){
            return new Fragment_daxacnhan();
        }if(position==2){
            return new Fragment_danggiao();
        }if(position==3){
            return new Fragment_dagiao();
        }else{
            return new Fragment_dahuy();
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
