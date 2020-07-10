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
import com.example.songplay.Model.MType;
import com.example.songplay.R;
import com.example.songplay.View.Activity.ListSongActivity;

import java.util.List;

public class MusicTypeAdapter extends RecyclerView.Adapter<MusicTypeAdapter.TypeViewHolder> {

    private List<MType> mTypeList;
    private Context context;

    public MusicTypeAdapter(Context context1, List<MType> mTypeList1){
        this.context = context1;
        this.mTypeList = mTypeList1;
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view_item_layout, parent, false);
        return new TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, int position) {
        final MType mType = mTypeList.get(position);
        holder.typeName.setText(mType.getTypeName());
        Glide.with(context).load(mType.getTypePhoto()).into(holder.typePhoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListSongActivity.class);
                intent.putExtra("type", mType.getIdType());
                intent.putExtra("img", mType.getTypePhoto());
                intent.putExtra("name", mType.getTypeName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTypeList.size();
    }

    public static class TypeViewHolder extends RecyclerView.ViewHolder {
        ImageView typePhoto;
        TextView typeName;
        public TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            typePhoto = itemView.findViewById(R.id.music_type_photo);
            typeName = itemView.findViewById(R.id.music_type_name);
        }
    }
}
