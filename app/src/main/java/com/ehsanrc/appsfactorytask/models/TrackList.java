package com.ehsanrc.appsfactorytask.models;

public class TrackList {

    private int trackId, trackNo;
    private String trackName;

    public TrackList(int trackId, int trackNo, String trackName) {
        this.trackId = trackId;
        this.trackNo = trackNo;
        this.trackName = trackName;
    }

    public int getTrackId() {
        return trackId;
    }

    public int getTrackNo() {
        return trackNo;
    }

    public String getTrackName() {
        return trackName;
    }
}
