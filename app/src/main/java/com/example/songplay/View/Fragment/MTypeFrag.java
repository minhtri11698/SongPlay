package com.example.songplay.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.songplay.Model.MType;
import com.example.songplay.R;
import com.example.songplay.View.Activity.MoreActivity;
import com.example.songplay.View.Adapter.MusicTypeAdapter;
import com.example.songplay.ViewModels.MusicTypeViewModel;

import java.util.List;

public class MTypeFrag extends Fragment {

    MusicTypeViewModel musicTypeViewModel;
    RecyclerView recyclerView;
    MusicTypeAdapter musicTypeAdapter;
    ImageButton moreTypeBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_m_type, container, false);
        recyclerView = view.findViewById(R.id.music_type_recycler_view_);
        moreTypeBtn = view.findViewById(R.id.view_more_type_btn);
        moreTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MoreActivity.class);
                intent.putExtra("all_type", "");
                startActivity(intent);
            }
        });
        musicTypeViewModel = new ViewModelProvider(this).get(MusicTypeViewModel.class);
        musicTypeViewModel.getMusicTypeLiveData("").observe(getViewLifecycleOwner(), new Observer<List<MType>>() {
            @Override
            public void onChanged(List<MType> mTypes) {
                musicTypeAdapter = new MusicTypeAdapter(MTypeFrag.this.getContext(), mTypes);
                recyclerView.setLayoutManager(new GridLayoutManager(MTypeFrag.this.getContext(), 2));
                recyclerView.setAdapter(musicTypeAdapter);
            }
        });
        return view;
    }
}