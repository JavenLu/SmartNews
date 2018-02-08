package javen.example.com.commonlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import javen.example.com.commonlibrary.CommonLibraryApplication;


/**
 * Created by Javen on 29/11/2017.
 */

public class SharedPreferencesUtil {
    private static final String APPLICATION_PREFERENCE_NAME = "sp_name";
    public static final String INIT_CHANNEL_DB = "int_channel_db";

    private static SharedPreferences getApplicationPreference(Context context) {
        return context.getSharedPreferences(APPLICATION_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getApplicationPreference(context).edit();
    }

    /*Int*/
    public static void savePreferenceInt(String key, int value) {
        getEditor(CommonLibraryApplication.getContext()).putInt(key, value).commit();
    }

    public static int getPreferenceInt(String key, int defaultValue) {
        return getApplicationPreference(CommonLibraryApplication.getContext()).getInt(key, defaultValue);
    }


    /*Boolean*/
    public static void savePreferenceBoolean(String key, boolean value) {
        getEditor(CommonLibraryApplication.getContext()).putBoolean(key, value).commit();
    }

    public static boolean getPreferenceBoolean(String key, boolean defaultValue) {
        return getApplicationPreference(CommonLibraryApplication.getContext()).getBoolean(key, defaultValue);
    }


    /*String*/
    public static void savePreferenceString(String key, String value) {
        getEditor(CommonLibraryApplication.getContext()).putString(key, value).commit();
    }


    public static String getPreferenceString(String key) {
        return getApplicationPreference(CommonLibraryApplication.getContext()).getString(key, "");
    }

    public static String getPreferenceStringWithDefault(String key, String defaultString) {
        return getApplicationPreference(CommonLibraryApplication.getContext()).getString(key, defaultString);
    }

    /*Long*/
    public static void savePreferenceLong(String key, long value) {
        getEditor(CommonLibraryApplication.getContext()).putLong(key, value).commit();
    }

    public static long getPreferenceLong(String key, long defaultValue) {
        return getApplicationPreference(CommonLibraryApplication.getContext()).getLong(key, defaultValue);
    }


    /*Float*/
    public static void savePreferenceFloat(String key, float value) {
        getEditor(CommonLibraryApplication.getContext()).putFloat(key, value).commit();
    }


    public static float getPreferenceFloat(String key, float defaultValue) {
        return getApplicationPreference(CommonLibraryApplication.getContext()).getFloat(key, defaultValue);
    }


}
