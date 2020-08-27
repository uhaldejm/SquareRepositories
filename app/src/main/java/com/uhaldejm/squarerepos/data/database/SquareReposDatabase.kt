package com.uhaldejm.squarerepos.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [BookmarkEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class SquareReposDatabase : RoomDatabase() {

  companion object {

    private const val DATABASE_NAME = "squareRepos.db"

    fun create(context: Context): SquareReposDatabase =
        Room.databaseBuilder(context, SquareReposDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
  }

  abstract fun bookmarkDao(): BookmarkDao

}