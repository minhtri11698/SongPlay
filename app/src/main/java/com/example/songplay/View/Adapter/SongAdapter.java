package com.example.songplay.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.songplay.Model.Song;
import com.example.songplay.R;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private Context context;
    private List<Song> songList;

    public SongAdapter(Context context1, List<Song> songList1){
        this.context = context1;
        this.songList = songList1;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.song_list_item_layout, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.songPosition.setText(String.valueOf(position));
        holder.singerName.setText(song.getSinger());
        holder.songName.setText(song.getSongName());
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {
        TextView songPosition, songName, singerName;
        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            songPosition = itemView.findViewById(R.id.song_list_position);
            songName = itemView.findViewById(R.id.song_list_name_song);
            singerName = itemView.findViewById(R.id.singer_name);
        }
    }
}
