package com.example.luope;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {



    private Context context;
    private List<MyData> myData;
    private static ViewHolder viewHolder = null;

    public CustomAdapter(Context context, List<MyData> myData) {
        this.context = context;
        this.myData = myData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemcantina =LayoutInflater.from(context).inflate(R.layout.recyclercantina, parent,false);

        return new ViewHolder(itemcantina);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.description.setText(myData.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return myData.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = (TextView)itemView.findViewById(R.id.testrow);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context,""+itemView.getId(),Toast.LENGTH_LONG).show();

            if (viewHolder!=null){
                itemView.setBackgroundResource(android.R.color.white);
                Toast.makeText(context,"is not",Toast.LENGTH_LONG).show();

            }
            SelectionEffect(0);
        }

        private void SelectionEffect(int mode) {

            if(mode == 0){
            itemView.setBackgroundResource(R.color.endcastanho);
            }
            if(mode==1){
                itemView.setBackgroundResource(android.R.color.white);
            }
          //
        }



    }
}
