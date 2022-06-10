package com.kavrin.borutoapp.di

import android.app.Application
import com.kavrin.borutoapp.data.repository.DataStoreOperationsImpl
import com.kavrin.borutoapp.data.repository.Repository
import com.kavrin.borutoapp.domain.repository.DataStoreOperations
import com.kavrin.borutoapp.domain.use_cases.UseCases
import com.kavrin.borutoapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.kavrin.borutoapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.kavrin.borutoapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.kavrin.borutoapp.domain.use_cases.search_heroes.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

	@Provides
	@Singleton
	fun provideDataStoreOperations(
		app: Application
	): DataStoreOperations {
		return DataStoreOperationsImpl(context = app)
	}

	@Provides
	@Singleton
	fun provideUseCases(
		repository: Repository
	): UseCases {
		return UseCases(
			saveOnBoardingUseCase = SaveOnBoardingUseCase(repository = repository),
			readOnBoardingUseCase = ReadOnBoardingUseCase(repository = repository),
			getAllHeroesUseCase = GetAllHeroesUseCase(repository = repository),
			searchHeroesUseCase = SearchHeroesUseCase(repository = repository)
		)
	}
}

// UseCase: Simple interaction between the user and our application