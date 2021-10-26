package com.example.newmed.reposotiry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newmed.viewmodel.PatientViewModel
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
        alko: Boolean,
        traumaticBrain: Boolean,
        diabetes: Boolean,
        hypertension: Boolean,
        ishemiya: Boolean,
        arrhytmia: Boolean,
        gemma: Boolean,
        cirrhosis: Boolean,
        pulse: String
    ) {
        return patientDao.insert(
            PatientEntity(
                id = 0,
                date = date,
                nameCall = nameCall,
                numberCall = numberCall,
                addressPatient = addressPatient,
                namePatient = namePatient,
                agePatient = agePatient,
                numberPatient = numberPatient,
                pricePatient = pricePatient,
                alko = alko,
                traumaticBrain = traumaticBrain,
                diabetes = diabetes,
                hypertension = hypertension,
                ishemiya = ishemiya,
                arrhytmia = arrhytmia,
                gemma = gemma,
                cirrhosis = cirrhosis,
                pulse = pulse
            )
        )
    }

    suspend fun getPatientById(id: Int) = patientDao.getPatientById(id)

    suspend fun updateInfoPatient(patientId: Int, brain: Boolean, diabetes: Boolean) {
        patientDao.updateInfoPatient(patientId, brain, diabetes)
    }
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