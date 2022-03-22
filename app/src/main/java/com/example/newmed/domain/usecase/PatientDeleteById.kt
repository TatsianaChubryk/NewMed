package com.example.newmed.domain.usecase

import com.example.newmed.data.reposotiry.PatientRepository
import com.example.newmed.presentation.interfaces.DeleteByIdInterface

class PatientDeleteById(private val repository: PatientRepository): DeleteByIdInterface {
    override suspend fun patientDeleteClick(id: Int) {
        repository.deletePatientById(id)
    }
}