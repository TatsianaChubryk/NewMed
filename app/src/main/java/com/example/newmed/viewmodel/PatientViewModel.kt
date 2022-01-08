package com.example.newmed.viewmodel

import androidx.lifecycle.*
import com.example.newmed.database.MedDatabase
import com.example.newmed.database.PatientDao
import com.example.newmed.database.PatientEntity
import com.example.newmed.databinding.FragmentInfoPatientBinding
import com.example.newmed.model.PatientModel
import com.example.newmed.reposotiry.PatientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call

class PatientViewModel(private val repository: PatientRepository) : ViewModel() {

    val allPatient: LiveData<List<PatientModel>> = repository.getAllPatient().asLiveData()

    private val _patient = MutableLiveData<PatientEntity>()
    val patient: LiveData<PatientEntity> = _patient

    private lateinit var binding: FragmentInfoPatientBinding
    private var patientId = -1

    //добавление пациента в БД
    fun addPatient(
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
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPatient(
                //id = id,
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
        }
    }

    fun getPatientById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _patient.postValue(repository.getPatientById(id))
        }
    }

    fun updatePatient(patinet: PatientEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePatient(patinet)
        }
    }

    fun clearDB() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}