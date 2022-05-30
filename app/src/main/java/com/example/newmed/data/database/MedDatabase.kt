package com.example.newmed.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newmed.data.entity.PatientEntity
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [PatientEntity::class],
    version = 27,
    exportSchema = true
)

abstract class MedDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao

    companion object {
        @Volatile
        private var INSTANCE: MedDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): MedDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MedDatabase::class.java,
                    "med_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}