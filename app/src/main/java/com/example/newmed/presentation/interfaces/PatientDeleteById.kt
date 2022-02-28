package com.example.newmed.presentation.interfaces

import com.example.newmed.data.reposotiry.PatientRepository

class PatientDeleteById(private val repository: PatientRepository): DeleteByIdInterface {
    override suspend fun patientDeleteClick(id: Int) {
        repository.deletePatientById(id)
    }
}