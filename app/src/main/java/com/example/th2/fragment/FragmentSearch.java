package com.example.th2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2.R;
import com.example.th2.adapter.RecycleViewAdapter;
import com.example.th2.dal.SQLiteHelper;
import com.example.th2.model.Item;

import java.util.List;




public class FragmentSearch extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;

    private Button btSearch;
    private SearchView searchView;

    private Spinner spNxb;
    private Spinner spTacGia;
    private RecycleViewAdapter adapter;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter =new RecycleViewAdapter();
        db=new SQLiteHelper(getContext());
        List<Item> list=db.getAll();
        adapter.setList(list);

        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Item> list=db.searchBySach(s);

                adapter.setList(list);

                return true;
            }
        });

        btSearch.setOnClickListener(this);
        spNxb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position , long l) {
                String nxb=spNxb.getItemAtPosition(position).toString();
                List<Item> list;
                if(!nxb.equalsIgnoreCase("all")){
                    list=db.SearchByNXB(nxb);


                }else {
                    list=db.getAll();
                }
                adapter.setList(list);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spTacGia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position , long l) {
                String tacgia=spTacGia.getItemAtPosition(position).toString();
                List<Item> list;
                if(!tacgia.equalsIgnoreCase("all")){
                    list=db.SearchByTacGia(tacgia);


                }else {
                    list=db.getAll();
                }
                adapter.setList(list);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    private void initView(View view) {
        recyclerView=view.findViewById(R.id.recycleView);

        btSearch=view.findViewById(R.id.btSearch);
        searchView=view.findViewById(R.id.search);

        spTacGia=view.findViewById(R.id.spTacGia);
        spNxb=view.findViewById(R.id.spNxb);
        String[] arr=getResources().getStringArray(R.array.tacgia);
        String [] arr1=new String[arr.length+1];
        arr1[0]="all";
        for(int i=0;i<arr.length;i++){
            arr1[i+1]=arr[i];

        }
        spTacGia.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.item_spinner,arr1));
        String[] arr2=getResources().getStringArray(R.array.nxb);
        String [] arr3=new String[arr2.length+1];

        arr3[0]="all";
        for(int i=0;i<arr2.length;i++){
            arr3[i+1]=arr2[i];

        }
        spNxb.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.item_spinner,arr3));





    }

    @Override
    public void onClick(View view) {
        if(view==btSearch){

            return;
        }




    }
}
