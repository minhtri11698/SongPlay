package com.example.songplay.Respository;

import androidx.lifecycle.MutableLiveData;

import com.example.songplay.Model.Playlist;
import com.example.songplay.Service.APIService;
import com.example.songplay.Service.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistRespository {
    private DataService dataService = APIService.getService();
    private Call<List<Playlist>> callback;
    private MutableLiveData<List<Playlist>> playlistLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Playlist>> playlistLiveDataList = new MutableLiveData<>();
    private List<Playlist> playlistList, allplaylistList;
    private static final PlaylistRespository ourInstance = new PlaylistRespository();

    public static PlaylistRespository getInstance(){
        return ourInstance;
    }

    public MutableLiveData<List<Playlist>> getPlaylistTop(){
        callback = dataService.GetPlaylistToday();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                playlistList = response.body();
                playlistLiveData.setValue(playlistList);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
        return playlistLiveData;
    }

    public MutableLiveData<List<Playlist>> getAllPlaylist(){
        callback = dataService.GetPlaylistList();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                allplaylistList = response.body();
                playlistLiveDataList.setValue(allplaylistList);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
        return playlistLiveDataList;
    }
}
