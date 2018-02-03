package com.zyla.musicapp.adapter;

/**
 * Created by pratap.kesaboyina on 24-12-2014.
 */

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zyla.musicapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ItemRowHolder> {

    String TAG ="MusicAdapter";

    HashMap<String, ArrayList<String>> nameList;
    private Context mContext;
    ArrayList<String> key = new ArrayList<>();
    ArrayList<ArrayList<String>> value = new ArrayList<>();

    /**
     *
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
        ItemRowHolder viewHolder = new ItemRowHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {

        itemRowHolder.itemTitle.setText(key.get(i));

        DataAdapter itemListDataAdapter = new DataAdapter(mContext, value.get(i));

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        itemRowHolder.recyclerViewList.setLayoutManager(mLayoutManager);
        itemRowHolder.recyclerViewList.setAdapter(itemListDataAdapter);

    }

    @Override
    public int getItemCount() {
        return (null != nameList ? nameList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView itemTitle;
        protected RecyclerView recyclerViewList;

        public ItemRowHolder(View view) {
            super(view);
            this.itemTitle = (TextView) view.findViewById(R.id.itemTitle);
            this.recyclerViewList = (RecyclerView) view.findViewById(R.id.recycler_view_list);

        }
    }

}