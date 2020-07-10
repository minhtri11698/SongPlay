package com.example.songplay.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.songplay.Model.MType;
import com.example.songplay.Respository.MusicTypeRespository;

import java.util.List;

public class MusicTypeViewModel extends ViewModel {
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<List<MType>> musicTypeLiveData;
    private MutableLiveData<List<MType>> allMusicLiveData;
    private MusicTypeRespository musicTypeRespository = MusicTypeRespository.getInstance();

    public MusicTypeViewModel(){
        super();
        isLoading.setValue(true);
        musicTypeLiveData = musicTypeRespository.getTopMusicType();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<List<MType>> getMusicTypeLiveData(String state){
        if (state.equals("all")){
            allMusicLiveData = musicTypeRespository.getAllMusicType();
            return allMusicLiveData;
        } else {
            musicTypeLiveData = musicTypeRespository.getTopMusicType();
            return musicTypeLiveData;
        }
    }
}
