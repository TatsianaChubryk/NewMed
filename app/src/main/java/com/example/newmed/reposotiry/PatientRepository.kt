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
        active: Boolean,
        nameCall: String,
        numberCall: String,
        addressPatient: String,
        namePatient: String,
        agePatient: String,
        numberPatient: String,
        pricePatient: Int,
        dayNight: Boolean,
        alko: Boolean,
        distance: Int,
        time: Int,
        traumaticBrain: Boolean,
        diabetes: Boolean,
        hypertension: Boolean,
        ishemiya: Boolean,
        arrhytmia: Boolean,
        gemma: Boolean,
        cirrhosis: Boolean/*,
        magnia: Double,
        ringera: Double,
        galoperidol: Double,
        dimedrol: Double,
        fenibut: Double,
        tiamin: Double,
        unitiol: Double,
        sonnat: Double,
        karbazipin: Double,
        normogidron: Double,
        anaprilin: Double*/
    ) {
        return patientDao.insert(
            PatientEntity(
                id = 0,
                date = date,
                active = active,
                nameCall = nameCall,
                numberCall = numberCall,
                addressPatient = addressPatient,
                namePatient = namePatient,
                agePatient = agePatient,
                numberPatient = numberPatient,
                pricePatient = pricePatient,
                dayNight = dayNight,
                alko = alko,
                distance = distance,
                time = time,
                traumaticBrain = traumaticBrain,
                diabetes = diabetes,
                hypertension = hypertension,
                ishemiya = ishemiya,
                arrhytmia = arrhytmia,
                gemma = gemma,
                cirrhosis = cirrhosis/*,
                magnia = magnia,
                ringera = ringera,
                galoperidol = galoperidol,
                dimedrol = dimedrol,
                fenibut = fenibut,
                tiamin = tiamin,
                unitiol = unitiol,
                sonnat = sonnat,
                karbazipin = karbazipin,
                normogidron = normogidron,
                anaprilin = anaprilin*/
            )
        )
    }

    suspend fun getPatientById(id: Int) = patientDao.getPatientById(id)

    suspend fun updatePatient(paient: PatientEntity) {
        patientDao.updatePatient(paient)
    }

    //очистит БД
    suspend fun deleteAll() = patientDao.deleteAll()
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