package com.example.component_basic_jetpack.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class TokenManager (private val context: Context){
    companion object{
        val TOKEN_KEY = stringPreferencesKey("bearer_token")
    }

    suspend fun saveToken(token:String){
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }
    suspend fun getToken():String?{
        val preferences = context.dataStore.data.map {
            it[TOKEN_KEY]
        }
        return preferences.first()
    }

    suspend fun deleteToken(){
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }
}

