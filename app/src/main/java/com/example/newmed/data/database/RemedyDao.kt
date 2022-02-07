package com.example.newmed.data.database

import androidx.room.*
import com.example.newmed.data.entity.RemedyEntity.Companion.TABLE_NAME
import com.example.newmed.data.entity.RemedyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RemedyDao {

    @Query("SELECT * FROM $TABLE_NAME ORDER BY id ASC")
    fun getAllRemedy(): Flow<List<RemedyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remedy: RemedyEntity)

    @Query("SELECT * FROM $TABLE_NAME WHERE ${RemedyEntity.COLUMN_ID} = :id")
    suspend fun getRemedyById(id: Int): RemedyEntity

    @Update
    suspend fun updateRemedy(remedy: RemedyEntity)
}