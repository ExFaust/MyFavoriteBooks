package com.somename.data.content;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RootSearchEntity
{
    public RootSearchEntity() {

    }

    @SerializedName("items")
    private List<BookEntity> books;

    public List<BookEntity> getBooks()
    {
        return books;
    }

    public void setBooks(List<BookEntity> books)
    {
        this.books = books;
    }

}
