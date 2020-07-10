package com.example.songplay.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.songplay.Model.Playlist;
import com.example.songplay.Respository.PlaylistRespository;

import java.util.List;

public class PlaylistViewModel extends ViewModel {
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<List<Playlist>> allPlaylistMutableLiveData;
    private MutableLiveData<List<Playlist>> playlistMutableLiveData;

    public PlaylistViewModel() {
        super();
        isLoading.setValue(true);
        PlaylistRespository playlistRespository = PlaylistRespository.getInstance();
        playlistMutableLiveData = playlistRespository.getPlaylistTop();
        allPlaylistMutableLiveData = playlistRespository.getAllPlaylist();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<List<Playlist>> getPlaylistLiveData(String state) {
        if (state.equals("all")){
            return allPlaylistMutableLiveData;
        } else {
            return playlistMutableLiveData;
        }
    }
}
