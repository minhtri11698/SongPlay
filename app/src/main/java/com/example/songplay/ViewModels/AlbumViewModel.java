package com.example.songplay.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.songplay.Model.Album;
import com.example.songplay.Respository.AlbumRespository;

import java.util.List;

public class AlbumViewModel extends ViewModel {
    private MutableLiveData<List<Album>> hotAlbumLiveData;
    private MutableLiveData<List<Album>> allAlbumLiveData;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    AlbumRespository albumRespository = AlbumRespository.getInstance();

    public AlbumViewModel(){
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
    public MutableLiveData<List<Album>> getAlbumLiveData(String state){
        if (state.equals("all")){
            allAlbumLiveData = albumRespository.getAllAlbumLiveData();
            return allAlbumLiveData;
        } else {
            hotAlbumLiveData = albumRespository.getHotAlbumLiveData();
            return hotAlbumLiveData;
        }
    }
}
