package vn.edu.fpt.fastandroidnetworking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Model> modelList;
    private String get = "https://jsonplaceholder.typicode.com/albums?fbclid=IwAR1LuyeZsuLGd8rYUu8TIPRju-VsaclWlMcLL_Y-QnAexflSMLuHTMzXIJQ";

    private RecyclerView rvList;

    private RecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvList=findViewById(R.id.rvList);


        modelList = new ArrayList<>();



    }

    public void getFastAndroidNetworking(View view) {
        AndroidNetworking.get(get).build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Model model = new Model();
                        model.setTitle(jsonObject.getString("title"));
                        model.setId(jsonObject.getInt("id"));
                        model.setUserId(jsonObject.getInt("userId"));
                        modelList.add(model);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                rvList.setLayoutManager(linearLayoutManager);
                recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,modelList);
                rvList.setAdapter(recyclerViewAdapter);
            }
            @Override
            public void onError(ANError anError) {

            }
        });


    }



}