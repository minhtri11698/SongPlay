package com.example.songplay.Respository;

import androidx.lifecycle.MutableLiveData;

import com.example.songplay.Model.MType;
import com.example.songplay.Service.APIService;
import com.example.songplay.Service.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicTypeRespository {
    private static final MusicTypeRespository ourInstance = new MusicTypeRespository();
    private MutableLiveData<List<MType>> mTypeLiveData = new MutableLiveData<>();
    private MutableLiveData<List<MType>> allMTypeLiveData = new MutableLiveData<>();
    private List<MType> mTypeList, allMTypeList;
    private Call<List<MType>> callback;
    private DataService dataService = APIService.getService();

    public static MusicTypeRespository getInstance(){
        return ourInstance;
    }

    public MutableLiveData<List<MType>> getAllMusicType(){
        callback = dataService.GetAllType();
        callback.enqueue(new Callback<List<MType>>() {
            @Override
            public void onResponse(Call<List<MType>> call, Response<List<MType>> response) {
                allMTypeList = response.body();
                allMTypeLiveData.setValue(allMTypeList);
            }

            @Override
            public void onFailure(Call<List<MType>> call, Throwable t) {

            }
        });
        return allMTypeLiveData;
    }

    public MutableLiveData<List<MType>> getTopMusicType(){
        callback = dataService.GetMusicType();
        callback.enqueue(new Callback<List<MType>>() {
            @Override
            public void onResponse(Call<List<MType>> call, Response<List<MType>> response) {
                mTypeList = response.body();
                mTypeLiveData.setValue(mTypeList);
            }

            @Override
            public void onFailure(Call<List<MType>> call, Throwable t) {

            }
        });
        return mTypeLiveData;
    }
}
