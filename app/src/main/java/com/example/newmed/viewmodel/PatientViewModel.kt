package com.example.newmed.viewmodel

import androidx.lifecycle.*
import com.example.newmed.database.PatientEntity
import com.example.newmed.model.PatientModel
import com.example.newmed.reposotiry.PatientRepository
import com.example.newmed.server.ApiExchange
import com.example.newmed.server.ExchangeItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call

class PatientViewModel(private val repository: PatientRepository) : ViewModel() {

    val allPatient: LiveData<List<PatientModel>> = repository.getAllPatient().asLiveData()

    private val _patient = MutableLiveData<PatientEntity>()
    val patient: LiveData<PatientEntity> = _patient

    //добавление пациента в БД
    fun addPatient(
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
        }
    }

    fun getPatientById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _patient.postValue(repository.getPatientById(id))
        }
    }

    fun updateInfoPatient() {
        viewModelScope.launch(Dispatchers.IO) {
            _patient.value?.let {
                repository.updateInfoPatient (it.id, it.traumaticBrain, it.diabetes)
                _patient.postValue(it.copy(traumaticBrain = !it.traumaticBrain, diabetes = !it.diabetes))
            }
        }
    }
}