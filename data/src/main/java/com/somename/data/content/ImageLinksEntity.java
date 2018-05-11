package com.somename.data.content;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class ImageLinksEntity extends RealmObject {

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("smallThumbnail")
    private String smallThumbnail;

    public String getThumbnail ()
    {
        return thumbnail;
    }

    public void setThumbnail (String thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    public String getSmallThumbnail ()
    {
        return smallThumbnail;
    }

    public void setSmallThumbnail (String smallThumbnail)
    {
        this.smallThumbnail = smallThumbnail;
    }
}
