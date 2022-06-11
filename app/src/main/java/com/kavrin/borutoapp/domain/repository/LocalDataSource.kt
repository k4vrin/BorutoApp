package com.kavrin.borutoapp.domain.repository

import com.kavrin.borutoapp.domain.model.Hero

interface LocalDataSource {

	suspend fun getSelectedHero(heroId: Int): Hero
}