package com.kavrin.borutoapp.di

import android.app.Application
import com.kavrin.borutoapp.data.pref.DataStoreOperationsImpl
import com.kavrin.borutoapp.domain.repository.DataStoreOperations
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
}