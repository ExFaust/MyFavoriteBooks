package com.somename.presentation;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.somename.presentation.di.DaggerMainComponent;
import com.somename.presentation.di.MainComponent;
import com.somename.presentation.di.MainModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

public class BooksApplication extends Application {

    private static Context sContext;

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;

        Realm.init(sContext);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .rxFactory(new RealmObservableFactory())
                .build();
        Realm.setDefaultConfiguration(configuration);

        mainComponent = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();

    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    @NonNull
    public static Context getContext() {
        return sContext;
    }

}
