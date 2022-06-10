package com.kavrin.borutoapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kavrin.borutoapp.data.local.BorutoDatabase
import com.kavrin.borutoapp.data.paging_source.HeroRemoteMediator
import com.kavrin.borutoapp.data.paging_source.SearchHeroSource
import com.kavrin.borutoapp.data.remote.BorutoApi
import com.kavrin.borutoapp.domain.model.Hero
import com.kavrin.borutoapp.domain.repository.RemoteDaraSource
import com.kavrin.borutoapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
	private val borutoApi: BorutoApi,
	private val borutoDatabase: BorutoDatabase
) : RemoteDaraSource {

	private val heroDao = borutoDatabase.heroDao()

	override fun getAllHeroes(): Flow<PagingData<Hero>> {
		val pagingSourceFactory = { heroDao.getAllHeroes() }
		return Pager(
			config = PagingConfig(pageSize = ITEMS_PER_PAGE),
			remoteMediator = HeroRemoteMediator(
				borutoApi = borutoApi,
				borutoDatabase = borutoDatabase
			),
			pagingSourceFactory = pagingSourceFactory
		).flow
	}

	override fun searchHeroes(query: String): Flow<PagingData<Hero>> {
		return Pager(
			config = PagingConfig(pageSize = ITEMS_PER_PAGE),
			pagingSourceFactory = {
				SearchHeroSource(
					borutoApi = borutoApi,
					query = query
				)
			}
		).flow
	}
}