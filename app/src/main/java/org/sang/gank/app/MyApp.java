package org.sang.gank.app;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by 王松 on 2016/9/17.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "c47f18f26dc9409bfeec04d423d67465");
    }
}
