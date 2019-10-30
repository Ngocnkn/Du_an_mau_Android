package com.example.duan_qlsach.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duan_qlsach.R;
import com.example.duan_qlsach.adapter.DSSach;
import com.example.duan_qlsach.dao.SachDAO;
import com.example.duan_qlsach.model.Sach;

import java.util.List;

public class DSSachActivity extends AppCompatActivity {
    RecyclerView re;
    SearchView sv;
    DSSach adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsach);
        setTitle("SÁCH");
        anhxa();
        SachDAO sachDAO = new SachDAO(this);
        List<Sach> list = sachDAO.selectall();
        adapter = new DSSach(this, list);
        re.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        re.setHasFixedSize(true);
        re.setAdapter(adapter);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

//        SachDAO sachDAO1=new SachDAO(this);
//        for(int i=0;i<100;i++) {
//            int gia= (int) (30000+Math.round(Math.random()*10)/10*30000);
//            int soluong=(int)(1000+Math.round(Math.random()*10)/10*1000);
//            Sach s = new Sach();
//            s.setGia(gia);
//            s.setMasach(i);
//            s.setMatl(1);
//            s.setTensach("JAVA " + i);
//            s.setTacgia("QUYET");
//            s.setNxb("HA NOI");
//            s.setSoluong(soluong);
//            sachDAO1.insertSach(s);
//        }


    }

    private void anhxa() {
        re = findViewById(R.id.re);
        sv=findViewById(R.id.sv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ql_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.them:
                Intent i = new Intent(getBaseContext(), ThemSachActivity.class);
                startActivity(i);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
