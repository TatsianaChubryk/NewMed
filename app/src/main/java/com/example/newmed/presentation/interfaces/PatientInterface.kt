package com.example.newmed.presentation.interfaces

import com.example.newmed.data.entity.PatientEntity
import com.example.newmed.domain.model.PatientModel
import kotlinx.coroutines.flow.Flow

interface PatientInterface {

    fun getAllPatient(): Flow<List<PatientModel>>

    suspend fun addPatient(patient: PatientEntity)

    suspend fun getPatientById(id: Int): PatientEntity

    suspend fun updatePatient(patient: PatientEntity)

    suspend fun deleteAll()

    suspend fun deletePatientById(id: Int)
}