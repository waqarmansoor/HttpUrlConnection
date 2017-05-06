package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.waqar.webservices.R;

import java.util.List;

import model.Flower;

/**
 * Created by waqar on 5/6/2017.
 */

public class FlowerAdapter extends BaseAdapter {

    Context context;
    List<Flower> flowers;
    public FlowerAdapter(Context context, List<Flower> flowers){
        this.context=context;
        this.flowers=flowers;
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
        ImageView iv= (ImageView) view.findViewById(R.id.fl_img);

        iv.setImageBitmap(flowers.get(position).getImgBitmap());
        title.setText(flowers.get(position).getName());
        price.setText(flowers.get(position).getPrice());

        return view;
    }
}
