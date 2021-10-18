package com.example.newmed.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newmed.database.PatientEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {

    //получает всех пациентов из БД
    @Query("SELECT * FROM $TABLE_NAME ORDER BY id DESC")
    fun getAllPatient(): Flow<List<PatientEntity>>

    //добавляет пациента в БД
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(patient: PatientEntity)

    @Query("DELETE FROM $TABLE_NAME" +
            " WHERE ${PatientEntity.COLUMN_ID} = :id")
    suspend fun deletePatientById(id: Int)
}