package com.example.bitmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MyView mv;
    Bitmap bm;

    class MyView extends View {

        Paint p;

        MyView(Context c){
            super(c);
            p = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            p.setColor(Color.RED);
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(1);

            //canvas.drawCircle(240, 360, 100, p);
            //canvas.drawLine(10, 20, 200, 300, p);
            //bm = BitmapFactory.decodeResource(getResources(), R.drawable.sam);
            bm = BitmapFactory.decodeFile("/data/data/com.example.bitmap/sam1.jpg");

            int w, h, sw, sh;
            Rect r1, r2;

            w = bm.getWidth();
            h = bm.getHeight();
            sw = this.getWidth();
            sh = this.getHeight();

           /*
           중앙으로 오게 하기
            float x, y;
            x = (sw-w)/2;
            y = (sh-h)/2;
            */

            r1 = new Rect(0, h/2, w/2, h); //그림의 크기를 조정하는 사각형
            r2 = new Rect(sw/2, sh/2, sw, sh); //위치를 조정하는 사각형

            String s;
            s = String.format("너비= %d  높이= %d  화면 너비= %d  화면 높이= %d", w, h, sw, sh);
            canvas.drawText(s, 10, 700, p);
            //canvas.drawBitmap(bm, r1, r2, p);
            //canvas.rotate(30); 0, 0 기준으로 30도 회전
            //canvas.rotate(30, w/2, h/2); // 해당 좌표를 기준으로 30도 회전
            //canvas.scale(2, 3); //x축을 2배 y축을 3배 확대해서 그린다.
            //canvas.scale(0.2f, 0.3f); //x축을 0.2배 y축을 0.3배 하여 그린다.
            //canvas.skew(0.2f, 0.3f); //x축과 y축을 해당 수치만큼 찌그러트린다. 0.2f = 20%
            //canvas.translate(100, 200); //을 주축으로 그리기
            canvas.drawBitmap(bm, 0, 0, p);
            //canvas.drawBitmap(bm, r1, r2, p); 이렇게 면 오른쪽 하단에 그림의 3번 구역이 출력됨
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mv = new MyView(this);
        setContentView(mv);
    }
}