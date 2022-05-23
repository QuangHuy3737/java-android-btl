package com.example.th2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2.LoginActivity;
import com.example.th2.R;
import com.example.th2.RegisterActivity;
import com.example.th2.UpdateDeleteActivity;
import com.example.th2.UpdateNopTien;
import com.example.th2.adapter.RecycleViewAdapter;
import com.example.th2.dal.SQLiteHelper;
import com.example.th2.model.Item;

import java.util.List;

public class FragmentInfor extends Fragment implements RecycleViewAdapter.ItemListener {
    public FragmentInfor(){}
    private Button btLogout;
    private TextView tvLoi;
    private Button btSearch;
    private SearchView searchView;
    private RecyclerView recyclerView;

    private Spinner spNxb;
    private Spinner spTacGia;
    private RecycleViewAdapter adapter;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_infor,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btLogout= view.findViewById(R.id.btLogout);
        tvLoi = view.findViewById(R.id.tvLoi);
        recyclerView=view.findViewById(R.id.recycleView);
        adapter =new RecycleViewAdapter();
        db=new SQLiteHelper(getContext());
        List<Item> list=db.searchBySach("huy");
        adapter.setList(list);

        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onItemClick(View view, int position) {
        Item item=adapter.getItem(position);
        Intent intent=new Intent(getActivity(), UpdateNopTien.class);
        intent.putExtra("item",item);
        startActivity(intent);

    }

}