package Parser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import model.Flower;
/**
 * Created by waqar on 5/5/2017.
 */

public class JsonParser{
    private static int flowerId;
    private static String name;
    private static String photo;
    private static String instructions;
    private static String price;
    private static String category;

    public static List<Flower> getData(String content){

        JSONArray jsonArray= null;
        List<Flower> list=null;
        Flower flower;
        try {
            jsonArray = new JSONArray(content);
            list=new ArrayList<Flower>();
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject= jsonArray.getJSONObject(i);

                flowerId=jsonObject.getInt("productId");
                name=jsonObject.getString("name");
                photo=jsonObject.getString("photo");
                instructions=jsonObject.getString("instructions");
                price=jsonObject.getString("price");
                category=jsonObject.getString("category");

                flower=new Flower();

                flower.setFlowerId(flowerId);
                flower.setName(name);
                flower.setPhoto(photo);
                flower.setCategory(category);
                flower.setInstructions(instructions);
                flower.setPrice(price);
                list.add(flower);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Token",e.toString());
            return null;
        }


    }
}
