package com.example.songplay.Respository;

import androidx.lifecycle.MutableLiveData;

import com.example.songplay.Model.Album;
import com.example.songplay.Service.APIService;
import com.example.songplay.Service.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumRespository {
    private DataService dataService = APIService.getService();
    private Call<List<Album>> callback;
    private MutableLiveData<List<Album>> hotAlbumLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Album>> allAlbumLiveData = new MutableLiveData<>();
    private List<Album> hotAlbumList, allAlbumList;
    private static final AlbumRespository ourInstance = new AlbumRespository();

    public static AlbumRespository getInstance(){
        return ourInstance;
    }

    public MutableLiveData<List<Album>> getHotAlbumLiveData(){
        callback = dataService.GetAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                hotAlbumList = response.body();
                hotAlbumLiveData.setValue(hotAlbumList);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
        return hotAlbumLiveData;
    }

    public MutableLiveData<List<Album>> getAllAlbumLiveData(){
        callback = dataService.GetAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                allAlbumList = response.body();
                allAlbumLiveData.setValue(allAlbumList);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
        return allAlbumLiveData;
    }
}
