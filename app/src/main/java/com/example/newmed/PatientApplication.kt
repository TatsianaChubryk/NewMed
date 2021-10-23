package com.example.newmed

import android.app.Application
import com.example.newmed.database.MedDatabase
import com.example.newmed.reposotiry.PatientRepository
import com.example.newmed.reposotiry.RemedyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PatientApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { MedDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { PatientRepository(database.patientDao()) }
    val repositoryRemedy by lazy { RemedyRepository(database.remedyDao()) }
}