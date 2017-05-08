package Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.waqar.webservices.MainActivity;
import com.example.waqar.webservices.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import model.Flower;

/**
 * Created by waqar on 5/6/2017.
 */

public class FlowerAdapter extends BaseAdapter {

    Context context;
    List<Flower> flowers;
    LruCache<Integer,Bitmap> imagecache;
    public FlowerAdapter(Context context, List<Flower> flowers){
        this.context=context;
        this.flowers=flowers;

        int maxMemory= (int) (Runtime.getRuntime().maxMemory()/1024);
        int cacheSize=maxMemory/8;
        imagecache=new LruCache<>(cacheSize);
    }


    @Override
    public int getCount() {
        return flowers.size();
    }

    @Override
    public Object getItem(int position) {
        return flowers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.flower,parent,false);

        TextView title= (TextView) view.findViewById(R.id.title);
        TextView price= (TextView) view.findViewById(R.id.price);
        title.setText(flowers.get(position).getName());
        price.setText(flowers.get(position).getPrice());

        Bitmap bitmap=imagecache.get(flowers.get(position).getFlowerId());

        if(bitmap !=null){
            Log.d("waqar","CacheHit");
            ImageView iv= (ImageView) view.findViewById(R.id.fl_img);
            iv.setImageBitmap(flowers.get(position).getImgBitmap());
        }else{
            Log.d("waqar","CacheMiss");
            FlowerAndView flowerAndView=new FlowerAndView();
            flowerAndView.flower= flowers.get(position);
            flowerAndView.view=view;
            ImageLoader imageLoader=new ImageLoader();
            imageLoader.execute(flowerAndView);
        }



        return view;
    }

    public class FlowerAndView{
        Flower flower;
        View view;
        Bitmap bitmap;
    }

    public class ImageLoader extends AsyncTask<FlowerAndView,String,FlowerAndView>{

        @Override
        protected FlowerAndView doInBackground(FlowerAndView... params) {
            FlowerAndView container=params[0];
            Flower flower=container.flower;
            String url;
            try {
                    url= MainActivity.imgBase+flower.getPhoto();
                    InputStream is= (InputStream) new URL(url).getContent();
                    Bitmap bitmap= BitmapFactory.decodeStream(is);
                    flower.setImgBitmap(bitmap);
                    is.close();
                    container.bitmap=bitmap;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return container;
        }


        @Override
        protected void onPostExecute(FlowerAndView result) {
            super.onPostExecute(result);
            ImageView iv= (ImageView) result.view.findViewById(R.id.fl_img);
            iv.setImageBitmap(result.bitmap);
            //result.flower.setImgBitmap(result.bitmap);
            imagecache.put(result.flower.getFlowerId(),result.bitmap);

        }
    }

















}
