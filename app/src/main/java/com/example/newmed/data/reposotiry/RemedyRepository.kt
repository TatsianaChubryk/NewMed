package com.example.newmed.data.reposotiry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newmed.data.database.RemedyDao
import com.example.newmed.data.entity.RemedyEntity
import com.example.newmed.data.entity.asDomainModel
import com.example.newmed.presentation.viewmodel.RemedyViewModel
import kotlinx.coroutines.flow.map

class RemedyRepository(private val remedyDao: RemedyDao) {

    //вернет все препараты из БД
    fun getAllRemedy() = remedyDao.getAllRemedy().map { it.asDomainModel() }

    //добавить препараты в БД
    suspend fun addRemedy(
        nameRemedy: String,
        amountRemedy: Int
        // augmentRemedy: Int
    ) {
        return remedyDao.insert(
            RemedyEntity(
                id = 0,
                nameRemedy = nameRemedy,
                amountRemedy = amountRemedy
                //augmentRemedy = augmentRemedy
            )
        )
    }

    suspend fun getRemedyById(id: Int) = remedyDao.getRemedyById(id)

    suspend fun updateRemedy(remedy: RemedyEntity) {
        remedyDao.updateRemedy(remedy)
    }
}

class RemedyViewModelFactory(private val repositoryRemedy: RemedyRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RemedyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RemedyViewModel(repositoryRemedy) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}