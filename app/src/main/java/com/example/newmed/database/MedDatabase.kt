package com.example.newmed.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PatientEntity::class, RemedyEntity::class],
    version = 8,
    exportSchema = true
)

abstract class MedDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao
    abstract fun remedyDao(): RemedyDao

    companion object {
        @Volatile
        private var INSTANCE: MedDatabase? = null

        fun getDatabase(context: Context): MedDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MedDatabase::class.java,
                    "med_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}