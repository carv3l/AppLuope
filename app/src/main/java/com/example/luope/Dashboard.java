package com.example.luope;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dashboard extends AppCompatActivity {
    ArrayList<String> list;

String[] data = {"BIG B","GANDHI"};


    String serverUri = "https://cordeirovending.luope.com/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView( R.layout.activity_dashboard);
        String data = getIntent().getStringExtra("dados");


        ListView listViewcanteen = (ListView)findViewById(R.id.listviewcanteen);

        CustomAdapter customAdapter = new CustomAdapter();

        listViewcanteen.setAdapter(customAdapter);


        Log.d("dari",data);


        String token = (data.substring(data.lastIndexOf(":") + 1));

        token = token.replace(""+"\"" ,"");
        token = token.replace("}","");

        Toast.makeText(this, "Responsa:"+token ,Toast.LENGTH_LONG).show();

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


       // Toast.makeText(this, serverUri+info,Toast.LENGTH_LONG).show();



        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = ""+serverUri+canteens+token;
        Toast.makeText(Dashboard.this, "Result:"+url, Toast.LENGTH_LONG).show();
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                    //    textView.setText("Response: " + response.toString());
                       // Toast.makeText(Dashboard.this, "Result: "+response.toString(), Toast.LENGTH_LONG).show();
                        try {
                            int count = 0;
                         //   TextView canteentxt = (TextView)findViewById(R.id.mTExtvies);
                            String canteen_id,canteen_description;
                            while (count < response.length()) {
                                JSONObject jsatojso = response.getJSONObject(count);
                                canteen_id = jsatojso.getString("canteen_id");
                                canteen_description = jsatojso.getString("description");



                             //   canteentxt.append(canteen_description + "- "+ canteen_id + "\n\n");

                                Toast.makeText(Dashboard.this,""+jsatojso,Toast.LENGTH_LONG).show();
                                canteen canteen =  new canteen(canteen_id,canteen_description);
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

       // ListView list = (ListView)findViewById(R.id.listviewcanteen);


        //list.setAdapter(canteenAdpt);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }


        class CustomAdapter extends BaseAdapter{

        public int getCount(){

            return data.length;
        }
        public Object getItem(int i){
            return null;

        }
        public long getItemId(int i){

            return 0;
        }
        public View getView(int i, View view, ViewGroup viewGroup){
            view = getLayoutInflater().inflate(R.layout.row_listview,null);
            TextView row  =(TextView)findViewById(R.id.testrow);

            row.setText(data[i]);

            return view;
        }

    }







}
