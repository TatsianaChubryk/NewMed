package com.example.newmed.presentation.viewmodel

import androidx.lifecycle.*
import com.example.newmed.data.entity.PatientEntity
import com.example.newmed.domain.model.PatientModel
import com.example.newmed.data.reposotiry.PatientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientViewModel(
    private val repository: PatientRepository/*,
    private val deleteByIdUseCase: DeleteByIdUseCase*/
) : ViewModel() {

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

    fun updatePatient(patinet: PatientEntity) {
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

   /* fun callPatient(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.callPatient(id)
        }
    }

    fun getPrice(price: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPrice(price)
        }
    }*/
}