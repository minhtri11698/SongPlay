package com.example.songplay.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.songplay.Model.Song;
import com.example.songplay.Respository.SongRespository;

import java.util.List;

public class SongListViewModel extends ViewModel {
    private MutableLiveData<List<Song>> songListLiveData;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private SongRespository songRespository = SongRespository.getInstance();

    public SongListViewModel(){
        super();
        isLoading.setValue(true);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<List<Song>> getSongList(String type, String id){
        songListLiveData = songRespository.getSongListLiveData(type, id);
        return songListLiveData;
    }
}
