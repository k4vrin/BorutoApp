package com.kavrin.borutoapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kavrin.borutoapp.data.local.BorutoDatabase
import com.kavrin.borutoapp.util.Constants.BORUTO_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

	@Provides
	@Singleton
	fun provideDatabase(
		@ApplicationContext content: Context,
	): RoomDatabase {
		return Room.databaseBuilder(
			content,
			BorutoDatabase::class.java,
			BORUTO_DB
		).build()
	}

}