package com.example.du_an_1.Adapter;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.du_an_1.Fragment.Fragment_user_choxacnhan;
import com.example.du_an_1.Fragment.Fragment_user_dagiao;
import com.example.du_an_1.Fragment.Fragment_user_dahuy;
import com.example.du_an_1.Fragment.Fragment_user_danggiao;
import com.example.du_an_1.Fragment.Fragment_user_xacnhan;

public class TabuserAdapter extends FragmentStateAdapter {


    public TabuserAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
      if(position==0){
          return new Fragment_user_choxacnhan();
      }if(position==1){
            return new Fragment_user_xacnhan();
      }if(position==2){
            return new Fragment_user_danggiao();
      }if(position==3){
            return new Fragment_user_dagiao();
      }else{
            return new Fragment_user_dahuy();
      }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
