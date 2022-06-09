package com.kavrin.borutoapp.data.repository

import androidx.paging.PagingData
import com.kavrin.borutoapp.domain.model.Hero
import com.kavrin.borutoapp.domain.repository.DataStoreOperations
import com.kavrin.borutoapp.domain.repository.RemoteDaraSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
	private val dataStore: DataStoreOperations,
	private val remote: RemoteDaraSource
) {

	fun getAllHeroes(): Flow<PagingData<Hero>> {
		return remote.getAllHeroes()
	}

	suspend fun saveOnBoardingState(completed: Boolean) {
		dataStore.saveOnBoardingState(completed = completed)
	}

	fun readOnBoardingState(): Flow<Boolean> {
		return dataStore.readOnBoardingState()
	}
}