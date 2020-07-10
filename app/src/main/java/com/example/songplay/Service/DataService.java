package com.example.songplay.Service;

import com.example.songplay.Model.Album;
import com.example.songplay.Model.MType;
import com.example.songplay.Model.Playlist;
import com.example.songplay.Model.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("playlisttoday.php")
    Call<List<Playlist>> GetPlaylistToday();

    @GET("theloai.php")
    Call<List<MType>> GetMusicType();

    @GET("album.php")
    Call<List<Album>> GetAlbum();

    @GET("danhsachplaylist.php")
    Call<List<Playlist>> GetPlaylistList();

    @GET("getAllAlbum.php")
    Call<List<Album>> GetAllAlbum();

    @GET("getAllType.php")
    Call<List<MType>> GetAllType();

    @FormUrlEncoded
    @POST("songlist.php")
    Call<List<Song>> GetSongListOnType(@Field("idtype") String idtype);

    @FormUrlEncoded
    @POST("songlist.php")
    Call<List<Song>> GetSongOnPlaylist(@Field("idplaylist") String idplaylist);
}
