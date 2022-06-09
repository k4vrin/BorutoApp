package com.kavrin.borutoapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kavrin.borutoapp.util.Constants.HERO_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = HERO_REMOTE_KEYS_DATABASE_TABLE)
data class HeroRemoteKeys(
	@PrimaryKey(autoGenerate = false)
	val id: Int,
	val prevPage: Int?,
	val nextPage: Int?
)

/**
 * Whenever we fetch some data or our heroes from our API in order to paginate that data properly and cache it
 * in a local database as well, we need to use something that's called the remote mediator and that remote mediator implementation
 * will need to use those remote keys in order to paginated through the data properly, and they get more data whenever we need to
 */
