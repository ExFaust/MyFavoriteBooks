package com.somename.presentation.viewmodel;

public class ImageLinksViewModel {

    private String thumbnail="";

    private String smallThumbnail="";

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
