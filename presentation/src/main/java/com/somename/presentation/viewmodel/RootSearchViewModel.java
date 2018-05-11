package com.somename.presentation.viewmodel;

import java.util.List;

public class RootSearchViewModel
{
    public RootSearchViewModel() {

    }

    private List<BookViewModel> bookViewModels;

    public List<BookViewModel> getBookViewModels()
    {
        return bookViewModels;
    }

    public void setBookViewModels(List<BookViewModel> bookViewModels)
    {
        this.bookViewModels = bookViewModels;
    }

}
