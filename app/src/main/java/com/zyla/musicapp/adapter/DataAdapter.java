package com.zyla.musicapp.adapter;

/**
 * Created by Atul kumar  on 31-01-2018.
 */

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.zyla.musicapp.R;
import com.zyla.musicapp.databinding.ListItemRowBinding;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.SingleItemRowHolder> {


    private Context mContext;
    private ArrayList<String> value = new ArrayList<>();

    /**
     * @param context
     * @param itemsList
     */
    public DataAdapter(Context context, ArrayList<String> itemsList) {
        this.value = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_row, null);

        return new SingleItemRowHolder(view);
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {
         holder.bindList(value.get(i));
    }

    @Override
    public int getItemCount() {
        return (null != value ? value.size() : 0);
    }

    protected class SingleItemRowHolder extends RecyclerView.ViewHolder {
        protected ListItemRowBinding binding;

        private SingleItemRowHolder(View view) {
            super(view);
            binding= DataBindingUtil.bind(view);
        }
        private void bindList(String string){
            binding.setValue(string);
        }

    }

}