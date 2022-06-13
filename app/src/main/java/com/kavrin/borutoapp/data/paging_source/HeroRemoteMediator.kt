package com.kavrin.borutoapp.data.paging_source

import androidx.paging.*
import androidx.room.withTransaction
import com.kavrin.borutoapp.data.local.BorutoDatabase
import com.kavrin.borutoapp.data.remote.BorutoApi
import com.kavrin.borutoapp.domain.model.Hero
import com.kavrin.borutoapp.domain.model.HeroRemoteKeys
import com.kavrin.borutoapp.util.Constants.FIRST_REQUEST_DEFAULT
import com.kavrin.borutoapp.util.Constants.ONE_MINUTE_IN_SECONDS
import com.kavrin.borutoapp.util.Constants.ONE_SECOND_IN_MILLIS
import com.kavrin.borutoapp.util.Constants.TWENTY_FOUR_HOURS_IN_MINUTES
import javax.inject.Inject

class HeroRemoteMediator @Inject constructor(
	private val borutoApi: BorutoApi,
	private val borutoDatabase: BorutoDatabase,
) : RemoteMediator<Int, Hero>() {

	/**
	 * Initialize
	 *
	 * @return
	 *
	 * Checking whether cached data is out of date and decide whether to trigger a remote refresh
	 */
	override suspend fun initialize(): InitializeAction {
		val currentTime = System.currentTimeMillis()
		val lastUpdated = heroRemoteKeysDao.getRemoteKeys(heroId = 1)?.lastUpdated ?: FIRST_REQUEST_DEFAULT
		val cacheTimeout = TWENTY_FOUR_HOURS_IN_MINUTES

		val diffInMinutes = (currentTime - lastUpdated) / ONE_SECOND_IN_MILLIS / ONE_MINUTE_IN_SECONDS
		return if (diffInMinutes.toInt() <= cacheTimeout)
			InitializeAction.SKIP_INITIAL_REFRESH
		else
			InitializeAction.LAUNCH_INITIAL_REFRESH

	}

	private val heroDao = borutoDatabase.heroDao()
	private val heroRemoteKeysDao = borutoDatabase.heroRemoteKeysDao()

	override suspend fun load(
		loadType: LoadType,
		state: PagingState<Int, Hero>,
	): MediatorResult {
		return try {
			val currentPage: Int = when (loadType) {
				LoadType.REFRESH -> {
					val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)
					remoteKeys?.nextPage?.minus(1) ?: 1
				}
				LoadType.PREPEND -> {
					val remoteKeys = getRemoteKeyForFirstItem(state)
					val prevPage = remoteKeys?.prevPage
						?: return MediatorResult.Success(
							endOfPaginationReached = remoteKeys != null
						)
					prevPage
				}
				LoadType.APPEND -> {
					val remoteKeys = getRemoteKeyForLastItem(state)
					val nextPage = remoteKeys?.nextPage
						?: return MediatorResult.Success(
							endOfPaginationReached = remoteKeys != null
						)
					nextPage
				}
			}

			val response = borutoApi.getAllHeroes(page = currentPage)

			if (response.heroes.isNotEmpty()) {
				borutoDatabase.withTransaction {
					// First time run app or explicitly Invalidating the data
					if (loadType == LoadType.REFRESH) {
						heroDao.deleteAllHeroes()
						heroRemoteKeysDao.deleteAllRemoteKeys()
					}

					val prevPage = response.prevPage
					val nextPage = response.nextPage
					val keys = response.heroes.map { hero ->
						HeroRemoteKeys(
							id = hero.id,
							prevPage = prevPage,
							nextPage = nextPage,
							lastUpdated = response.lastUpdated
						)
					}

					heroRemoteKeysDao.addAllRemoteKeys(heroRemoteKeys = keys)
					heroDao.addHeroes(heroes = response.heroes)
				}
			}
			MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
		} catch (e: Exception) {
			return MediatorResult.Error(e)
		}
	}

	private suspend fun getRemoteKeysClosestToCurrentPosition(
		state: PagingState<Int, Hero>,
	): HeroRemoteKeys? {
		return state.anchorPosition?.let { position ->
			// closestItemToPosition return a hero object
			state.closestItemToPosition(anchorPosition = position)?.id?.let { id ->
				heroRemoteKeysDao.getRemoteKeys(heroId = id)
			}
		}
	}

	private suspend fun getRemoteKeyForFirstItem(
		state: PagingState<Int, Hero>,
	): HeroRemoteKeys? {
		return state.pages.firstOrNull { page: PagingSource.LoadResult.Page<Int, Hero> ->
			page.data.isNotEmpty()
		}?.data?.firstOrNull()
			?.let { hero: Hero ->
				heroRemoteKeysDao.getRemoteKeys(heroId = hero.id)
			}
	}

	private suspend fun getRemoteKeyForLastItem(
		state: PagingState<Int, Hero>,
	): HeroRemoteKeys? {
		return state.pages.lastOrNull {
			it.data.isNotEmpty()
		}?.data?.lastOrNull()
			?.let { hero ->
				heroRemoteKeysDao.getRemoteKeys(heroId = hero.id)
			}
	}

//	private fun parseMillis(millis: Long): String {
//		val data = Date(millis)
//		val formatter = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ROOT)
//		return formatter.format(data)
//	}


}