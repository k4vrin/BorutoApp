package com.kavrin.borutoapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kavrin.borutoapp.util.Constants.HERO_DATABASE_TABLE

@Entity(tableName = HERO_DATABASE_TABLE)
data class Hero(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val month: String,
    val day: String,
    val family: List<String>,
    val abilities: List<String>,
    val natureTypes: List<String>
)

/**
 * We serialize List<Hero> object and send it from our backend server (ktor) to the app.
 * In the app it will be be deserialized and cached to the Room database.
 * the database will act as the single source of truth every time we need to display data in the app
 * and we only send a request to the server if it's needed, to save bandwidth. Otherwise we will
 * always use database
 *
 */
