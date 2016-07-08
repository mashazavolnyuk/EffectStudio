package com.example.darkmaleficent.effectstudio;

import com.vk.sdk.VKSdk;

/**
 * Created by Dark Maleficent on 04.07.2016.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
    }
}
