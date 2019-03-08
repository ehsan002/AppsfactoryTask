package com.ehsanrc.appsfactorytask.controllers;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ehsanrc.appsfactorytask.R;
import com.ehsanrc.appsfactorytask.adapters.TrackListAdapter;
import com.ehsanrc.appsfactorytask.models.TrackList;
import com.ehsanrc.appsfactorytask.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private TextView tvName, tvArtist;
    private RecyclerView rv;
    private TrackListAdapter adapter;
    private ImageView ivAlbumImage;

    private String name, artist;
    private int albumId, albumImageRes;

    private List<TrackList> tracks = new ArrayList<>();
    private TrackList track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        SQLiteDatabase db = DatabaseHelper.createDB(this);

        albumId = bundle.getInt("albumId");
        name = bundle.getString("name");
        artist = bundle.getString("artist");
        albumImageRes = bundle.getInt("image");

        try {
            tracks = DatabaseHelper.readDataFromTracks(db, albumId);
        }catch (Exception e){
            Toast.makeText(this, "Nothing to display", Toast.LENGTH_SHORT).show();
        }


        DatabaseHelper.closeDatabase(db);

        tvName = findViewById(R.id.tv_details_album);
        tvArtist = findViewById(R.id.tv_details_artist);
        ivAlbumImage = findViewById(R.id.iv_image_detail);

        tvName.setText(name);
        tvArtist.setText(artist);
        ivAlbumImage.setImageResource(albumImageRes);

        rv = findViewById(R.id.rv_tracks);
        adapter = new TrackListAdapter(this, tracks);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
