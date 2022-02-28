package com.example.newmed

import android.app.Application
import com.example.newmed.data.database.MedDatabase
import com.example.newmed.data.reposotiry.PatientRepository
import com.example.newmed.data.reposotiry.RemedyRepository
import com.example.newmed.presentation.interfaces.PatientDeleteById
import com.example.newmed.presentation.interfaces.PatientInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PatientApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { MedDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { PatientRepository(database.patientDao()) }
    val deleteById by lazy {PatientDeleteById(repository)}
    val repositoryRemedy by lazy { RemedyRepository(database.remedyDao()) }
}