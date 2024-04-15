package com.example.du_an_1.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.LoaisanphamDao;
import com.example.du_an_1.Model.Loaisanpham;
import com.example.du_an_1.R;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LoaisanphamAdapter extends RecyclerView.Adapter<LoaisanphamAdapter.loaisp> {
    @NonNull
    Context context;
    ArrayList<Loaisanpham> loaispArrayList;

    LoaisanphamDao loaidao;
    public LoaisanphamAdapter(@NonNull Context context, ArrayList<Loaisanpham> loaispArrayList) {
        this.context = context;
        this.loaispArrayList = loaispArrayList;
        loaidao=new LoaisanphamDao(context);

    }
    @Override
    public loaisp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loaisp,null);

        return new loaisp(view);
    }

    @Override
    public void onBindViewHolder(@NonNull loaisp holder, int position) {

            holder.tvma.setText("Mã loại: " + loaispArrayList.get(position).ma);
            holder.tvname.setText("Tên loại: " + loaispArrayList.get(position).ten);


      holder.btsua.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Dialog dialog=new Dialog(view.getContext());
              dialog.setContentView(R.layout.dialog_loai_san_pham_sua);
              EditText ed;
              Button button;
              ed=dialog.findViewById(R.id.edtenloai);
              button=dialog.findViewById(R.id.btloaisp);
              button.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      Loaisanpham loaisp=new Loaisanpham();
                      String t=ed.getText().toString();
                      loaisp.ten=t;
                      loaisp.ma=loaispArrayList.get(position).ma;
                      loaidao.update(loaisp);
                      loaispArrayList=loaidao.getall();
                      notifyDataSetChanged();
                      dialog.dismiss();
                  }
              });
              dialog.show();
          }
      });
      holder.btxao.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
              builder.setIcon(R.drawable.ic_android_black_24dp);
              builder.setTitle("Thông báo");
              builder.setMessage("bạn có muốn xóa loại sản phẩm này?");
              builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                     dialogInterface.dismiss();
                  }
              });

              builder.setPositiveButton("Yes sir", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      String t=String.valueOf(loaispArrayList.get(position).ma);
                      loaidao.delete(t);
//                      loaispArrayList.removeItemsWithStatus1();
//                      loaispArrayList.refreshIndices();
                      removeItem(position);
                  }
              });
              builder.show();
          }
      });
    }

    @Override
    public int getItemCount() {

        return loaispArrayList.size();
    }


    class loaisp extends RecyclerView.ViewHolder {
        TextView tvma,tvname;
        ImageButton btxao,btsua;
        public loaisp(@NonNull View itemView) {
            super(itemView);
            tvma=itemView.findViewById(R.id.textView5);
            tvname=itemView.findViewById(R.id.textView6);
            btxao=itemView.findViewById(R.id.imageButton2);
            btsua=itemView.findViewById(R.id.imageButton);

        }
    }
    public void removeItem(int position) {
        loaispArrayList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}

//        loaispArrayList=loaidao.getall();
//        loaisanphamArrayList.clear();
//        for (Loaisanpham loaisanpham:loaispArrayList) {
//            if(loaisanpham.trangthai==0){
//                loaisanphamArrayList.add(loaisanpham);
//            }
//        }
//            holder.tvma.setText(String.valueOf("Mã loại:" + loaisanphamArrayList.get(position).ma));
//            holder.tvname.setText("Tên loại: " + loaisanphamArrayList.get(position).ten);
