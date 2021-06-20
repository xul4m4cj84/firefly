package com.example.firefly.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Bug::class])
abstract class BugDatabase: RoomDatabase() {
    abstract val bugDatabaseDao: BugDatabaseDao
    companion object {

        @Volatile
        private var INSTANCE: BugDatabase? = null

        fun getInstance(context: Context): BugDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BugDatabase::class.java,
                        "scene_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}