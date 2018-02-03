package com.zyla.musicapp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import com.zyla.musicapp.adapter.MusicAdapter;
import com.zyla.musicapp.databinding.ActivityMainBinding;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    String TAG ="Main Activity";

    private HashMap<String, ArrayList<String>> mNameArtist;
    private HashMap<String, ArrayList<String>> mNameAlbum;
    private HashMap<String, ArrayList<String>> mFilterData;
    private String[] content = null;
    private int mMusicType;
    private int mDataLimit;
    String line = "";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        mNameArtist = new HashMap<>();
        mNameAlbum = new HashMap<>();
        mFilterData = new HashMap<>();

        try {
            InputStream inputStream = getAssets().open("music_data.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            while((line = br.readLine()) != null){
                content = line.split(",");
                Log.d(TAG,content[0]+" ---- " + content[1]+"    "+content[2]);

                if(null == mNameArtist.get(content[1]) || mNameArtist.get(content[1]).isEmpty()){
                    ArrayList<String> value = new ArrayList<>();
                    value.add(content[0]);
                    mNameArtist.put(content[1], value);
                }else{
                    mNameArtist.get(content[1]).add(content[0]);
                }
                if(null == mNameAlbum.get(content[2]) || mNameAlbum.get(content[2]).isEmpty()){
                    ArrayList<String> value = new ArrayList<>();
                    value.add(content[0]);
                    mNameAlbum.put(content[2], value);
                }else{
                    mNameAlbum.get(content[2]).add(content[0]);
                }

            }
            br.close();
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            binding.recyclerView.setLayoutManager(mLayoutManager);

        } catch (IOException e) {
            e.printStackTrace();
        }

        binding.toolbar.dataLimit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,parent+" ---- " + view+"    "+position+ "   "  +id);
                mDataLimit=position;
                if (mMusicType==0){
                    mFilterData=findSortList(mNameArtist,position+1);
                    dataAdapter(mFilterData);
                } else if (mMusicType==1){
                    mFilterData= findSortList(mNameAlbum,position+1);
                    dataAdapter(mFilterData);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        binding.toolbar.musicType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,parent+" ---- " + view+"    "+position+ "   "  +id);
                mMusicType = position;
                binding.toolbar.dataLimit.setSelection(mDataLimit);
                if (position==0){
                    mFilterData=findSortList(mNameArtist,mDataLimit+1);
                    dataAdapter(mFilterData);
                } else if (position==1){
                    mFilterData=findSortList(mNameAlbum,mDataLimit+1);
                    dataAdapter(mFilterData);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    /**
     *
     * @param itemList item list which is currently show in list
     * @param number numbet
     *
     */
    private HashMap<String, ArrayList<String>> findSortList(HashMap<String, ArrayList<String>> itemList, int number){

        HashMap<String, ArrayList<String>> tempdata= new HashMap<>();

        for(Map.Entry<String, ArrayList<String>> e : itemList.entrySet()) {
            String key = e.getKey();
            ArrayList<String> newList = e.getValue();
            if (number<=newList.size()){
                List<String> tempArrayList = newList.subList(0,number);
                ArrayList<String> list = new ArrayList<>(tempArrayList);
                tempdata.put(key,list);
            } else {
                tempdata.put(key,newList);
            }
            Log.d(TAG,key+" ---- " + newList);
        }
         return tempdata;

    }

    /**
     *
     * @param itemList
     */
    private void dataAdapter(HashMap<String, ArrayList<String>> itemList){
        MusicAdapter adapter = new MusicAdapter(MainActivity.this, itemList);
        binding.recyclerView.setAdapter(adapter);
    }


}
