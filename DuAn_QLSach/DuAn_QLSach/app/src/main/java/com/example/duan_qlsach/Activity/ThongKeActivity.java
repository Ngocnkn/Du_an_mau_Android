package com.example.duan_qlsach.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.duan_qlsach.R;
import com.example.duan_qlsach.dao.HoaDonDAO;
import com.example.duan_qlsach.model.HoaDon;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ThongKeActivity extends AppCompatActivity {
    TextView tvhomnay, tvthangnay, tvnamnay;
    int homnay, thangnay, namnay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        tvhomnay = findViewById(R.id.tvhomnay);
        tvthangnay = findViewById(R.id.tvthangnay);
        tvnamnay = findViewById(R.id.tvnamnay);
        HoaDonDAO hoaDonDAO = new HoaDonDAO(this);
        List<HoaDon> list = hoaDonDAO.gethoadon();
        homnay = 0;
        thangnay = 0;
        namnay = 0;

        Date date1 = new Date();
        int daynow = date1.getDay();
        int monthnow = date1.getMonth();
        int yearnow = date1.getYear();
        for (int i = 0; i < list.size(); i++) {
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(list.get(i).getNgay());
                if (date.getDay() == daynow && date.getMonth() == monthnow && date.getYear() == yearnow) {
                    homnay += list.get(i).getTongtien();
                }
                if (date.getMonth() == monthnow && date.getYear() == yearnow) {
                    thangnay += list.get(i).getTongtien();
                }
                if (date.getYear() == yearnow) {
                    namnay += list.get(i).getTongtien();
                }
            } catch (ParseException e) {
                // ... handle parsing exception
            }

            tvhomnay.setText("Hôm nay: " + homnay);
            tvthangnay.setText("Tháng nay: " + thangnay);
            tvnamnay.setText("Năm nay: " + namnay);

        }

    }
}
