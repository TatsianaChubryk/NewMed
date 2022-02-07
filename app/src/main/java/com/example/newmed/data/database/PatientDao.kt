package com.example.newmed.data.database

import androidx.room.*
import com.example.newmed.data.entity.PatientEntity
import com.example.newmed.data.entity.PatientEntity.Companion.COLUMN_ID
import com.example.newmed.data.entity.PatientEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {

    @Query("SELECT * FROM $TABLE_NAME ORDER BY id ASC")
    fun getAllPatient(): Flow<List<PatientEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(patient: PatientEntity)

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
    suspend fun getPatientById(id: Int): PatientEntity

    @Update
    suspend fun updatePatient(patient: PatientEntity)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAll()

    @Query("DELETE FROM $TABLE_NAME" +
            " WHERE $COLUMN_ID = :id")
    suspend fun deletePatientById(id: Int)
}