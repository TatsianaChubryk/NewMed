package com.example.newmed

import android.app.Application
import com.example.newmed.database.MedDatabase
import com.example.newmed.reposotiry.PatientRepository
import com.example.newmed.reposotiry.RemedyRepository

class PatientApplication: Application() {

    val database by lazy { MedDatabase.getDatabase(this) }
    val repository by lazy { PatientRepository(database.patientDao()) }
    val repositoryRemedy by lazy { RemedyRepository(database.remedyDao()) }
}