package com.example.aperarnaud.test.Model;

import com.example.aperarnaud.test.Model.Playlist;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Playlist {
    public int id;
    public String title;
    public int duration;
    @SerializedName("public")
    public boolean ispublic;
    @SerializedName("is_loved_track")
    public boolean isLovedTrack;
    public boolean collaborative;
    public int rating;
    public int nb_tracks;
    public int fans;
    public String link;
    public String picture;
    public String picture_small;
    public String picture_medium;
    public String picture_big;
    public String picture_xl;
    public String checksum;
    public String tracklist;
    public String creation_date;
    public String time_add;
    public String time_mod;
    public Creator creator;
    public String type;
}
