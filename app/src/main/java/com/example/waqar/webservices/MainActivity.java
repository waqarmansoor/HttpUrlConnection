package com.example.waqar.webservices;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import model.Flower;
import Parser.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import Adapter.FlowerAdapter;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb;
    Button btn;
    ListView listView;
    FlowerAdapter floweradapter;
    String uri="http://services.hanselandpetal.com/secure/flowers.json";
    String imgBase="http://services.hanselandpetal.com/photos/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb= (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        btn= (Button) findViewById(R.id.button);
        listView= (ListView) findViewById(R.id.lt_view);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask myTask=new MyTask();
                myTask.execute(uri);
            }
        });


    }

    private class MyTask extends AsyncTask<String,String,List<Flower>>{

        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Flower> doInBackground(String... params) {
            String dataR=HttpManager.getData(params[0],"feeduser","feedpassword");
            List<Flower> flowers=new ArrayList<>();
            flowers=JsonParser.getData(dataR);

            String url;
            for (Flower flower:flowers) {
                try {
                    url=imgBase+flower.getPhoto();
                    InputStream is= (InputStream) new URL(url).getContent();
                    Bitmap bitmap= BitmapFactory.decodeStream(is);
                    flower.setImgBitmap(bitmap);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        return flowers;
        }

        @Override
        protected void onPostExecute(List<Flower> flowers) {
            super.onPostExecute(flowers);
            pb.setVisibility(View.INVISIBLE);
            floweradapter=new FlowerAdapter(MainActivity.this,flowers);
            listView.setAdapter(floweradapter);

        }

    }

}
