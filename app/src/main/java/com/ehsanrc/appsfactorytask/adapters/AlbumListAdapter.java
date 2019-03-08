package com.ehsanrc.appsfactorytask.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ehsanrc.appsfactorytask.R;
import com.ehsanrc.appsfactorytask.controllers.DetailsActivity;
import com.ehsanrc.appsfactorytask.models.AlbumList;
import com.ehsanrc.appsfactorytask.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.MyViewHolder> {

    private Context context;
    private List<AlbumList> albums = new ArrayList<>();

    public AlbumListAdapter(Context context, List<AlbumList> albums) {
        this.context = context;
        this.albums = albums;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_album,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.albumName.setText(albums.get(position).getAlbumName());
        holder.artistName.setText(albums.get(position).getArtistName());
        holder.albumImage.setImageResource(albums.get(position).getAlbumImage());

        if(albums.get(position).getFavorite() == 1){
            holder.favButton.setImageResource(android.R.drawable.btn_star_big_on);
        }else holder.favButton.setImageResource(android.R.drawable.btn_star_big_off);

        holder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = DatabaseHelper.createDB(context);

                if(albums.get(position).getFavorite() == 0){
                    Log.i("FavInitial", albums.get(position).getAlbumId()+" "+albums.get(position).getFavorite());
                    DatabaseHelper.updateFavorite(albums.get(position).getAlbumId(), db, 1);
                    holder.favButton.setImageResource(android.R.drawable.btn_star_big_on);
                    Log.i("FavUpdated", albums.get(position).getAlbumId()+" "+albums.get(position).getFavorite());
                    Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show();
                }else{
                    Log.i("FavInitial", albums.get(position).getAlbumId()+" "+albums.get(position).getFavorite());
                    DatabaseHelper.updateFavorite(albums.get(position).getAlbumId(), db, 0);
                    holder.favButton.setImageResource(android.R.drawable.btn_star_big_off);
                    Log.i("FavUpdated", albums.get(position).getAlbumId()+" "+albums.get(position).getFavorite());
                    Toast.makeText(context, "Removed from Favorites", Toast.LENGTH_SHORT).show();
                }
                DatabaseHelper.closeDatabase(db);
            }
        });

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("name", albums.get(position).getAlbumName());
                intent.putExtra("artist", albums.get(position).getArtistName());
                intent.putExtra("albumId", albums.get(position).getAlbumId());
                intent.putExtra("image", albums.get(position).getAlbumImage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView albumImage;
        public TextView albumName, artistName;
        public RelativeLayout parentLayout;
        public ImageButton favButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            albumImage = itemView.findViewById(R.id.iv_album_image);
            albumName = itemView.findViewById(R.id.tv_album_name);
            artistName = itemView.findViewById(R.id.tv_artist_name);
            favButton = itemView.findViewById(R.id.ib_fav);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
