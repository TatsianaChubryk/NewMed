package com.example.newmed.database

import androidx.room.*
import com.example.newmed.database.PatientEntity.Companion.COLUMN_BRAIN
import com.example.newmed.database.PatientEntity.Companion.COLUMN_DIABETES
import com.example.newmed.database.PatientEntity.Companion.COLUMN_ID
import com.example.newmed.database.PatientEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {

    @Query("SELECT * FROM $TABLE_NAME ORDER BY id DESC")
    fun getAllPatient(): Flow<List<PatientEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(patient: PatientEntity)

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
    suspend fun getPatientById(id: Int): PatientEntity

    @Query("UPDATE $TABLE_NAME SET $COLUMN_BRAIN = :brain, $COLUMN_DIABETES = :diabetes WHERE $COLUMN_ID = :patientId")
    suspend fun updateInfoPatient(patientId: Int, brain: Boolean, diabetes: Boolean)
}