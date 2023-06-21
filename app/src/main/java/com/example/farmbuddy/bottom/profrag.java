package com.example.farmbuddy.bottom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.farmbuddy.MainAdapter;
import com.example.farmbuddy.MainAdapter2;
import com.example.farmbuddy.MainModel;
import com.example.farmbuddy.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class profrag extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profrag, container, false);
        RecyclerView recyclerView;
        MainAdapter2 mainAdapter2;
        recyclerView = view.findViewById(R.id.rv1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Vendor").orderByChild("Product"), MainModel.class)
                        .build();

        mainAdapter2 = new MainAdapter2(options);
        mainAdapter2.startListening();
        recyclerView.setAdapter(mainAdapter2);
        mainAdapter2.notifyDataSetChanged();

        return  view;

    }

}