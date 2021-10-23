package com.example.newmed.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newmed.database.RemedyEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface RemedyDao {

    //получает всех препараты из БД
    @Query("SELECT * FROM $TABLE_NAME ORDER BY id DESC")
    fun getAllRemedy(): Flow<List<RemedyEntity>>

    //добавляет препарат в БД
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remedy: RemedyEntity)

   /* @Update("UPDATE * FROM $TABLE_NAME" +
    "WHERE ${RemedyEntity.COLUMN_ID} = :id")
    suspend fun updateRemedeById(id: Int): RemedyEntity*/

  /*  //удаляет препалат из БД
    @Query("DELETE FROM ${PatientEntity.TABLE_NAME}" +
            " WHERE ${PatientEntity.COLUMN_ID} = :id")
    suspend fun deletePatientById(id: Int)*/
}