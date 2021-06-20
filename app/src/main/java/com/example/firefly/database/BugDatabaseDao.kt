package com.example.firefly.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BugDatabaseDao {
    @Insert
    suspend fun insertBug(bug: Bug)

    @Delete
    suspend fun deleteBug(bug: Bug)

    @Query("select * from Bug where id = :id")
    suspend fun loadOneBug(id: Long): Bug

    @Query("select*from Bug")
    fun loadAllBug(): LiveData<List<Bug>>

    @Query("select*from Bug where name LIKE '%' || :name || '%'")
    fun findBugs(name: String): LiveData<List<Bug>>

    @Query("delete from Bug")
    suspend fun deleteAllBug()
}