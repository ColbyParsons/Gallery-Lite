package com.example.a20665652.fotag20665652;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.ArrayList;

public class PhotoActivity extends AppCompatActivity {

    public int value;
    public int index;
    public String url;
    public ArrayList<Integer> vals;
    public int filter;


    private class PhotoTask extends AsyncTask<String, Void, Bitmap> {
        ImageView image;

        public PhotoTask(ImageView image) {
            this.image = image;
        }

        protected Bitmap doInBackground(String... url) {
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(url[0]).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }

        protected void onPostExecute(Bitmap result) {
            image.setImageBitmap(result);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("value",value);
        outState.putInt("index",index);
        outState.putString("url",url);
        outState.putInt("filter",filter);
        outState.putIntegerArrayList("vals",vals);
    }


    //setContentView(R.layout.activity_main);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Log.d("PHOTO","MADE IT HERE");
        if(savedInstanceState == null) {
            Intent intent = this.getIntent();
            value = intent.getIntExtra("value", -1);
            index = intent.getIntExtra("index", -1);
            url = intent.getStringExtra("url");
            vals = intent.getIntegerArrayListExtra("vals");
            filter = intent.getIntExtra("filter",-1);
        }else{
            value = savedInstanceState.getInt("value");
            index = savedInstanceState.getInt("index");
            url = savedInstanceState.getString("url");
            vals = savedInstanceState.getIntegerArrayList("vals");
            filter = savedInstanceState.getInt("filter");
        }

        if(value == -1||index == -1){
            return;
        }
        Log.d("PHOTO","MADE IT HERE2");
        ImageView newImage = (ImageView) findViewById(R.id.pframe);
        new PhotoTask(newImage).execute(url);
        final Button none = (Button) findViewById(R.id.pnone);
        final Button back = (Button) findViewById(R.id.back);
        final Button star1 = (Button) findViewById(R.id.pstar1);
        final Button star2 = (Button) findViewById(R.id.pstar2);
        final Button star3 = (Button) findViewById(R.id.pstar3);
        final Button star4 = (Button) findViewById(R.id.pstar4);
        final Button star5 = (Button) findViewById(R.id.pstar5);
        Log.d("PHOTO","MADE IT HERE3");
        if(value == 1){
            star1.setText("★");
            star2.setText("☆");
            star3.setText("☆");
            star4.setText("☆");
            star5.setText("☆");
        }else if(value == 2){
            star1.setText("★");
            star2.setText("★");
            star3.setText("☆");
            star4.setText("☆");
            star5.setText("☆");
        }else if(value == 3){
            star1.setText("★");
            star2.setText("★");
            star3.setText("★");
            star4.setText("☆");
            star5.setText("☆");
        }else if(value == 4){
            star1.setText("★");
            star2.setText("★");
            star3.setText("★");
            star4.setText("★");
            star5.setText("☆");
        }else if(value == 5){
            star1.setText("★");
            star2.setText("★");
            star3.setText("★");
            star4.setText("★");
            star5.setText("★");
        }

        Log.d("PHOTO","MADE IT HERE4");

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here ★ ☆
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("value",value);
                i.putExtra("index",index);
                i.putExtra("filter",filter);
                i.putIntegerArrayListExtra("vals",vals);
                startActivity(i);
            }
        });

        none.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here ★ ☆
                star1.setText("☆");
                star2.setText("☆");
                star3.setText("☆");
                star4.setText("☆");
                star5.setText("☆");
                value = 0;
            }
        });

        star1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here ★ ☆
                star1.setText("★");
                star2.setText("☆");
                star3.setText("☆");
                star4.setText("☆");
                star5.setText("☆");
                value = 1;
            }
        });

        star2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here ★ ☆
                star1.setText("★");
                star2.setText("★");
                star3.setText("☆");
                star4.setText("☆");
                star5.setText("☆");
                value = 2;
            }
        });

        star3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here ★ ☆
                star1.setText("★");
                star2.setText("★");
                star3.setText("★");
                star4.setText("☆");
                star5.setText("☆");
                value = 3;
            }
        });

        star4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here ★ ☆
                star1.setText("★");
                star2.setText("★");
                star3.setText("★");
                star4.setText("★");
                star5.setText("☆");
                value = 4;
            }
        });

        star5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here ★ ☆
                star1.setText("★");
                star2.setText("★");
                star3.setText("★");
                star4.setText("★");
                star5.setText("★");
                value = 5;
            }
        });
        Log.d("PHOTO","MADE IT HERE5");
    }

}
