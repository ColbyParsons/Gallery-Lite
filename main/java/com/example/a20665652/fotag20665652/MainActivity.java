package com.example.a20665652.fotag20665652;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Button;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Integer> photoArr = new ArrayList<>();
    public String dir = "https://www.student.cs.uwaterloo.ca/~cs349/f18/assignments/images/";
    public String[] photoUrls = {"bunny.jpg","chinchilla.jpg","doggo.jpg","hamster.jpg","husky.jpg","kitten.png","loris.jpg","puppy.jpg","redpanda.jpg","sleepy.png"};
    public int loading = 0;
    public int currFilter = 0;

    //Took this framework from stackoverflow
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
                loading--;
            }
            return bmp;
        }

        protected void onPostExecute(Bitmap result) {
            image.setImageBitmap(result);
            loading--;
        }
    }
    //ends here

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList("stars",photoArr);
        outState.putInt("filter",currFilter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = this.getIntent();
        int retval = intent.getIntExtra("value", -1);
        int retindex = intent.getIntExtra("index", -1);
        int retfilter = intent.getIntExtra("filter", -1);
        ArrayList<Integer> retArr = intent.getIntegerArrayListExtra("vals");
        if(savedInstanceState != null || (retindex != -1 && retval != -1&&retfilter!=-1)){
            GridLayout photos = (GridLayout) findViewById(R.id.photos);

            if(savedInstanceState != null) {
                photoArr = savedInstanceState.getIntegerArrayList("stars");
                currFilter = savedInstanceState.getInt("filter");
            }
            Button s1 = (Button) findViewById(R.id.star1);
            Button s2 = (Button) findViewById(R.id.star2);
            Button s3 = (Button) findViewById(R.id.star3);
            Button s4 = (Button) findViewById(R.id.star4);
            Button s5 = (Button) findViewById(R.id.star5);

            if(retindex != -1 && retval != -1){
                photoArr = retArr;
                currFilter = retfilter;
                photoArr.set(retindex,retval);
            }
            if(currFilter == 0){
                s1.setText("☆");
                s2.setText("☆");
                s3.setText("☆");
                s4.setText("☆");
                s5.setText("☆");
            }else if(currFilter == 1){
                s1.setText("★");
                s2.setText("☆");
                s3.setText("☆");
                s4.setText("☆");
                s5.setText("☆");
            }else if(currFilter == 2){
                s1.setText("★");
                s2.setText("★");
                s3.setText("☆");
                s4.setText("☆");
                s5.setText("☆");
            }else if(currFilter == 3){
                s1.setText("★");
                s2.setText("★");
                s3.setText("★");
                s4.setText("☆");
                s5.setText("☆");
            }else if(currFilter == 4){
                s1.setText("★");
                s2.setText("★");
                s3.setText("★");
                s4.setText("★");
                s5.setText("☆");
            }else if(currFilter == 5){
                s1.setText("★");
                s2.setText("★");
                s3.setText("★");
                s4.setText("★");
                s5.setText("★");
            }
            //ADD FILTER BEHAVIOUR
            if(photoArr.size() != 0){
            int index = 0;
            for(String file : photoUrls) {
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                inflater.inflate(R.layout.image_view, photos);
                final View child = photos.getChildAt(index);
                final Button none = (Button) child.findViewById(R.id.none);
                final Button star1 = (Button) child.findViewById(R.id.istar1);
                final Button star2 = (Button) child.findViewById(R.id.istar2);
                final Button star3 = (Button) child.findViewById(R.id.istar3);
                final Button star4 = (Button) child.findViewById(R.id.istar4);
                final Button star5 = (Button) child.findViewById(R.id.istar5);
                final ImageView newImage = (ImageView) child.findViewById(R.id.frame);
                if(photoArr.get(index) == 1){
                    star1.setText("★");
                    star2.setText("☆");
                    star3.setText("☆");
                    star4.setText("☆");
                    star5.setText("☆");
                }else if(photoArr.get(index) == 2){
                    star1.setText("★");
                    star2.setText("★");
                    star3.setText("☆");
                    star4.setText("☆");
                    star5.setText("☆");
                }else if(photoArr.get(index) == 3){
                    star1.setText("★");
                    star2.setText("★");
                    star3.setText("★");
                    star4.setText("☆");
                    star5.setText("☆");
                }else if(photoArr.get(index) == 4){
                    star1.setText("★");
                    star2.setText("★");
                    star3.setText("★");
                    star4.setText("★");
                    star5.setText("☆");
                }else if(photoArr.get(index) == 5){
                    star1.setText("★");
                    star2.setText("★");
                    star3.setText("★");
                    star4.setText("★");
                    star5.setText("★");
                }

                loading++;
                new PhotoTask(newImage).execute(dir+file);
                final int refIndex = index;
                final String refFile = file;
                newImage.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // your handler code here ★ ☆
                        Intent i = new Intent(getApplicationContext(),PhotoActivity.class);
                        i.putExtra("value",photoArr.get(refIndex));
                        i.putExtra("index",refIndex);
                        i.putExtra("url",dir+refFile);
                        i.putIntegerArrayListExtra("vals",photoArr);
                        i.putExtra("filter",currFilter);
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
                        photoArr.set(refIndex, 0);
                        if(currFilter != 0){
                            child.setVisibility(View.GONE);
                        }
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
                        photoArr.set(refIndex, 1);
                        if(currFilter != 1 && currFilter != 0){
                            child.setVisibility(View.GONE);
                        }
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
                        photoArr.set(refIndex, 2);
                        if(currFilter != 2 && currFilter != 0){
                            child.setVisibility(View.GONE);
                        }
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
                        photoArr.set(refIndex, 3);
                        if(currFilter != 3 && currFilter != 0){
                            child.setVisibility(View.GONE);
                        }
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
                        photoArr.set(refIndex, 4);
                        if(currFilter != 4 && currFilter != 0){
                            child.setVisibility(View.GONE);
                        }
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
                        photoArr.set(refIndex, 5);
                        if(currFilter != 5 && currFilter != 0){
                            child.setVisibility(View.GONE);
                        }
                    }
                });
                index++;
            }
            }
            for(int i = 0; i < photoArr.size();i++){
                View child = photos.getChildAt(i);
                if(currFilter == 0){
                    child.setVisibility(View.VISIBLE);
                }else if(photoArr.get(i) == currFilter) {
                    child.setVisibility(View.VISIBLE);
                }else{
                    child.setVisibility(View.GONE);
                }
            }
        }


        Button clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(loading != 0)return;
                GridLayout photos = (GridLayout) findViewById(R.id.photos);
                photos.removeAllViews();
                photoArr.clear();
                currFilter = 0;
                Button s1 = (Button) findViewById(R.id.star1);
                Button s2 = (Button) findViewById(R.id.star2);
                Button s3 = (Button) findViewById(R.id.star3);
                Button s4 = (Button) findViewById(R.id.star4);
                Button s5 = (Button) findViewById(R.id.star5);
                s1.setText("☆");
                s2.setText("☆");
                s3.setText("☆");
                s4.setText("☆");
                s5.setText("☆");
            }
        });

        Button load = (Button) findViewById(R.id.load);
        load.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(loading != 0)return;
                GridLayout photos = (GridLayout) findViewById(R.id.photos);
                photos.removeAllViews();
                currFilter = 0;
                Button s1 = (Button) findViewById(R.id.star1);
                Button s2 = (Button) findViewById(R.id.star2);
                Button s3 = (Button) findViewById(R.id.star3);
                Button s4 = (Button) findViewById(R.id.star4);
                Button s5 = (Button) findViewById(R.id.star5);
                s1.setText("☆");
                s2.setText("☆");
                s3.setText("☆");
                s4.setText("☆");
                s5.setText("☆");

                int index = 0;
                for(String file : photoUrls) {
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    inflater.inflate(R.layout.image_view, photos);
                    final View child = photos.getChildAt(index);
                    final Button none = (Button) child.findViewById(R.id.none);
                    final Button star1 = (Button) child.findViewById(R.id.istar1);
                    final Button star2 = (Button) child.findViewById(R.id.istar2);
                    final Button star3 = (Button) child.findViewById(R.id.istar3);
                    final Button star4 = (Button) child.findViewById(R.id.istar4);
                    final Button star5 = (Button) child.findViewById(R.id.istar5);
                    ImageView newImage = (ImageView) child.findViewById(R.id.frame);

                    loading++;
                    new PhotoTask(newImage).execute(dir+file);

                    photoArr.add(0);
                    final int refIndex = index;

                    final String refFile = file;
                    newImage.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            // your handler code here ★ ☆
                            Intent i = new Intent(getApplicationContext(),PhotoActivity.class);
                            i.putExtra("value",photoArr.get(refIndex));
                            i.putExtra("index",refIndex);
                            i.putExtra("url",dir+refFile);
                            i.putIntegerArrayListExtra("vals",photoArr);
                            i.putExtra("filter",currFilter);
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
                            photoArr.set(refIndex, 0);
                            if(currFilter != 0){
                                child.setVisibility(View.GONE);
                            }
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
                            photoArr.set(refIndex, 1);
                            if(currFilter != 1 && currFilter != 0){
                                child.setVisibility(View.GONE);
                            }
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
                            photoArr.set(refIndex, 2);
                            if(currFilter != 2 && currFilter != 0){
                                child.setVisibility(View.GONE);
                            }
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
                            photoArr.set(refIndex, 3);
                            if(currFilter != 3 && currFilter != 0){
                                child.setVisibility(View.GONE);
                            }
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
                            photoArr.set(refIndex, 4);
                            if(currFilter != 4 && currFilter != 0){
                                child.setVisibility(View.GONE);
                            }
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
                            photoArr.set(refIndex, 5);
                            if(currFilter != 5 && currFilter != 0){
                                child.setVisibility(View.GONE);
                            }
                        }
                    });
                    index++;
                }


                //LOAD PHOTOS HERE
            }
        });

        Button any = (Button) findViewById(R.id.any);
        any.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(loading != 0)return;
                // your handler code here
                currFilter = 0;
                Button s1 = (Button) findViewById(R.id.star1);
                Button s2 = (Button) findViewById(R.id.star2);
                Button s3 = (Button) findViewById(R.id.star3);
                Button s4 = (Button) findViewById(R.id.star4);
                Button s5 = (Button) findViewById(R.id.star5);
                s1.setText("☆");
                s2.setText("☆");
                s3.setText("☆");
                s4.setText("☆");
                s5.setText("☆");
                GridLayout photos = (GridLayout) findViewById(R.id.photos);
                for(int i = 0; i < photoArr.size();i++){
                    View child = photos.getChildAt(i);
                    child.setVisibility(View.VISIBLE);
                }
            }
        });

        Button star1 = (Button) findViewById(R.id.star1);
        star1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(loading != 0)return;
                // your handler code here ★ ☆
                currFilter = 1;
                Button s1 = (Button) findViewById(R.id.star1);
                Button s2 = (Button) findViewById(R.id.star2);
                Button s3 = (Button) findViewById(R.id.star3);
                Button s4 = (Button) findViewById(R.id.star4);
                Button s5 = (Button) findViewById(R.id.star5);
                s1.setText("★");
                s2.setText("☆");
                s3.setText("☆");
                s4.setText("☆");
                s5.setText("☆");
                GridLayout photos = (GridLayout) findViewById(R.id.photos);
                for(int i = 0; i < photoArr.size();i++){
                    View child = photos.getChildAt(i);
                    if(photoArr.get(i) == 1) {
                        child.setVisibility(View.VISIBLE);
                    }else{
                        child.setVisibility(View.GONE);
                    }
                }
            }
        });

        Button star2 = (Button) findViewById(R.id.star2);
        star2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(loading != 0)return;
                currFilter = 2;
                Button s1 = (Button) findViewById(R.id.star1);
                Button s2 = (Button) findViewById(R.id.star2);
                Button s3 = (Button) findViewById(R.id.star3);
                Button s4 = (Button) findViewById(R.id.star4);
                Button s5 = (Button) findViewById(R.id.star5);
                s1.setText("★");
                s2.setText("★");
                s3.setText("☆");
                s4.setText("☆");
                s5.setText("☆");
                GridLayout photos = (GridLayout) findViewById(R.id.photos);
                for(int i = 0; i < photoArr.size();i++){
                    View child = photos.getChildAt(i);
                    if(photoArr.get(i) == 2) {
                        child.setVisibility(View.VISIBLE);
                    }else{
                        child.setVisibility(View.GONE);
                    }
                }
            }
        });

        Button star3 = (Button) findViewById(R.id.star3);
        star3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(loading != 0)return;
                currFilter = 3;
                Button s1 = (Button) findViewById(R.id.star1);
                Button s2 = (Button) findViewById(R.id.star2);
                Button s3 = (Button) findViewById(R.id.star3);
                Button s4 = (Button) findViewById(R.id.star4);
                Button s5 = (Button) findViewById(R.id.star5);
                s1.setText("★");
                s2.setText("★");
                s3.setText("★");
                s4.setText("☆");
                s5.setText("☆");
                GridLayout photos = (GridLayout) findViewById(R.id.photos);
                for(int i = 0; i < photoArr.size();i++){
                    View child = photos.getChildAt(i);
                    if(photoArr.get(i) == 3) {
                        child.setVisibility(View.VISIBLE);
                    }else{
                        child.setVisibility(View.GONE);
                    }
                }
            }
        });

        Button star4 = (Button) findViewById(R.id.star4);
        star4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(loading != 0)return;
                currFilter = 4;
                Button s1 = (Button) findViewById(R.id.star1);
                Button s2 = (Button) findViewById(R.id.star2);
                Button s3 = (Button) findViewById(R.id.star3);
                Button s4 = (Button) findViewById(R.id.star4);
                Button s5 = (Button) findViewById(R.id.star5);
                s1.setText("★");
                s2.setText("★");
                s3.setText("★");
                s4.setText("★");
                s5.setText("☆");
                GridLayout photos = (GridLayout) findViewById(R.id.photos);
                for(int i = 0; i < photoArr.size();i++){
                    View child = photos.getChildAt(i);
                    if(photoArr.get(i) == 4) {
                        child.setVisibility(View.VISIBLE);
                    }else{
                        child.setVisibility(View.GONE);
                    }
                }
            }
        });

        Button star5 = (Button) findViewById(R.id.star5);
        star5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(loading != 0)return;
                currFilter = 5;
                Button s1 = (Button) findViewById(R.id.star1);
                Button s2 = (Button) findViewById(R.id.star2);
                Button s3 = (Button) findViewById(R.id.star3);
                Button s4 = (Button) findViewById(R.id.star4);
                Button s5 = (Button) findViewById(R.id.star5);
                s1.setText("★");
                s2.setText("★");
                s3.setText("★");
                s4.setText("★");
                s5.setText("★");
                GridLayout photos = (GridLayout) findViewById(R.id.photos);
                for(int i = 0; i < photoArr.size();i++){
                    View child = photos.getChildAt(i);
                    if(photoArr.get(i) == 5) {
                        child.setVisibility(View.VISIBLE);
                    }else{
                        child.setVisibility(View.GONE);
                    }
                }
            }
        });
    }
}
