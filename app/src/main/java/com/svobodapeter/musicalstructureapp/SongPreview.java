package com.svobodapeter.musicalstructureapp;

/**
 * Created by pitrs on 14.03.2018.
 */

public class SongPreview {

    /**
     * Custom class called by Adapter to get and populate RecyclerView. Here we can find views
     * which are changing content acc. to ArrayList in MainActivity.class
     */

    private int mAlbumImage;
    private String mArtistName;
    private String mSongName;

    public SongPreview(int albumImage, String artistName, String songName){
        mAlbumImage = albumImage;
        mArtistName = artistName;
        mSongName = songName;
    }

    public int getAlbumImage() {
        return mAlbumImage;
    }

    public String getArtistName () {
        return mArtistName;
    }

    public String getSongName () {
        return mSongName;
    }
}
