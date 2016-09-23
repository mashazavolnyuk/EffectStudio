package com.mashazavolnyuk.effectstudio.effect;

import com.mashazavolnyuk.effectstudio.ImageChanger;

import java.lang.reflect.Constructor;

/**
 * Created by Dark Maleficent on 23.09.2016.
 */

public class ImageChangerFactory {

//    public static ISimpleChangeImage createChanger(int effectID){
//        ISimpleChangeImage effect = null;
//        switch (effectID) {
//            case 1:
//                effect = new GrayScaleEffect();
//                break;
//            case 2:
//                effect = new BlurEffect();
//                break;
//            case 3:
//                effect = new BlurPixelEffect();
//                break;
//            case 4:
//                effect = new ReflectionEffect();
//                break;
//            case 5:
//                effect = new RemovalEffect();
//                break;
//            case 6:
//                effect = new FleaEffect();
//                break;
//            case 7:
//                effect = new ShowEffect();
//                break;
//            case 8:
//                effect = new TestEffect();
//                break;
//        }
//        return effect;
//    }

    public static ImageChanger createChanger(String changerName) {
        Class changerClass = getChangerClassByName(changerName);
        if (changerClass == null) {
            throw new IllegalArgumentException("Cannot instantiate unknown effect '" +
                    changerName + "'!");
        }
        return instantiateEffect(changerClass);
    }

    public static boolean isChangerSupported(String changerName) {
        return getChangerClassByName(changerName) != null;
    }

    private static Class getChangerClassByName(String className) {
        Class changerClass = null;

        // Get context's classloader; otherwise cannot load non-framework effects
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            changerClass = contextClassLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return changerClass;
    }

    private static ImageChanger instantiateEffect(Class changerClass) {
        // Make sure this is an Effect subclass
        try {
            changerClass.asSubclass(ImageChanger.class);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Attempting to allocate effect '" + changerClass
                    + "' which is not a subclass of Effect!", e);
        }

        // Look for the correct constructor
        Constructor changerConstructor = null;
        try {
            changerConstructor = changerClass.getConstructor();
        } catch (Exception e) {
            throw new RuntimeException("The effect class '" + changerClass + "' does not have "
                    + "the required constructor.", e);
        }

        // Construct the effect
        ImageChanger changer = null;
        try {
            changer = (ImageChanger)changerConstructor.newInstance();
        } catch (Throwable t) {
            throw new RuntimeException("There was an error constructing the effect '" + changerClass
                    + "'!", t);
        }

        return changer;
    }
}
