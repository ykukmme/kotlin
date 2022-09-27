package com.example.mymall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    MyItem mi;
    ArrayList<MyItem> data;
    MyAdapter adapter;
    int total = 0;

    class MyAdapter extends BaseAdapter {
        Context con;
        MyAdapter(Context c){
            con = c;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater li = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = li.inflate(R.layout.myitem, viewGroup, false);
            }
            ImageView iv = (ImageView) view.findViewById(R.id.image);
            iv.setImageResource(data.get(i).img);

            TextView tv = (TextView) view.findViewById(R.id.comment);
            tv.setText(data.get(i).cmt);

            EditText et = (EditText) view.findViewById(R.id.price);
            et.setText(data.get(i).pri+"");

            RatingBar rb = (RatingBar) view.findViewById(R.id.rate);
            rb.setRating(data.get(i).man);

            Button bu = (Button) view.findViewById(R.id.buy);
            View.OnClickListener cl;
            final int ii = i;
            RadioGroup rg = (RadioGroup) view.findViewById(R.id.radio_group);

            cl = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    total = total + data.get(ii).pri;
                    Toast.makeText(getApplicationContext(), data.get(ii).pri + "원 구매 총 금액= " + total, Toast.LENGTH_LONG).show();
                }
            };
            bu.setOnClickListener(cl);
            return view;
        }
    }

    class MyItem{
        int img;
        String cmt;
        int pri;
        float man;
        MyItem(){}
        MyItem(int a, String b, int c, float d){
            img = a;
            cmt = b;
            pri = c;
            man = d;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<MyItem>();
        adapter = new MyAdapter(this);

        lv = (ListView) findViewById(R.id.list);

        mi = new MyItem();
        mi.img = R.drawable.tv;
        mi.cmt = "삼성 85인치 TV입니다\n아주 좋은 UHD TV입니다.\n짱좋음";
        mi.pri = 2100000;
        mi.man = 1.1f;
        data.add(mi);
        mi = new MyItem();
        mi.img = R.drawable.ref;
        mi.cmt = "LG 냉장고 입니다\n850리터의 대용량.\n짱좋음";
        mi.pri = 2400000;
        mi.man = 2.3f;
        data.add(mi);
        mi = new MyItem(R.drawable.wash, "LG 세탁기 입니다\n완전 대용량.\n짱좋음", 1000000, 3.1f);
        data.add(mi);
        mi = new MyItem();
        mi.img = R.drawable.rang;
        mi.cmt = "LG 전자레인지 입니다\n그릴전자레인지.\n짱좋음";
        mi.pri = 300000;
        mi.man = 4.5f;
        data.add(mi);

        lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(adapter);
    }
}