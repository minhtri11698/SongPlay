package com.example.songplay.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.songplay.Model.Album;
import com.example.songplay.R;
import com.example.songplay.databinding.TopPlaylistItemLayoutBinding;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private List<Album> albumList;
    private Context context;

    public AlbumAdapter(Context context1, List<Album> albumList1){
        this.context = context1;
        this.albumList = albumList1;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.top_playlist_item_layout, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.albumName.setText(album.getAlbumName());
        holder.singerName.setText(album.getSingerName());
        Glide.with(context).load(album.getAlbumPhoto()).into(holder.albumIcon);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        TextView albumName, singerName;
        ImageView albumIcon;
        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            albumName = itemView.findViewById(R.id.playlist_name);
            singerName = itemView.findViewById(R.id.singer_name);
            albumIcon = itemView.findViewById(R.id.playlist_image);
        }
    }
}
