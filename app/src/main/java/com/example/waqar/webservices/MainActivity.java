package com.example.waqar.webservices;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb;
    Button btn;
    ListView listView;
    ArrayAdapter<Flower> arrayAdapter;
    String uri="http://services.hanselandpetal.com/feeds/flowers.json";

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
            String dataR=HttpManager.getData(params[0]);
            return JsonParser.getData(dataR);
        }

        @Override
        protected void onPostExecute(List<Flower> flowers) {
            super.onPostExecute(flowers);
            pb.setVisibility(View.INVISIBLE);
            //Log.d("Token",flowers.get(1).toString());
            arrayAdapter=new ArrayAdapter<Flower>(getBaseContext(),android.R.layout.simple_list_item_1,flowers);
            listView.setAdapter(arrayAdapter);

        }

    }

}
