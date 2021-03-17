package com.example.clone_instagrama;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("r5zF4AzROpEayUwu9CtfGwFzUmm5NpmBJATUyOPS")
                .clientKey("OJXmvlaAoP14URMerDKhj4RG5swBAS0NyS6tzkDx")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
