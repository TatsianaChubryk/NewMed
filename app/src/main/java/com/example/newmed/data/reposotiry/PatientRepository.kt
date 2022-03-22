package com.example.newmed.data.reposotiry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newmed.presentation.viewmodel.PatientViewModel
import com.example.newmed.data.database.PatientDao
import com.example.newmed.data.entity.PatientEntity
import com.example.newmed.data.entity.asDomainModel
import com.example.newmed.presentation.interfaces.DeleteByIdInterface
import com.example.newmed.presentation.interfaces.PatientInterface
import kotlinx.coroutines.flow.map

class PatientRepository(private val patientDao: PatientDao): PatientInterface {

    //вернет всех пациентов из БД
    override fun getAllPatient() = patientDao.getAllPatient().map { it.asDomainModel() }

    override suspend fun addPatient(patient: PatientEntity) {
        patientDao.insert(patient)
    }

    override suspend fun getPatientById(id: Int) = patientDao.getPatientById(id)

    override suspend fun updatePatient(patient: PatientEntity) {
        patientDao.updatePatient(patient)
    }

    override suspend fun deleteAll() = patientDao.deleteAll()

    override suspend fun deletePatientById(id: Int) = patientDao.deletePatientById(id)

}

class PatientViewModelFactory(private val repository: PatientInterface, private val patientDeleteById: DeleteByIdInterface) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PatientViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PatientViewModel(repository, patientDeleteById) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}