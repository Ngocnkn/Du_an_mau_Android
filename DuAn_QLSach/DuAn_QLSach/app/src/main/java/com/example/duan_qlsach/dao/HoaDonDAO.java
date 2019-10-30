package com.example.duan_qlsach.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan_qlsach.database.DatabaseHelper;
import com.example.duan_qlsach.model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    DatabaseHelper dt;
    SQLiteDatabase db;
    public static final String SQL = "CREATE TABLE HoaDon(" +
            "mahd INTEGER primary key autoincrement," +
            "ngay TEXT,tongtien int)";
    static final String TABLE_NAME = "HoaDon";

    public HoaDonDAO(Context context) {
        dt = new DatabaseHelper(context);
        db = dt.getWritableDatabase();
    }

    public boolean inserthoadon(HoaDon hd) {
        ContentValues values = new ContentValues();
        values.put("ngay", hd.getNgay());
        values.put("tongtien", hd.getTongtien());
        long a = db.insert(TABLE_NAME, null, values);
        if (a != -1) return true;
        return false;
    }

    public HoaDon getlasthoadon() {
        Cursor c = db.rawQuery("SELECT * from " + TABLE_NAME, null);
        HoaDon hd = new HoaDon();
        if(c.moveToLast()){
        hd.setMahd(c.getInt(0));
        hd.setNgay(c.getString(1));
        hd.setTongtien(c.getInt(2));}
        return hd;
    }

    public List<HoaDon> gethoadon() {
        List<HoaDon> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * from " + TABLE_NAME, null);
        while (c.moveToNext()) {
            HoaDon ct = new HoaDon();
            ct.setMahd(c.getInt(0));
            ct.setNgay(c.getString(1));
            ct.setTongtien(c.getInt(2));
            list.add(ct);
        }
        return list;
    }

    public int gettongtien(int mahd) {
        Cursor c = db.rawQuery("SELECT tongtien from " + TABLE_NAME + " where mahd=?", new String[]{String.valueOf(mahd)});
        int ketqua = 0;
        if (c.moveToFirst()) {
            ketqua = c.getInt(c.getColumnIndex("tongtien"));
        }
        return ketqua;
    }
}
