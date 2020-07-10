package com.example.songplay.Respository;

import androidx.lifecycle.MutableLiveData;

import com.example.songplay.Model.Song;
import com.example.songplay.Service.APIService;
import com.example.songplay.Service.DataService;
import com.example.songplay.View.Activity.ListSongActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongRespository {
    private DataService dataService = APIService.getService();
    private List<Song> songList;
    private MutableLiveData<List<Song>> songListLiveData = new MutableLiveData<>();
    private static final SongRespository ourInstance = new SongRespository();

    public static SongRespository getInstance(){
        return ourInstance;
    }

    public MutableLiveData<List<Song>> getSongListLiveData(String request, String id){
        Call<List<Song>> callback;
        if (request.equals("type")){
            callback = dataService.GetSongListOnType(id);
            callback.enqueue(new Callback<List<Song>>() {
                @Override
                public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                    songList = response.body();
                    songListLiveData.setValue(songList);
                }

                @Override
                public void onFailure(Call<List<Song>> call, Throwable t) {

                }
            });
            return songListLiveData;
        } else {
            callback = dataService.GetSongOnPlaylist(id);
            callback.enqueue(new Callback<List<Song>>() {
                @Override
                public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                    songList = response.body();
                    songListLiveData.setValue(songList);
                }

                @Override
                public void onFailure(Call<List<Song>> call, Throwable t) {

                }
            });
            return songListLiveData;
        }
    }
}
