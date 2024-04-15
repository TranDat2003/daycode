package com.example.du_an_1.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.KhachhangDao;
import com.example.du_an_1.Dao.LoaisanphamDao;
import com.example.du_an_1.Dao.SanphamDao;
import com.example.du_an_1.Model.Loaisanpham;
import com.example.du_an_1.Model.Sanpham;
import com.example.du_an_1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.sanpham> {
    Context context;
    ArrayList<Sanpham> sanphamArrayList;
    KhachhangDao khachhangdao;
    Sanpham sanpham;
    LoaisanphamDao loaidao;
    SanphamDao sanphamdao;
    EditText edma,edten,edgia,edmota,edsoluong,edimg;
    Button btok,bthuy;
    Spinner spinner;
    ArrayList<Loaisanpham> loaisanphamArrayList;
    int y;

    public SanphamAdapter(Context context, ArrayList<Sanpham> sanphamArrayList) {
        this.context = context;
        this.sanphamArrayList = sanphamArrayList;
        khachhangdao=new KhachhangDao(context);
        loaidao=new LoaisanphamDao(context);
        sanphamdao=new SanphamDao(context);
    }

    @NonNull
    @Override
    public sanpham onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham,null,false);
        return new sanpham(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sanpham holder, int position) {
//           holder.tvma.setText(sanphamArrayList.get(position).masp+"");
           loaidao=new LoaisanphamDao(context);
           sanpham=sanphamArrayList.get(position);
           holder.tvsoluong.setText("Số lượng" +sanphamArrayList.get(position).soluong+"");
           holder.tvname.setText("Tên sản phẩm: "+sanphamArrayList.get(position).tensp);
           holder.tvgia.setText("Giá sản phẩm: "+sanphamArrayList.get(position).gia+"");
           holder.tvthongtin.setText("Thông tin sản phẩm: "+sanphamArrayList.get(position).mota);
           Loaisanpham loaisp=loaidao.getID(String.valueOf(sanpham.maloai));

        Picasso.get().load(sanphamArrayList.get(position).img).into(holder.imageView);

        if(loaisp.trangthai==0){
            holder.tvloai.setText("Loại sản phẩm: "+loaisp.ten);
        }else{
            holder.tvloai.setText("Loại sản phẩm: N/A (Chưa xác định)");
        }

           holder.btsua.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Dialog dialog=new Dialog(view.getContext());
                   dialog.setContentView(R.layout.dialog_suasanpham);
                   Sanpham sanpham1=new Sanpham();
                   loaisanphamArrayList=loaidao.getall();
                   edma=dialog.findViewById(R.id.editTextText9);
                   edten=dialog.findViewById(R.id.editTextText6);
                   edmota=dialog.findViewById(R.id.editTextText8);
                   edgia=dialog.findViewById(R.id.editTextText7);
                   edsoluong=dialog.findViewById(R.id.editTextText19);
                   edimg=dialog.findViewById(R.id.editTextText33);
                   btok=dialog.findViewById(R.id.button9);
                   bthuy=dialog.findViewById(R.id.button10);


                   spinner=dialog.findViewById(R.id.spinner);
                   edgia.setText(sanphamArrayList.get(position).gia+"");
                   edten.setText(sanphamArrayList.get(position).tensp);
                   edma.setText(sanphamArrayList.get(position).masp+"");
                   edmota.setText(sanphamArrayList.get(position).mota);
                   edsoluong.setText(sanphamArrayList.get(position).soluong+"");
                   edimg.setText(sanphamArrayList.get(position).img);

                   LoaisanphamSpinner loaisanphamSpinner=new LoaisanphamSpinner(context,loaisanphamArrayList);
                   spinner.setAdapter(loaisanphamSpinner);
                   spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                       @Override
                       public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                         y=loaisanphamArrayList.get(i).ma;
                       }

                       @Override
                       public void onNothingSelected(AdapterView<?> adapterView) {

                       }
                   });

                   btok.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           sanpham1.maloai=y;
                           sanpham1.tensp=edten.getText().toString();
                           sanpham1.masp=sanphamArrayList.get(position).masp;
                           sanpham1.mota=edmota.getText().toString();
                           sanpham1.soluong=Integer.parseInt(edsoluong.getText().toString());
                           sanpham1.gia=Integer.parseInt(edgia.getText().toString());
                           sanpham1.img=edimg.getText().toString();
                           sanphamdao.update(sanpham1);
                           sanphamArrayList=sanphamdao.getall();
                           notifyDataSetChanged();
                           Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                           dialog.dismiss();
                       }
                   });
                   bthuy.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           dialog.dismiss();
                       }
                   });


                   dialog.show();
               }
           });
           holder.btxoa.setOnClickListener(new View.OnClickListener() {
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
                           String t=String.valueOf(sanphamArrayList.get(position).masp);
                           Sanpham sanpham1=new Sanpham();
                           sanpham1.trangthai=1;
                           sanpham1.masp=sanphamArrayList.get(position).masp;
                           sanphamdao.delete(sanpham1);
                           sanphamArrayList.remove(position);
                           notifyDataSetChanged();
                       }

           });
            builder.show();
    }});
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(context,""+sanphamArrayList.get(position).img,Toast.LENGTH_LONG).show();
        }
    });
    }

    @Override
    public int getItemCount() {
        return sanphamArrayList.size();
    }

    class sanpham extends RecyclerView.ViewHolder {
        TextView tvname,tvloai,tvgia,tvthongtin,tvsoluong;
        ImageButton btsua,btxoa;
        ImageView imageView;
        public sanpham(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView5);
            tvsoluong=itemView.findViewById(R.id.textView41);
            tvgia=itemView.findViewById(R.id.textView20);
            tvname=itemView.findViewById(R.id.textView18);
            tvthongtin=itemView.findViewById(R.id.textView21);
            tvloai=itemView.findViewById(R.id.textView19);
            btsua=itemView.findViewById(R.id.imageButton6);
            btxoa=itemView.findViewById(R.id.imageButton5);
        }
    }
}
