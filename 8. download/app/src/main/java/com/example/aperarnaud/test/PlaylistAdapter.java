package com.example.aperarnaud.test;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aperarnaud.test.Model.Playlist;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.MyViewHolder> {

    private List<Playlist> playlists;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public PlaylistAdapter(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Playlist playlist = playlists.get(position);
        holder.title.setText(playlist.title);
        holder.genre.setText(playlist.title);
        holder.year.setText(playlist.type);
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }
}