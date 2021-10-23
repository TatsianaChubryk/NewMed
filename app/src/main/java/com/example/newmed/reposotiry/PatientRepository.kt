package com.example.newmed.reposotiry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newmed.models.PatientViewModel
import com.example.newmed.database.PatientDao
import com.example.newmed.database.PatientEntity
import com.example.newmed.database.asDomainModel
import kotlinx.coroutines.flow.map

class PatientRepository(private val patientDao: PatientDao) {

    //вернет всех пациентов из БД
    fun getAllPatient() = patientDao.getAllPatient().map { it.asDomainModel() }

    //добавит пациента в БД
    suspend fun addPatient(
        date: String,
        nameCall: String,
        numberCall: String,
        addressPatient: String,
        namePatient: String,
        agePatient: String,
        numberPatient: String,
        pricePatient: String,
        adPatient: String
    ) {
        return patientDao.insert(PatientEntity(
            id = 0,
            date = date,
            nameCall = nameCall,
            numberCall = numberCall,
            addressPatient = addressPatient,
            namePatient = namePatient,
            agePatient = agePatient,
            numberPatient = numberPatient,
            pricePatient = pricePatient,
            adPatient = adPatient
        ))}

    //вернет пациента по его id
    suspend fun getPatientById(id: Int) = patientDao.getPatientById(id)

    //удалит patient из БД по его id
   // suspend fun deletePatientById(id: Int) = patientDao.deletePatientById(id)
}

class PatientViewModelFactory(private val repository: PatientRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PatientViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PatientViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}