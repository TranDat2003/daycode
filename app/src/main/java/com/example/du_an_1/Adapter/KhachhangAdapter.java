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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.KhachhangDao;
import com.example.du_an_1.Model.Khachhang;
import com.example.du_an_1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class KhachhangAdapter extends RecyclerView.Adapter<KhachhangAdapter.khachhang>{
    Context context;
    ArrayList<Khachhang> khachhangArrayList;
    KhachhangDao khachhangdao;

    public KhachhangAdapter(Context context, ArrayList<Khachhang> khachhangArrayList) {
        this.context = context;
        this.khachhangArrayList = khachhangArrayList;
        khachhangdao=new KhachhangDao(context);
    }

    @NonNull
    @Override
    public khachhang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khachhang,null,false);
        return new khachhang(view);
    }

    EditText edma,edten,edsdt,edaddress,ednamsinh;
    Button btok,bthuy;
    @Override
    public void onBindViewHolder(@NonNull khachhang holder, int position) {
            holder.tvma.setText("Mã khách hàng: "+khachhangArrayList.get(position).makh+"");
            holder.tvten.setText("Họ và tên: "+khachhangArrayList.get(position).name);
            holder.tvdiachi.setText("Địa chỉ: "+khachhangArrayList.get(position).address);
            holder.tvsdt.setText("Số điện thoại: "+khachhangArrayList.get(position).phone);
            holder.tvmk.setText("Mật khẩu: "+khachhangArrayList.get(position).mk);
            holder.btxoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                    builder.setIcon(R.drawable.ic_android_black_24dp);
                    builder.setTitle("Thông báo");
                    builder.setMessage("bạn có muốn xóa người dùng này?");
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    builder.setPositiveButton("Yes sir", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Khachhang khachhang=new Khachhang();
                            khachhang.makh=khachhangArrayList.get(position).makh;
                            khachhang.trangthai=1;
                            khachhangdao.delete(khachhang);
                            khachhangArrayList.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                  builder.show();

                }
            });
            holder.btsua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog=new Dialog(view.getContext());
                    SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
                    dialog.setContentView(R.layout.dialog_suakhachhang);
                    edma=dialog.findViewById(R.id.editTextText);
                    edten=dialog.findViewById(R.id.editTextText2);
                    edsdt=dialog.findViewById(R.id.editTextText4);
                    edaddress=dialog.findViewById(R.id.editTextText5);
                    ednamsinh=dialog.findViewById(R.id.editTextText34);
                    btok=dialog.findViewById(R.id.button7);
                    bthuy=dialog.findViewById(R.id.button8);
                    edma.setText(khachhangArrayList.get(position).makh+"");
                    edten.setText(khachhangArrayList.get(position).name);

                    edsdt.setText(khachhangArrayList.get(position).phone);
                    edaddress.setText(khachhangArrayList.get(position).address);
                    ednamsinh.setText(dateFormat.format(khachhangArrayList.get(position).ngaysinh));
                    btok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                           Khachhang khachhang=new Khachhang();
                           khachhang.makh=Integer.parseInt(edma.getText().toString());
                           khachhang.name=edten.getText().toString();
                           khachhang.address=edaddress.getText().toString();
                           khachhang.phone=edsdt.getText().toString();
                            try {
                                khachhang.ngaysinh=dateFormat.parse(ednamsinh.getText().toString());
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                            khachhangdao.update(khachhang);
                           khachhangArrayList=khachhangdao.getall();
                           notifyDataSetChanged();
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
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, ""+khachhangArrayList.get(position).trangthai+"", Toast.LENGTH_SHORT).show();
                }
            });
    }

    @Override
    public int getItemCount() {
        return khachhangArrayList.size();
    }

    class khachhang extends RecyclerView.ViewHolder {
        TextView tvma,tvten,tvsdt,tvdiachi,tvmk;
        ImageButton btxoa,btsua;
        public khachhang(@NonNull View itemView) {
            super(itemView);
            tvma=itemView.findViewById(R.id.textView7);
            tvten=itemView.findViewById(R.id.textView8);
            tvsdt=itemView.findViewById(R.id.textView9);
            tvdiachi=itemView.findViewById(R.id.textView10);
            tvmk=itemView.findViewById(R.id.textView11);
            btxoa=itemView.findViewById(R.id.imageButton3);
            btsua=itemView.findViewById(R.id.imageButton4);
        }
    }
}
