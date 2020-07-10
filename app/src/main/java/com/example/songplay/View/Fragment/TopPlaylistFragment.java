package com.example.songplay.View.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.songplay.Model.Playlist;
import com.example.songplay.R;
import com.example.songplay.View.Activity.MoreActivity;
import com.example.songplay.View.Adapter.PlaylistAdapter;
import com.example.songplay.ViewModels.PlaylistViewModel;

import java.util.List;

public class TopPlaylistFragment extends Fragment {

    PlaylistAdapter playlistAdapter;
    PlaylistViewModel playlistViewModel;
    RecyclerView playlistRecycler;
    ImageButton viewMorePlaylist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_playlist_fragment, container, false);
        viewMorePlaylist = view.findViewById(R.id.view_more_top_playlist_btn);
        viewMorePlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MoreActivity.class);
                intent.putExtra("all_playlist", "");
                startActivity(intent);
            }
        });
        playlistRecycler = view.findViewById(R.id.top_playlist_list);
        playlistViewModel = new ViewModelProvider(this).get(PlaylistViewModel.class);
        playlistViewModel.getPlaylistLiveData("").observe(getViewLifecycleOwner(), new Observer<List<Playlist>>() {
            @Override
            public void onChanged(List<Playlist> playlists) {
                playlistAdapter = new PlaylistAdapter(TopPlaylistFragment.this.getContext(), playlists);
                playlistRecycler.setLayoutManager(new LinearLayoutManager(TopPlaylistFragment.this.getContext()));
                playlistRecycler.setAdapter(playlistAdapter);
            }
        });
        return view;
    }
}