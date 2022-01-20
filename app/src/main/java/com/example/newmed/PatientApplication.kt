package com.example.newmed

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.newmed.database.MedDatabase
import com.example.newmed.database.PatientDao
import com.example.newmed.reposotiry.PatientRepository
import com.example.newmed.viewmodel.PatientViewModel
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

class PatientApplication: Application() {


    /* val applicationScope = CoroutineScope(SupervisorJob())

  val database by lazy { MedDatabase.getDatabase(this, applicationScope) }
  val repository by lazy { PatientRepository(database.patientDao()) }*/



    private var context: Context? = null

    override fun onCreate() {
        super.onCreate()
        context = this

        startKoin(
            this, listOf(
                viewModelModele,
                patientDBModule
            )
        )
    }
}

    val viewModelModele = module {
        viewModel { PatientViewModel(get()) }
    }

    val patientDBModule = module {

        fun providePatientDB(application: Application): MedDatabase {
            return  Room.databaseBuilder(application, MedDatabase::class.java, "med_database")
                .fallbackToDestructiveMigration()
                .build()
        }

        fun providePatientDao(database: MedDatabase): PatientDao {
            return  database.patientDao
        }

        single { providePatientDB(get()) }
        single { providePatientDao(get()) }
        single { PatientRepository(get()) }
    }