package com.somename.data.content;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class BookEntity extends RealmObject {

    @SerializedName("id")
    private String id;

    @SerializedName("volumeInfo")
    private VolumeInfoEntity volumeInfoEntity;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public VolumeInfoEntity getVolumeInfoEntity()
    {
        return volumeInfoEntity;
    }

    public void setVolumeInfoEntity(VolumeInfoEntity volumeInfoEntity)
    {
        this.volumeInfoEntity = volumeInfoEntity;
    }

}
