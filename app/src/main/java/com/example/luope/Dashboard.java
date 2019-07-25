package com.example.luope;

import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    ArrayList<String> list;

    //String[] data = {"BIG B","GANDHI"};
        private RecyclerView recyclercantina;
        private LinearLayoutManager linearLayoutManager;
        private CustomAdapter adapter;
        private List<MyData> data_list;

    String serverUri = "https://cordeirovending.luope.com/api/";

    String test1,test2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dashboard);
        String data = getIntent().getStringExtra("dados");
        super.onCreate(savedInstanceState);

        Log.d("dari",data);
        String token = (data.substring(data.lastIndexOf(":") + 1));

        token = token.replace(""+"\"" ,"");
        token = token.replace("}","");

      //  Toast.makeText(this, "Responsa:"+token ,Toast.LENGTH_LONG).show();




        list = new ArrayList<String>();
        list.add("kiosk/user/getInfo");
        list.add("kiosk/user/logout");
        list.add("kiosk/canteen/getAll/");
        list.add("kiosk/ement/getByCanteen");
        list.add("kiosk/movement/getAll");
        list.add("kiosk/ement/reserve");
        list.add("kiosk/ement/unreserve");
        list.add("kiosk/ement/reserved");
        list.add("kiosk/user/updateEmail");
        list.add("kiosk/user/updateInfo");

        final String info,logout,canteens,ement,allOperations,reserve,unreserve,reservedMeals,setMail,updateInfo;

        info = list.get(0);
        logout = list.get(1);
        canteens = list.get(2);
        ement = list.get(3);
        allOperations = list.get(4);
        reserve = list.get(5);
        unreserve= list.get(6);
        reservedMeals = list.get(7);
        setMail = list.get(8);
        updateInfo = list.get(9);


        recyclercantina = (RecyclerView) findViewById(R.id.recyclerCantina);
        data_list = new ArrayList<>();
        load_data_from_server(canteens, token);

        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclercantina.setLayoutManager(linearLayoutManager);

        adapter = new CustomAdapter(this,data_list);
        recyclercantina.setAdapter(adapter);


    }

    private void load_data_from_server(String canteens, String token){

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = ""+serverUri+canteens+token;
       // Toast.makeText(Dashboard.this, "Result:"+url, Toast.LENGTH_LONG).show();
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        //    textView.setText("Response: " + response.toString());
                        // Toast.makeText(Dashboard.this, "Result: "+response.toString(), Toast.LENGTH_LONG).show();
                        try {
                            int count = 0;
                            String test1,test2;
                            while (count < response.length()) {
                                JSONObject jsatojso = response.getJSONObject(count);
                                MyData canteendata = new MyData(jsatojso.getString("canteen_id"),jsatojso.getString("description"));

                                test1 = "Ola";
                                test2 = "Shite";
                                MyData tes1 = new MyData(test1,test2);
                                data_list.add(canteendata);

                               // Toast.makeText(Dashboard.this,""+canteen_description,Toast.LENGTH_LONG).show();
                              //  Toast.makeText(Dashboard.this,""+jsatojso,Toast.LENGTH_LONG).show();
                                count++;
                            }
                        }catch (Exception e){
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Dashboard.this, "ERRO"+error, Toast.LENGTH_LONG).show();

                    }
                });
        MyRequestQueue.add(jsonArrayRequest);

    }

    public void onClick(final View view) {
        int itemPosition = recyclercantina.getChildLayoutPosition(view);
        int item = recyclercantina.getId();
        Toast.makeText(this, ""+item, Toast.LENGTH_LONG).show();
    }







}
