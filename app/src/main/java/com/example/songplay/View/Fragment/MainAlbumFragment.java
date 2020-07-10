package com.example.songplay.View.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.songplay.Model.Album;
import com.example.songplay.R;
import com.example.songplay.View.Activity.MoreActivity;
import com.example.songplay.View.Adapter.AlbumAdapter;
import com.example.songplay.ViewModels.AlbumViewModel;

import java.util.List;

public class MainAlbumFragment extends Fragment {

    AlbumAdapter albumAdapter;
    AlbumViewModel albumViewModel;
    RecyclerView albumRecyclerView;
    ProgressDialog progress;
    ImageButton morebtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_album, container, false);
        albumRecyclerView = v.findViewById(R.id.hot_album_list);
        morebtn = v.findViewById(R.id.view_more_album_btn);

        progress = new ProgressDialog(this.getContext());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);

        albumViewModel = new ViewModelProvider(this).get(AlbumViewModel.class);
        albumViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean) {
                    progress.show();
                }
            }
        });

        albumViewModel.getAlbumLiveData("").observe(getViewLifecycleOwner(), new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albums) {
                albumViewModel.getIsLoading().postValue(false);
                progress.dismiss();
                recyclerInit(albums);
            }
        });

        morebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MoreActivity.class);
                intent.putExtra("all_album", "");
                startActivity(intent);
            }
        });
        return v;
    }

    private void recyclerInit(List<Album> albums){
        albumAdapter = new AlbumAdapter(this.getContext(), albums);
        albumRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        albumRecyclerView.setAdapter(albumAdapter);
    }
}