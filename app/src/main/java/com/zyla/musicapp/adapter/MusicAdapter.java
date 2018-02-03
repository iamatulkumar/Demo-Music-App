package com.zyla.musicapp.adapter;

/**
 * Created by pratap.kesaboyina on 24-12-2014.
 */

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyla.musicapp.R;
import com.zyla.musicapp.databinding.ListItemBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ItemRowHolder> {

    private String TAG ="MusicAdapter";

    private HashMap<String, ArrayList<String>> nameList;
    private Context mContext;
    private ArrayList<String> key = new ArrayList<>();
    private ArrayList<ArrayList<String>> value = new ArrayList<>();

    /**
     * @param context Mainactivity context
     * @param listHashMap hashmap which show in mainactivity
     */
    public MusicAdapter(Context context, HashMap<String, ArrayList<String>> listHashMap) {
        this.nameList=listHashMap;
        this.mContext = context;

        for(Map.Entry<String, ArrayList<String>> e : nameList.entrySet()) {
            key.add(e.getKey());
            value.add(e.getValue());
            Log.d(TAG,key+"  "+value);
        }
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
        return  new ItemRowHolder(view);
    }


    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {

        itemRowHolder.bindheader(key.get(i));

        DataAdapter itemListDataAdapter = new DataAdapter(mContext, value.get(i));

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        itemRowHolder.binding.recyclerViewList.setLayoutManager(mLayoutManager);
        itemRowHolder.binding.recyclerViewList.setAdapter(itemListDataAdapter);

    }

    @Override
    public int getItemCount() {
        return (null != nameList ? nameList.size() : 0);
    }

    protected class ItemRowHolder extends RecyclerView.ViewHolder {
        ListItemBinding binding;

        private ItemRowHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
    }

        private void bindheader(String str){
            binding.setKey(str);
        }
    }

}