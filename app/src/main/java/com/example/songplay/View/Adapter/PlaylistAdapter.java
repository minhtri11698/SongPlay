package com.example.songplay.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.songplay.Model.Playlist;
import com.example.songplay.R;
import com.example.songplay.View.Activity.ListSongActivity;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    Context context;
    List<Playlist> topPlaylist;

    public PlaylistAdapter(Context context, List<Playlist> mtopPlaylist){
        this.context = context;
        this.topPlaylist = mtopPlaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.top_playlist_item_layout, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Playlist playlist = topPlaylist.get(position);
        holder.playlistname.setText(playlist.getPlaylistName());
        Glide.with(context).load(playlist.getPlaylistIcon()).into(holder.playlistImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("playlist", playlist.getIdPlaylist());
                intent.putExtra("img", playlist.getPlaylistIcon());
                intent.putExtra("name", playlist.getPlaylistName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topPlaylist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView playlistImage;
        TextView playlistname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playlistname = itemView.findViewById(R.id.playlist_name);
            playlistImage = itemView.findViewById(R.id.playlist_image);
        }
    }
}
