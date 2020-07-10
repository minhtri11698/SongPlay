package com.example.songplay.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.songplay.Model.Album;
import com.example.songplay.Model.MType;
import com.example.songplay.Model.Playlist;
import com.example.songplay.R;
import com.example.songplay.View.Adapter.AlbumAdapter;
import com.example.songplay.View.Adapter.MusicTypeAdapter;
import com.example.songplay.View.Adapter.PlaylistAdapter;
import com.example.songplay.ViewModels.AlbumViewModel;
import com.example.songplay.ViewModels.MusicTypeViewModel;
import com.example.songplay.ViewModels.PlaylistViewModel;

import java.util.List;

public class MoreActivity extends AppCompatActivity {

    private TextView title;
    private MusicTypeViewModel musicTypeViewModel;
    private MusicTypeAdapter musicTypeAdapter;
    private PlaylistViewModel playlistViewModel;
    private AlbumViewModel albumViewModel;
    private PlaylistAdapter playlistAdapter;
    private AlbumAdapter albumAdapter;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ProgressDialog progressBar;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        recyclerView = findViewById(R.id.more_recycler_view);
        toolbar = findViewById(R.id.more_search_toolbar);
        title = findViewById(R.id.more_view_title);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = new ProgressDialog(this);
        progressBar.setTitle("Loading...");
        progressBar.setMessage("Please wait!");
        progressBar.setCancelable(true);

        Intent intent = getIntent();
        if (intent != null){
            if(intent.hasExtra("all_playlist")){
                title.setText(R.string.all_top_100);
                playlistViewModel = new ViewModelProvider(this).get(PlaylistViewModel.class);
                playlistViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(aBoolean) {
                            progressBar.show();
                        }
                    }
                });
                playlistViewModel.getPlaylistLiveData("all").observe(this, new Observer<List<Playlist>>() {
                    @Override
                    public void onChanged(List<Playlist> playlists) {
                        progressBar.dismiss();
                        playlistViewModel.getIsLoading().postValue(false);
                        setPlaylistRecyclerView(playlists);
                    }
                });
            } else if (intent.hasExtra("all_album")){
                title.setText(R.string.all_album);
                albumViewModel = new ViewModelProvider(this).get(AlbumViewModel.class);
                albumViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (aBoolean) {
                            progressBar.show();
                        }
                    }
                });
                albumViewModel.getAlbumLiveData("all").observe(this, new Observer<List<Album>>() {
                    @Override
                    public void onChanged(List<Album> albums) {
                        progressBar.dismiss();
                        albumViewModel.getIsLoading().postValue(false);
                        setAlbumRecyclerView(albums);
                    }
                });
            } else if (intent.hasExtra("all_type")){
                title.setText(R.string.all_type);
                musicTypeViewModel = new ViewModelProvider(this).get(MusicTypeViewModel.class);
                musicTypeViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (aBoolean) {
                            progressBar.show();
                        }
                    }
                });
                musicTypeViewModel.getMusicTypeLiveData("all").observe(this, new Observer<List<MType>>() {
                    @Override
                    public void onChanged(List<MType> mTypes) {
                        progressBar.dismiss();
                        musicTypeViewModel.getIsLoading().postValue(false);
                        setMusicTypeRecycler(mTypes);
                    }
                });
            }
        }


        toolbar.findViewById(R.id.toolbar_search_edit).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                hideKeyboard(view);
            }
        });

    }

    private void setPlaylistRecyclerView(List<Playlist> playlists){
        playlistAdapter = new PlaylistAdapter(this, playlists);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(playlistAdapter);
    }

    private void setAlbumRecyclerView(List<Album> albums){
        albumAdapter = new AlbumAdapter(this, albums);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(albumAdapter);
    }

    private void setMusicTypeRecycler(List<MType> mTypes){
        musicTypeAdapter = new MusicTypeAdapter(this, mTypes);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(musicTypeAdapter);
    }

    public void hideKeyboard(View view) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}