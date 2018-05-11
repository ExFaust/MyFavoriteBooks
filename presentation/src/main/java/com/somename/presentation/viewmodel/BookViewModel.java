package com.somename.presentation.viewmodel;

public class BookViewModel {

    private String id;

    private VolumeInfoViewModel volumeInfoViewModel;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public VolumeInfoViewModel getVolumeInfoViewModel()
    {
        return volumeInfoViewModel;
    }

    public void setVolumeInfoViewModel(VolumeInfoViewModel volumeInfoViewModel)
    {
        this.volumeInfoViewModel = volumeInfoViewModel;
    }
}
