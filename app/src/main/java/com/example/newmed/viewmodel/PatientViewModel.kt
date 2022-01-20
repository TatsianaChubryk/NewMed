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

    fun addPatient(patinet: PatientEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPatient(patinet)
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

    fun deletePatientById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
           repository.deletePatientById(id)
        }
    }
}