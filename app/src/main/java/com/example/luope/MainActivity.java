package com.example.luope;

import android.content.Intent;
import android.hardware.camera2.CameraCharacteristics;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    String serverApi = "https://cordeirovending.luope.com/api/kiosk/auth";


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }







    public void Login (View v){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

        //Intent i=  new Intent( this, Menu_Vendas.class );
//        startActivity(i);



        EditText editText_email = (EditText) findViewById(R.id.email);//Nome cliente
        final String login_email = editText_email.getText().toString();

        EditText editText_password = (EditText) findViewById(R.id.password);//Nome cliente
        final String login_password = editText_password.getText().toString();

         //     if (login_email.matches("") || login_password.matches("")){
          //      Toast.makeText(this,"Os campos não podem estar vazios", Toast.LENGTH_LONG).show();

        //}




        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, serverApi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                    Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                   intent.putExtra("dados",response);
                   startActivity(intent);


                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                Toast.makeText(MainActivity.this, "Não existe nenhum utilizador com esses dados", Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("email", login_email); //Add the data you'd like to send to the server.
                MyData.put("password", login_password);
                return MyData;
            }
        };
        MyRequestQueue.add(MyStringRequest);
      //
    }


















}
