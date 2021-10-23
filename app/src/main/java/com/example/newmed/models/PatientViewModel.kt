package com.example.newmed.models

import androidx.lifecycle.*
import com.example.newmed.database.PatientEntity
import com.example.newmed.reposotiry.PatientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientViewModel(private val repository: PatientRepository): ViewModel() {

    val allPatient: LiveData<List<PatientModel>> = repository.getAllPatient().asLiveData()

    private val _patient = MutableLiveData<PatientEntity>()
    val patient: LiveData<PatientEntity> = _patient

    //добавление пациента в БД
    fun addPatient(
        //id: Int,
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
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPatient(
                //id = id,
                date = date,
                nameCall = nameCall,
                numberCall = numberCall,
                addressPatient = addressPatient,
                namePatient = namePatient,
                agePatient = agePatient,
                numberPatient = numberPatient,
                pricePatient = pricePatient,
                adPatient = adPatient
            )}
    }

    fun getPatientById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _patient.postValue(repository.getPatientById(id))
        }

    }
}