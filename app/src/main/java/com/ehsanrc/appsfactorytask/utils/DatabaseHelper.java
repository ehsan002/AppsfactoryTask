package com.ehsanrc.appsfactorytask.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ehsanrc.appsfactorytask.R;
import com.ehsanrc.appsfactorytask.models.AlbumList;
import com.ehsanrc.appsfactorytask.models.TrackList;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DatabaseHelper {

    private static String PRIMARY_KEY = "INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL";
    private static String COUNT_QUERY = "SELECT count(*) FROM ";

    private static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    private static String INSERT_INTO_DB = "INSERT INTO ";
    private static String READ_FROM_DB = "SELECT * FROM ";
    private static String TABLE_NAME_ALBUMS = "albums";
    private static String TABLE_NAME_TRACKS = "tracks";
    private static String CREATE_TABLE_TRACKS = CREATE_TABLE+ TABLE_NAME_TRACKS+" (trackId "+PRIMARY_KEY+" , trackNo INTEGER, trackName VARCHAR, albumId INTEGER, FOREIGN KEY(albumId) REFERENCES albums(id))";
    private static String UPDATE_DB = "UPDATE "+TABLE_NAME_ALBUMS+" SET ";

    public static SQLiteDatabase createDB(Context context){
        SQLiteDatabase db = context.openOrCreateDatabase("music", MODE_PRIVATE, null);
        return db;
    }

    public static void closeDatabase(SQLiteDatabase db){
        db.close();
    }

    public static List<AlbumList> readDataFromAlbums(SQLiteDatabase db){

        List<AlbumList> list= new ArrayList<>();
        AlbumList listItem;

        Cursor c = db.rawQuery(READ_FROM_DB+TABLE_NAME_ALBUMS, null);

        int idIndex = c.getColumnIndex("id");
        int nameIndex = c.getColumnIndex("name");
        int artistIndex = c.getColumnIndex("artist");
        int imageIndex = c.getColumnIndex("image");
        int favIndex = c.getColumnIndex("favorite");

        c.moveToFirst();

        if(c!=null){
            listItem = new AlbumList(c.getInt(idIndex), c.getString(nameIndex), c.getString(artistIndex), c.getInt(imageIndex), c.getInt(favIndex));
            list.add(listItem);

            while (c.moveToNext()){
                listItem = new AlbumList(c.getInt(idIndex), c.getString(nameIndex), c.getString(artistIndex), c.getInt(imageIndex), c.getInt(favIndex));
                list.add(listItem);
            }
        }

        return list;
    }

    public static void createTable(SQLiteDatabase myDB){
        myDB.execSQL(CREATE_TABLE+TABLE_NAME_ALBUMS+" (id "+PRIMARY_KEY+", name VARCHAR, artist VARCHAR, image int, favorite int)");
        myDB.execSQL(CREATE_TABLE_TRACKS);
    }

    public static void insertDataIntoAlbums(SQLiteDatabase myDB){
        if (isEmpty(myDB, TABLE_NAME_ALBUMS)){
            myDB.execSQL(INSERT_INTO_DB+TABLE_NAME_ALBUMS+" (name, artist, image, favorite) VALUES ('Meteora','Linkin Park',"+R.drawable.meteora+", 0)");
            myDB.execSQL(INSERT_INTO_DB+TABLE_NAME_ALBUMS+" (name, artist, image, favorite) VALUES ('Hok Kolorob','Arnob',"+R.drawable.hok_kolorob+", 0)");
            myDB.execSQL(INSERT_INTO_DB+TABLE_NAME_ALBUMS+" (name, artist, image, favorite) VALUES ('Icchhe','Tahsan',"+R.drawable.icchhe+", 0)");
            myDB.execSQL(INSERT_INTO_DB+TABLE_NAME_ALBUMS+" (name, artist, image, favorite) VALUES ('Toxicity','System Of A Down',"+R.drawable.toxicity+", 0)");
            myDB.execSQL(INSERT_INTO_DB+TABLE_NAME_ALBUMS+" (name, artist, image, favorite) VALUES ('Aushomapto 2','Aurthohin',"+R.drawable.aushomapto2+", 0)");
            myDB.execSQL(INSERT_INTO_DB+TABLE_NAME_ALBUMS+" (name, artist, image, favorite) VALUES ('Hybrid Theory','Linkin Park',"+R.drawable.hybrid_theory+", 0)");
        }
    }

    public static void insertDataIntoTracks(SQLiteDatabase db){
        if (isEmpty(db, TABLE_NAME_TRACKS)){
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 01,'Forword', 01)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 02,'Don''t Stay', 01)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 03,'Somewhere I Belong', 01)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 04,'Lying From You', 01)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 05,'Hit The Floor', 01)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 06,'Easier To Run', 01)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 07,'Faint', 01)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 08,'Figure 09', 01)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 09,'From The Inside', 01)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 10,'Breaking The Habit', 01)");

            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 01,'Bakshe Bakshe', 02)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 02,'Tomar Jonno', 02)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 03,'Hok Kolorob', 02)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 04,'Tui Ki Janishna', 02)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 05,'Bhalobasha Tarpor', 02)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 06,'Tor Jonno', 02)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 07,'Shomoy Kate', 02)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 08,'Chalak Tumi', 02)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 09,'Muhurto', 02)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 10,'Shobdo', 02)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 11,'Prokrito Jol', 02)");

            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 01,'Alo', 03)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 02,'Brishtite', 03)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 03,'Durotto', 03)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 04,'Pagla Ghuri', 03)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 06,'Durey', 03)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 07,'Hoyni Bola', 03)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 08,'Icchhe', 03)");

            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 01,'Prison Songs', 04)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 02,'Niddles', 04)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 03,'Deer Dance', 04)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 04,'Jet Pilot', 04)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 05,'X', 04)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 06,'Chop Suey!', 04)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 07,'Bounce', 04)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 08,'Forest', 04)");

            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 01,'Uru Uru Mon', 05)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 02,'Alo R Adhar', 05)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 03,'Anmone 2', 05)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 04,'Jak Na', 05)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 05,'Golper Shuru', 05)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 06,'Majhe Majhe', 05)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 07,'Abar', 05)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 08,'Akasher Tara', 05)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 09,'Surjo', 05)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 10,'Cancer', 05)");
            db.execSQL(INSERT_INTO_DB+TABLE_NAME_TRACKS+" (trackNo,trackName, albumId) VALUES ( 11,'Nikrishto', 05)");

        }
    }

    public static List<AlbumList> readFavoriteData(SQLiteDatabase db) {

        Cursor c = db.rawQuery(READ_FROM_DB + TABLE_NAME_ALBUMS + " WHERE favorite = 1", null);

        List<AlbumList> list = new ArrayList<>();
        AlbumList album;

        int idIndex = c.getColumnIndex("id");
        int nameIndex = c.getColumnIndex("name");
        int artistIndex = c.getColumnIndex("artist");
        int imageIndex = c.getColumnIndex("image");
        int favIndex = c.getColumnIndex("favorite");

        c.moveToFirst();

        if(c!=null){
            album = new AlbumList(c.getInt(idIndex), c.getString(nameIndex), c.getString(artistIndex), c.getInt(imageIndex), c.getInt(favIndex));
            list.add(album);

            while (c.moveToNext()){
                album = new AlbumList(c.getInt(idIndex), c.getString(nameIndex), c.getString(artistIndex), c.getInt(imageIndex), c.getInt(favIndex));
                list.add(album);
            }
        }

        return list;

    }

    public static List<TrackList> readDataFromTracks(SQLiteDatabase db, int albumId) {

        Cursor c = db.rawQuery(READ_FROM_DB + TABLE_NAME_TRACKS + " WHERE albumId = "+albumId, null);

        List<TrackList> list = new ArrayList<>();
        TrackList track;

        int trackIdIndex = c.getColumnIndex("trackId");
        int trackNoIndex = c.getColumnIndex("trackNo");
        int trackNameIndex = c.getColumnIndex("trackName");

        c.moveToFirst();

        if(c!=null){
            track = new TrackList(c.getInt(trackIdIndex), c.getInt(trackNoIndex), c.getString(trackNameIndex));
            list.add(track);

            while (c.moveToNext()){
                track = new TrackList(c.getInt(trackIdIndex), c.getInt(trackNoIndex), c.getString(trackNameIndex));
                list.add(track);
            }
        }

        return list;

    }

    public static List<AlbumList> readSearchedData(SQLiteDatabase db, String searchText) {

        Cursor c = db.rawQuery(READ_FROM_DB + TABLE_NAME_ALBUMS + " WHERE (name LIKE '%"+searchText+"%' COLLATE NOCASE) OR (artist LIKE '%"+searchText+"%' COLLATE NOCASE) ", null);

        List<AlbumList> list = new ArrayList<>();
        AlbumList album;

        int idIndex = c.getColumnIndex("id");
        int nameIndex = c.getColumnIndex("name");
        int artistIndex = c.getColumnIndex("artist");
        int imageIndex = c.getColumnIndex("image");
        int favIndex = c.getColumnIndex("favorite");

        c.moveToFirst();

        if(c!=null){
            album = new AlbumList(c.getInt(idIndex), c.getString(nameIndex), c.getString(artistIndex), c.getInt(imageIndex), c.getInt(favIndex));
            list.add(album);

            while (c.moveToNext()){
                album = new AlbumList(c.getInt(idIndex), c.getString(nameIndex), c.getString(artistIndex), c.getInt(imageIndex), c.getInt(favIndex));
                list.add(album);
            }
        }

        return list;

    }

    public static void updateFavorite(int id, SQLiteDatabase db, int fav){
        db.execSQL(UPDATE_DB+"favorite = "+fav+" WHERE id = "+id);
        Log.i("Favorite", "Updated");
    }

    public static boolean isEmpty(SQLiteDatabase db, String tableName){

        Cursor c = db.rawQuery(COUNT_QUERY + tableName, null);

        c.moveToFirst();
        int count = c.getInt(0);
        if (count>0) return false;
        else return true;
    }

}
