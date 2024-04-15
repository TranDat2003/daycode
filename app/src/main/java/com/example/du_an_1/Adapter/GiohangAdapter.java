package com.example.du_an_1.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.DonhangDao;
import com.example.du_an_1.Dao.GiohangDao;
import com.example.du_an_1.Dao.KhachhangDao;
import com.example.du_an_1.Dao.LoaisanphamDao;
import com.example.du_an_1.Dao.SanphamDao;
import com.example.du_an_1.Model.Donhang;
import com.example.du_an_1.Model.Giohang;
import com.example.du_an_1.Model.Khachhang;
import com.example.du_an_1.Model.Loaisanpham;
import com.example.du_an_1.Model.Sanpham;
import com.example.du_an_1.R;
import com.squareup.picasso.Picasso;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GiohangAdapter extends RecyclerView.Adapter<GiohangAdapter.giohang>{
    Context context;
    ArrayList<Giohang> giohangArrayList;
    GiohangDao giohangDao;
    SanphamDao sanphamDao;
    TextView ngay;
    EditText edsdt,edten,edadress;
    Button btok,bthuy;
    int y=0,g;

    public GiohangAdapter(Context context, ArrayList<Giohang> giohangArrayList) {
        this.context = context;
        this.giohangArrayList = giohangArrayList;
        giohangDao=new GiohangDao(context);
    }

    @NonNull
    @Override
    public giohang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang,null,false);
        return new giohang(view);
    }

    @Override
    public void onBindViewHolder(@NonNull giohang holder, int position) {

        sanphamDao=new SanphamDao(context);
        Sanpham sanpham=sanphamDao.getid(String.valueOf(giohangArrayList.get(position).masp));
        LoaisanphamDao loaisanphamDao=new LoaisanphamDao(context);
        Loaisanpham loaisanpham=loaisanphamDao.getID(String.valueOf(sanpham.maloai));
        holder.tvname.setText("Tên sản phẩm: "+sanpham.tensp);
        holder.tvgia.setText("Giá sản phẩm: "+sanpham.gia+"");
        holder.tvloai.setText("Loại sản phẩm: "+loaisanpham.ten);
        holder.tvsoluong.setText("  "+giohangArrayList.get(position).soluong+"  ");
        Picasso.get().load(sanpham.img).into(holder.imageView);

        holder.btthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sanpham sanpham=sanphamDao.getid(String.valueOf(giohangArrayList.get(position).masp));
                g= sanpham.soluong;
                int m;
                m=giohangArrayList.get(position).soluong;
                m++;
                if(m>g){
                    Toast.makeText(context, "Đã đạt số lượng tối đa có thể đặt", Toast.LENGTH_SHORT).show();
                }else{
                    holder.tvsoluong.setText("  "+m+"  ");
                    Giohang giohang=new Giohang();
                    giohang.soluong=m;
                    giohang.makh=giohangArrayList.get(position).makh;
                    giohang.masp=giohangArrayList.get(position).masp;
                    giohang.magh=giohangArrayList.get(position).magh;
                    giohangDao.update(giohang);
                    giohangArrayList.get(position).soluong = m;

                    notifyDataSetChanged();
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+giohangArrayList.get(position).makh+"", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btgiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sanpham sanpham=sanphamDao.getid(String.valueOf(giohangArrayList.get(position).masp));
                g= sanpham.soluong;
                int m;
                m=giohangArrayList.get(position).soluong;
                m--;
                if(m<=0){
                    Toast.makeText(context, "Đã đạt số lượng tối đa có thể đặt", Toast.LENGTH_SHORT).show();
                }else{
                    holder.tvsoluong.setText("  "+m+"  ");
                    Giohang giohang=new Giohang();
                    giohang.soluong=m;
                    giohang.makh=giohangArrayList.get(position).makh;
                    giohang.masp=giohangArrayList.get(position).masp;
                    giohang.magh=giohangArrayList.get(position).magh;
                    giohangDao.update(giohang);
                    giohangArrayList.get(position).soluong = m;

                    notifyDataSetChanged();
                }
            }
        });

        holder.bthuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                giohangDao.delete(String.valueOf(giohangArrayList.get(position).magh));
                giohangArrayList.remove(position);
                notifyDataSetChanged();
            }
        });


        holder.btok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sanpham.soluong==0){
                    Toast.makeText(context, "Sản phẩm bạn chọn mua dã hết hãy chờ chúng tôi ", Toast.LENGTH_SHORT).show();
                }else{
                Dialog dialog=new Dialog(view.getContext());
                dialog.setContentView(R.layout.dialog_them_don_hang);
                SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");

                RadioGroup radioGroup = dialog.findViewById(R.id.radioGroup);
                RadioButton radioButton1 = dialog.findViewById(R.id.radioButton);
                RadioButton radioButton2 = dialog.findViewById(R.id.radioButton2);
                ngay=dialog.findViewById(R.id.textView62);

                edsdt=dialog.findViewById(R.id.editTextText16);
                edten=dialog.findViewById(R.id.editTextText17);
                edadress=dialog.findViewById(R.id.editTextText18);
                btok=dialog.findViewById(R.id.button18);
                bthuy=dialog.findViewById(R.id.button19);
                ngay.setText("Ngày"+dateFormat.format(new java.util.Date()));
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if(i==R.id.radioButton){
                          y=1;
                        }if(i==R.id.radioButton2){
                            y=2;
                        }
                    }
                });



                btok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(edten.getText().length()==0||edsdt.getText().length()==0||edadress.getText().length()==0||y==0){
                            Toast.makeText(context, "bạn không được để trống", Toast.LENGTH_SHORT).show();
                        }else{
                            if(TextUtils.isDigitsOnly(edsdt.getText().toString())){
                                if(sanpham.soluong<=0){
                                    Toast.makeText(context, "sản phẩm này đã hết", Toast.LENGTH_SHORT).show();
                                }else{
                                    Donhang donhang=new Donhang();
                                    DonhangDao donhangDao=new DonhangDao(context);
                                    donhang.ten=edten.getText().toString();
                                    donhang.diachi=edadress.getText().toString();
                                    donhang.sdt=Integer.parseInt(edsdt.getText().toString());
                                    donhang.ngay=new Date(new java.util.Date().getTime());
                                    donhang.phuongthuc=y;
                                    donhang.magiohang=giohangArrayList.get(position).magh;
                                    donhang.gia=sanpham.gia*giohangArrayList.get(position).soluong;
                                    donhang.trangthai=0;
                                    donhang.thanhtoan=0;
                                    donhangDao.insert(donhang);
                                    Toast.makeText(context, "Bạn đã đặt hàng thành công", Toast.LENGTH_SHORT).show();

                                    Sanpham sanpham1=new Sanpham();
                                    sanpham1.soluong=sanpham.soluong-giohangArrayList.get(position).soluong;
                                    sanpham1.masp=giohangArrayList.get(position).masp;
                                    sanphamDao.updatee(sanpham1);

                                    Giohang giohang=new Giohang();
                                    giohang.trangthai=1;
                                    giohang.magh=giohangArrayList.get(position).magh;
                                    giohangDao.updatee(giohang);
                                    giohangArrayList.remove(position);
                                    giohangDao.check(String.valueOf(giohang.masp),String.valueOf(giohang.trangthai));

                                    notifyDataSetChanged();
                                    dialog.dismiss();
                                }

                            }else{
                                Toast.makeText(context, "bạn nhập sai định dạng ",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
                bthuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }}
        });
    }

    @Override
    public int getItemCount() {
        return giohangArrayList.size();
    }

    class giohang extends RecyclerView.ViewHolder {
        TextView tvname,tvgia,tvloai,tvsoluong;
        Button btok,bthuy,bt;
        ImageButton btthem,btgiam;
        ImageView imageView;
        CheckBox checkBox;
        public giohang(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView9);
            checkBox=itemView.findViewById(R.id.checkBox2);
            tvname=itemView.findViewById(R.id.textView45);
            tvloai=itemView.findViewById(R.id.textView46);
            tvgia=itemView.findViewById(R.id.textView47);
            btok=itemView.findViewById(R.id.button15);
            bthuy=itemView.findViewById(R.id.button16);
            tvsoluong=itemView.findViewById(R.id.textView58);
            btgiam=itemView.findViewById(R.id.imageButton8);
            btthem=itemView.findViewById(R.id.imageButton9);
            bt=itemView.findViewById(R.id.button23);

        }
    }
}
