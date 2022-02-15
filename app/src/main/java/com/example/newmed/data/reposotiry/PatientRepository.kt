package com.example.newmed.data.reposotiry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newmed.presentation.viewmodel.PatientViewModel
import com.example.newmed.data.database.PatientDao
import com.example.newmed.data.entity.PatientEntity
import com.example.newmed.data.entity.asDomainModel
import kotlinx.coroutines.flow.map

class PatientRepository(private val patientDao: PatientDao) {

    //вернет всех пациентов из БД
    fun getAllPatient() = patientDao.getAllPatient().map { it.asDomainModel() }

    suspend fun addPatient(paient: PatientEntity) {
        patientDao.insert(paient)
    }

    suspend fun getPatientById(id: Int) = patientDao.getPatientById(id)

    suspend fun updatePatient(paient: PatientEntity) {
        patientDao.updatePatient(paient)
    }

    suspend fun deleteAll() = patientDao.deleteAll()

    suspend fun deletePatientById(id: Int) = patientDao.deletePatientById(id)
}

class PatientViewModelFactory(private val repository: PatientRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PatientViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PatientViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}