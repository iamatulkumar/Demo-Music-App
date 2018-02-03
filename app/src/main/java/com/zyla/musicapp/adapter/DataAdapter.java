package com.zyla.musicapp.adapter;

/**
 * Created by Atul kumar  on 31-01-2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zyla.musicapp.R;
import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.SingleItemRowHolder> {


    private Context mContext;
    ArrayList<String> value = new ArrayList<>();

    /**
     *
     * @param context
     * @param itemsList
     */
    public DataAdapter(Context context, ArrayList<String> itemsList) {
        this.value = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_row, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {
        holder.tvTitle.setText(value.get(i));
       // System.out.println("=========dataa"+value.get(i));

    }

    @Override
    public int getItemCount() {
        return (null != value ? value.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle;

        public SingleItemRowHolder(View view) {
            super(view);
            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);

        }

    }

}