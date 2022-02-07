package com.example.newmed.presentation.viewmodel

import androidx.lifecycle.*
import com.example.newmed.data.entity.RemedyEntity
import com.example.newmed.domain.model.RemedyModel
import com.example.newmed.data.reposotiry.RemedyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RemedyViewModel(private val repositoryRemedy: RemedyRepository) : ViewModel() {

    val allRemedy: LiveData<List<RemedyModel>> = repositoryRemedy.getAllRemedy().asLiveData()

    private val _amount = MutableLiveData<RemedyEntity>()
    val amount: LiveData<RemedyEntity> = _amount
/*
    //добавляет препараты в БД
    fun addRemedy(
        nameRemedy: String,
        amountRemedy: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryRemedy.addRemedy(
                nameRemedy = nameRemedy,
                amountRemedy = amountRemedy
            )
        }
    }*/

    fun getRemedyById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _amount.postValue(repositoryRemedy.getRemedyById(id))
        }
    }

    fun updateRemedy(remedy: RemedyEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryRemedy.updateRemedy(remedy)
        }
    }
}