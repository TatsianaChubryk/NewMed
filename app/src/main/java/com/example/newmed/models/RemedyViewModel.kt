package com.example.newmed.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.newmed.reposotiry.RemedyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RemedyViewModel(private val repositoryRemedy: RemedyRepository): ViewModel() {

    val allRemedy: LiveData<List<RemedyModel>> = repositoryRemedy.getAllRemedy().asLiveData()

    //добавляет препараты в БД
    fun addRemedy(
        nameRemedy: String,
        amountRemedy: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryRemedy.addRemedy(
                nameRemedy = nameRemedy,
                amountRemedy = amountRemedy
            )}
    }
}