package com.ehsanrc.appsfactorytask.controllers;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ehsanrc.appsfactorytask.R;
import com.ehsanrc.appsfactorytask.adapters.AlbumListAdapter;
import com.ehsanrc.appsfactorytask.models.AlbumList;
import com.ehsanrc.appsfactorytask.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText etSearch;
    private Button btnSearch;
    private RecyclerView rvSearch;
    private AlbumListAdapter adapter;
    private List<AlbumList> albums = new ArrayList<>();
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etSearch = findViewById(R.id.et_search);
        btnSearch = findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                albums.clear();

                String searchedText = etSearch.getText().toString();

                if (searchedText.equals("")){
                    Toast.makeText(SearchActivity.this, "No result found", Toast.LENGTH_SHORT).show();
                }else {
                    db = DatabaseHelper.createDB(SearchActivity.this);
                    try {
                        albums = DatabaseHelper.readSearchedData(db, searchedText);
                    }catch (Exception e){
                        Toast.makeText(SearchActivity.this, "No result found", Toast.LENGTH_SHORT).show();
                    }
                }


                DatabaseHelper.closeDatabase(db);

                adapter = new AlbumListAdapter(SearchActivity.this, albums);
                rvSearch = findViewById(R.id.rv_search);
                rvSearch.setAdapter(adapter);
                rvSearch.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
            }
        });

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
