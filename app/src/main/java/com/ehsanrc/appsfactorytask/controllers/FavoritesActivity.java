package com.ehsanrc.appsfactorytask.controllers;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.ehsanrc.appsfactorytask.R;
import com.ehsanrc.appsfactorytask.adapters.AlbumListAdapter;
import com.ehsanrc.appsfactorytask.models.AlbumList;
import com.ehsanrc.appsfactorytask.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private List<AlbumList> albums = new ArrayList<>();
    private SQLiteDatabase db;

    private RecyclerView rv;
    private AlbumListAdapter albumListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = DatabaseHelper.createDB(this);
        try{
            albums = DatabaseHelper.readFavoriteData(db);
        }
        catch (Exception e){
            Toast.makeText(this, "Nothing to display", Toast.LENGTH_SHORT).show();
        }


        DatabaseHelper.closeDatabase(db);

        rv = findViewById(R.id.rv_fav);
        albumListAdapter = new AlbumListAdapter(this, albums);
        rv.setAdapter(albumListAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }
}
