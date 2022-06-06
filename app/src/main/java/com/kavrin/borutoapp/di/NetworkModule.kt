package com.kavrin.borutoapp.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kavrin.borutoapp.data.remote.BorutoApi
import com.kavrin.borutoapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	@Singleton
	fun provideHttpClient(): OkHttpClient {
		return OkHttpClient.Builder()
			.connectTimeout(15, TimeUnit.MINUTES)
			.readTimeout(15, TimeUnit.MINUTES)
			.build()
	}

	@ExperimentalSerializationApi
	@Provides
	@Singleton
	fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
		val contentType = MediaType.get("application/json")
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(okHttpClient)
			.addConverterFactory(Json.asConverterFactory(contentType)) // Kotlinx serialization
			.build()
	}

	@Provides
	@Singleton
	fun provideBorutoApi(
		retrofit: Retrofit
	): BorutoApi {
		return retrofit.create(BorutoApi::class.java)
	}
}