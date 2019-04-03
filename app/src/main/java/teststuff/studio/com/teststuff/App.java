package teststuff.studio.com.teststuff;

import android.app.Activity;
import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import teststuff.studio.com.teststuff.Activity.MainActivity;
import teststuff.studio.com.teststuff.Activity.SplashActivity;
import teststuff.studio.com.teststuff.Database.AppDatabase;

public class App extends Application {

    public static AppDatabase myAppDatabase;

    @Override
    public void onCreate() {

        super.onCreate();

        myAppDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Characters").allowMainThreadQueries().build();

    }

}
