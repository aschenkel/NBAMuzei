package com.schenkel.axel.muzeiplantae.DataAccess.Dagger;

import android.content.Context;

import com.schenkel.axel.muzeiplantae.DataAccess.Implementations.FirebaseService;
import com.schenkel.axel.muzeiplantae.DataAccess.Implementations.SharedPreferencesService;
import com.schenkel.axel.muzeiplantae.DataAccess.Interfaces.LocalDBService;
import com.schenkel.axel.muzeiplantae.DataAccess.Interfaces.RemoteDBService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by axel on 20/05/17.
 */
@Module
public class DBsModule {
    private final Context context;

    public DBsModule(Context context) {
        this.context = context;
    }
    @Provides
    RemoteDBService providesRemoteDB() {
        return new FirebaseService();
    }

    @Provides
    LocalDBService providesLocalDB() {
        return new SharedPreferencesService(context);
    }
}
