package com.somename.presentation.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.somename.data.database.LocalRealmImpl;
import com.somename.data.network.LocalApi;
import com.somename.data.network.LoggingInterceptor;
import com.somename.data.repository.MovieNetworkRepository;
import com.somename.data.repository.MovieRealmRepository;
import com.somename.domain.DBRepository;
import com.somename.domain.NetworkRepository;
import com.somename.presentation.BuildConfig;
import com.somename.presentation.BooksApplication;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MainModule {

    private final BooksApplication mBooksApplication;

    public MainModule(BooksApplication booksApplication) {
        this.mBooksApplication = booksApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mBooksApplication;
    }

    @Provides
    @Singleton
    NetworkRepository provideRepository(MovieNetworkRepository movieNetworkRepository) {
        return movieNetworkRepository;
    }

    @Provides
    @Singleton
    DBRepository provideRealmRepository(MovieRealmRepository movieRealmRepository) {
        return movieRealmRepository;
    }

    @Provides
    @Singleton
    LocalApi provideAPI(@NonNull Retrofit retrofit) {
        return retrofit.create(LocalApi.class);
    }

    @Provides
    @Singleton
    LocalRealmImpl provideLocalRealm(Realm realm) {
        return new LocalRealmImpl(realm);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@NonNull OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor.create())
                .build();
    }

    @Provides
    @Singleton
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides @Named("executor_thread")
    Scheduler provideExecutorThread() {
        return Schedulers.io();
    }

    @Provides @Named("ui_thread") Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }
}
