package com.ehsanrc.appsfactorytask.models;

public class AlbumList {

    private int albumId;
    private String albumName, artistName;
    private int albumImage, favorite;

    public AlbumList(int albumId, String albumName, String artistName, int albumImage, int favorite) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.artistName = artistName;
        this.albumImage = albumImage;
        this.favorite = favorite;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getAlbumImage() {
        return albumImage;
    }

    public int getFavorite() {
        return favorite;
    }
}
