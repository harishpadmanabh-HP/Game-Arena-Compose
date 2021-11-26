package com.example.gamearenacompose.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.gamearenacompose.data.remote.models.User
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Prefs @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    var user:User?
        get()=try{
            Timber.e("Prefs user saved")
            Gson().fromJson(sharedPreferences.getString("user",""),User::class.java)
    }catch (e:Exception){
        null
    }

    set(value) = sharedPreferences.edit().putString("user",Gson().toJson(value)).apply()

}