package com.example.ti.casual_v1;

import android.content.Context;
import android.content.SharedPreferences;

import com.firebase.client.Firebase;

/**
 * Esta classe vai permitir usar o firebase em qualquer lugar do projeto
 */

public class ClassePadrao {
    public static String PREF ="com.example.ti.casual_v1.PREF";
    private static Firebase firebase;

    private ClassePadrao(){}

    public static Firebase getFirebase(){
        if (firebase == null){
            firebase = new Firebase("https://casual-v1-1.firebaseio.com");
        }
        return (firebase);
    }

    static public void saveSP(Context context, String key, String value){
        SharedPreferences sp = context.getSharedPreferences(PREF,Context.MODE_PRIVATE);
        sp.edit().putString(key,value).apply();
    }

    static public String getSP(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences(PREF,Context.MODE_PRIVATE);
        String token = sp.getString(key,"");
        return (token);
    }
}
