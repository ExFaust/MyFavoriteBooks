package com.somename.data.network;


import com.somename.data.BuildConfig;
import com.somename.data.content.BookEntity;
import com.somename.data.content.RootSearchEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocalApi {

    @GET("volumes?"+ BuildConfig.API_KEY)
    Observable<RootSearchEntity> booksEntityList(@Query("q") String bookName);//+"inauthor:keyes"
}