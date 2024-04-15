package com.example.du_an_1.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.du_an_1.Dao.ThongkeDao;
import com.example.du_an_1.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_quanlydoanhthu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_quanlydoanhthu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_quanlydoanhthu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_quanlydoanhthu.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_quanlydoanhthu newInstance(String param1, String param2) {
        Fragment_quanlydoanhthu fragment = new Fragment_quanlydoanhthu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Button bttungay,btdenngay,btdoanhthu;
    int mYear, mMonth,mDay;
    TextView tvdoanhthu;
    EditText edtungay,eddenngay;
    ThongkeDao thongkeDao;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_quanlydoanhthu, container, false);
        bttungay=view.findViewById(R.id.button25);
        btdenngay=view.findViewById(R.id.button26);
        btdoanhthu=view.findViewById(R.id.button27);
        edtungay=view.findViewById(R.id.editTextText15);
        eddenngay=view.findViewById(R.id.editTextText23);
        thongkeDao=new ThongkeDao(getContext());
        tvdoanhthu=view.findViewById(R.id.textView121);
        bttungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                mYear=calendar.get(Calendar.YEAR);
                mMonth=calendar.get(Calendar.MONTH);
                mDay=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(),0,onDateSetListenertungay,mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

        btdenngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                mYear=calendar.get(Calendar.YEAR);
                mMonth=calendar.get(Calendar.MONTH);
                mDay=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),0,onDateSetListenerdenngay,mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });

        btdoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eddenngay.getText().length()==0||edtungay.getText().length()==0){
                    Toast.makeText(getContext(), "Bạn chưa chọn ngày cần xem doanh thu", Toast.LENGTH_SHORT).show();
                }else {
                    String t = edtungay.getText().toString();
                    String tt = eddenngay.getText().toString();
                    int tong = thongkeDao.getDoanhThu(t, tt);
                    tvdoanhthu.setText("Doanh thu: " + String.valueOf(tong) + "VND");
                }
            }
        });

        return view;
    }
    DatePickerDialog.OnDateSetListener onDateSetListenertungay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int date) {
            mDay=date;
            mMonth=month;
            mYear=year;
            GregorianCalendar calendar=new GregorianCalendar(mYear, mMonth, mDay);

            edtungay.setText(simpleDateFormat.format(calendar.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener onDateSetListenerdenngay=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int date) {
            mDay=date;
            mMonth=month;
            mYear=year;
            GregorianCalendar calendar=new GregorianCalendar(mYear, mMonth, mDay);
            eddenngay.setText(simpleDateFormat.format(calendar.getTime()));
        }
    };
}