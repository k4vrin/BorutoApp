package com.kavrin.borutoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kavrin.borutoapp.domain.model.HeroRemoteKey

@Dao
interface HeroRemoteKeyDao {

	@Query("SELECT * FROM hero_remote_key_table WHERE id = :id")
	suspend fun getRemoteKey(id: Int): HeroRemoteKey?

	@Insert
	suspend fun addAllRemoteKeys(heroRemoteKeys: List<HeroRemoteKey>)

	@Query("DELETE FROM hero_remote_key_table")
	suspend fun deleteAllRemoteKeys()
}