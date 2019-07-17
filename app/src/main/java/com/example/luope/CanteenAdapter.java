package com.example.luope;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CanteenAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public CanteenAdapter(Context context, int resource){
        super(context,resource);
    }

    @Override
    public void add( Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();

    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;

        TextView cantid = (TextView)row.findViewById(R.id.testrow);


        if (row == null){

            Log.d("row","NULL ROW");
           // LayoutInflater  layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           // row = layoutInflater.inflate(R.layout.row_listview,parent,false);

        }


        canteen cantina = (canteen)this.getItem(position);

        cantid.setText(""+cantina);
        return row;
    }
}
