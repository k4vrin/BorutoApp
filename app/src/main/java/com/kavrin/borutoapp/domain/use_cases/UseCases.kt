package com.kavrin.borutoapp.domain.use_cases

import com.kavrin.borutoapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.kavrin.borutoapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.kavrin.borutoapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase


data class UseCases(
	// Welcome Screen UseCases
	val saveOnBoardingUseCase: SaveOnBoardingUseCase,
	val readOnBoardingUseCase: ReadOnBoardingUseCase,
	val getAllHeroesUseCase: GetAllHeroesUseCase
)
