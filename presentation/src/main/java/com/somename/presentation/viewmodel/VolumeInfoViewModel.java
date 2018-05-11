package com.somename.presentation.viewmodel;

public class VolumeInfoViewModel {

    private String averageRating="";

    private String publisher="";

    private String authors="";

    private String title="";

    private String description="";

    private ImageLinksViewModel imageLinksViewModel;

    private String categories="";

    private String publishedDate="";

    public String getAverageRating ()
    {
        return averageRating;
    }

    public void setAverageRating (String averageRating)
    {
        this.averageRating = averageRating;
    }

    public String getPublisher ()
    {
        return publisher;
    }

    public void setPublisher (String publisher)
    {
        this.publisher = publisher;
    }

    public String getAuthors ()
    {
        return authors;
    }

    public void setAuthors (String authors)
    {
        this.authors = authors;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public ImageLinksViewModel getImageLinksViewModel()
    {
        return imageLinksViewModel;
    }

    public void setImageLinksViewModel(ImageLinksViewModel imageLinksViewModel)
    {
        this.imageLinksViewModel = imageLinksViewModel;
    }

    public String getCategories ()
    {
        return categories;
    }

    public void setCategories (String categories)
    {
        this.categories = categories;
    }

    public String getPublishedDate ()
    {
        return publishedDate;
    }

    public void setPublishedDate (String publishedDate)
    {
        this.publishedDate = publishedDate;
    }
}
