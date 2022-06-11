package com.kavrin.borutoapp.data.repository

import com.kavrin.borutoapp.data.local.BorutoDatabase
import com.kavrin.borutoapp.domain.model.Hero
import com.kavrin.borutoapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(borutoDatabase: BorutoDatabase) : LocalDataSource {

	private val heroDao = borutoDatabase.heroDao()

	override suspend fun getSelectedHero(heroId: Int): Hero {
		return heroDao.getSelectedHero(heroId = heroId)
	}
}