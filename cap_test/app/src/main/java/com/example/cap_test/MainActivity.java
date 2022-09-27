package com.example.cap_test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    LinearLayout ll;
    Button b1;
    EditText txt;
    View.OnClickListener cl;

    public void Capture(View view, String title){
        if(view==null){
            System.out.println("ERROR NO view");
            return;
        }

        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        FileOutputStream fos;

        File uploadFolder = Environment.getExternalStoragePublicDirectory("/DCIM/Camera/");

        if(!uploadFolder.exists()){
            uploadFolder.mkdir();
        }

        String Str_Path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM?Camera/";

        try {
            fos = new FileOutputStream(Str_Path+title+".jpg");
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
        } catch (Exception e){
            e.printStackTrace();
        }
        MediaScanner ms = MediaScanner.newInstance(getApplicationContext());

        try {
            ms.mediaScanning(Str_Path+title+".jpg");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR: "+e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout capture_target_Layout = (LinearLayout)findViewById(R.id.capture_target); //캡쳐할 영역의 레이아웃
        Button capture_btn_Button = (Button)findViewById(R.id.capture_b);
        txt = (EditText) findViewById(R.id.text000);


        SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss"); //년,월,일,시간 포멧 설정
        Date time = new Date(); //파일명 중복 방지를 위해 사용될 현재시간
        String current_time = sdf.format(time); //String형 변수에 저장


        capture_btn_Button.setOnClickListener(new View.OnClickListener() { //캡쳐하기 클릭
            @Override
            public void onClick(View view) {
                txt.setText("버튼 눌림");
                Capture(capture_target_Layout,current_time + "_capture"); //지정한 Layout 영역 사진첩 저장 요청
            }
    });
    }


}