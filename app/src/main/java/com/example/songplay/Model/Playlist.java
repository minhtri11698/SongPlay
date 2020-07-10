package com.example.songplay.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playlist {

    @SerializedName("idPlaylist")
    @Expose
    private String idPlaylist;
    @SerializedName("playlistName")
    @Expose
    private String playlistName;
    @SerializedName("playlistBg")
    @Expose
    private String playlistBg;
    @SerializedName("playlistIcon")
    @Expose
    private String playlistIcon;

    public String getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistBg() {
        return playlistBg;
    }

    public void setPlaylistBg(String playlistBg) {
        this.playlistBg = playlistBg;
    }

    public String getPlaylistIcon() {
        return playlistIcon;
    }

    public void setPlaylistIcon(String playlistIcon) {
        this.playlistIcon = playlistIcon;
    }
}

