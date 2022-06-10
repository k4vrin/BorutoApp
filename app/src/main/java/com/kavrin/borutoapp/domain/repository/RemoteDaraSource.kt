package com.kavrin.borutoapp.domain.repository

import androidx.paging.PagingData
import com.kavrin.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDaraSource {

	fun getAllHeroes(): Flow<PagingData<Hero>>
	fun searchHeroes(query: String): Flow<PagingData<Hero>>
}