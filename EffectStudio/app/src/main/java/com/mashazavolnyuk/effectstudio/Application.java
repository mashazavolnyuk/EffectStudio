package com.mashazavolnyuk.effectstudio;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.vk.sdk.VKSdk;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
}
