package com.kavrin.borutoapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.kavrin.borutoapp.domain.repository.DataStoreOperations
import com.kavrin.borutoapp.util.Constants.PREFERENCES_KEY
import com.kavrin.borutoapp.util.Constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

// Create preferencesDataStore
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreOperationsImpl(context: Context) : DataStoreOperations {

	// Save Boolean preferences key
	private object PreferencesKey {
		val onBoardingKey = booleanPreferencesKey(name = PREFERENCES_KEY)
	}

	// Create an instance of DataStore
	private val dataStore = context.dataStore

	/**
	 * Save on boarding state
	 *
	 * @param completed
	 *
	 * If user finish welcome screen we want to save that state in DataStore
	 */
	override suspend fun saveOnBoardingState(completed: Boolean) {
		dataStore.edit { preferences ->
			preferences[PreferencesKey.onBoardingKey] = completed
		}
	}

	/**
	 * Read on boarding state
	 *
	 * @return OnBoarding State
	 *
	 * If user enter the application for first time welcome screen will be shown otherwise this function return true to prevent showing welcome screen again
	 */
	override fun readOnBoardingState(): Flow<Boolean> {
		return dataStore.data
			.catch { exception ->
				if (exception is IOException) emit(emptyPreferences())
				else throw exception
			}
			.map { preferences ->
				val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
				// Emit the Boolean state
				onBoardingState
			}
	}
}