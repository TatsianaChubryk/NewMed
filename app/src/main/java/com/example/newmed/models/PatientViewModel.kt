package com.example.newmed.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.newmed.reposotiry.PatientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientViewModel(private val repository: PatientRepository): ViewModel() {

    val allPatient: LiveData<List<PatientModel>> = repository.getAllPatient().asLiveData()

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
        pricePatient: String
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
                pricePatient = pricePatient
            )}
    }
}