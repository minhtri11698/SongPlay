package com.example.songplay.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.songplay.Model.Song;
import com.example.songplay.R;
import com.example.songplay.View.Adapter.SongAdapter;
import com.example.songplay.ViewModels.SongListViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListSongActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView icon;
    ProgressDialog progressDialog;
    SongListViewModel songListViewModel;
    SongAdapter songAdapter;
    RecyclerView songRecyclerView;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        collapsingToolbarLayout = findViewById(R.id.collapsing_tool_bar);
        floatingActionButton = findViewById(R.id.play_all_btn);
        toolbar = findViewById(R.id.song_list_toolbar);
        songRecyclerView = findViewById(R.id.song_list_recycler);
        icon = findViewById(R.id.song_list_icon_img);

        songListViewModel = new ViewModelProvider(this).get(SongListViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait!");
        progressDialog.setCancelable(true);


        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        songListViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    progressDialog.show();
                }
            }
        });

        Intent intent = getIntent();
        if (intent != null){
            String name = intent.getStringExtra("name");
            collapsingToolbarLayout.setTitle(name);
            Log.d("checkName", name);
            String img = intent.getStringExtra("img");
            Glide.with(this).load(img).into(icon);
            if (intent.hasExtra("type")){
                String id = intent.getStringExtra("type");
                songListViewModel.getSongList("type", id).observe(this, new Observer<List<Song>>() {
                    @Override
                    public void onChanged(List<Song> songs) {
                        progressDialog.dismiss();
                        songListViewModel.getIsLoading().postValue(false);
                        setSongRecyclerView(songs);
                    }
                });
            } else if (intent.hasExtra("playlist")){
                String id = intent.getStringExtra("playlist");
                Log.d("checklogid", id);
                songListViewModel.getSongList("playlist", id).observe(this, new Observer<List<Song>>() {
                    @Override
                    public void onChanged(List<Song> songs) {
                        progressDialog.dismiss();
                        songListViewModel.getIsLoading().postValue(false);
                        setSongRecyclerView(songs);
                    }
                });
            }
        }
    }

    private void setSongRecyclerView(List<Song> songList){
        songAdapter = new SongAdapter(this, songList);
        songRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        songRecyclerView.setAdapter(songAdapter);
    }
}