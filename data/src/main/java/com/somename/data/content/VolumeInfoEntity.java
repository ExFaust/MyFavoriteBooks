package com.somename.data.content;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class VolumeInfoEntity extends RealmObject {

    public VolumeInfoEntity() {
    }

    @SerializedName("averageRating")
    private String averageRating;

    @SerializedName("publisher")
    private String publisher;

    @SerializedName("authors")
    private RealmList<String> authors;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("imageLinks")
    private ImageLinksEntity imageLinksEntity;

    @SerializedName("categories")
    private RealmList<String> categories;

    @SerializedName("publishedDate")
    private String publishedDate;

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public RealmList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(RealmList<String> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageLinksEntity getImageLinksEntity() {
        return imageLinksEntity;
    }

    public void setImageLinksEntity(ImageLinksEntity imageLinksEntity) {
        this.imageLinksEntity = imageLinksEntity;
    }

    public RealmList<String> getCategories() {
        return categories;
    }

    public void setCategories(RealmList<String> categories) {
        this.categories = categories;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String toString(RealmList<String> strings) {
        if (strings != null) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < strings.size(); i++)
                if (i == 0)
                    builder.append(strings.get(i));
                else
                    builder.append(", ").append(strings.get(i));
            return builder.toString();
        } else
            return "";
    }

    public RealmList<String> toArray(String string) {
        RealmList<String> realmList = new RealmList<>();
        if (string != null)
            realmList.addAll(Arrays.asList(string.split(", ")));
        return realmList;
    }
}
