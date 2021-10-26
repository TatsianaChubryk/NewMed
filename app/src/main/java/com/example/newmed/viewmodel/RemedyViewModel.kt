package com.example.newmed.viewmodel

import androidx.lifecycle.*
import com.example.newmed.database.RemedyEntity
import com.example.newmed.model.RemedyModel
import com.example.newmed.reposotiry.RemedyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RemedyViewModel(private val repositoryRemedy: RemedyRepository) : ViewModel() {

    val allRemedy: LiveData<List<RemedyModel>> = repositoryRemedy.getAllRemedy().asLiveData()

    private val _amount = MutableLiveData<RemedyEntity>()
    val amount: LiveData<RemedyEntity> = _amount

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
    }

    fun updateAmountRemedy() {
        viewModelScope.launch(Dispatchers.IO) {
            val amount = _amount.value
            amount?.let {
                repositoryRemedy.updateAmountRemedy(it.id, it.amountRemedy)
                _amount.postValue(it.copy(amountRemedy = 10))

            }
        }
    }
}