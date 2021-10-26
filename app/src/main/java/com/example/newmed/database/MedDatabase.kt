package com.example.newmed.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [PatientEntity::class, RemedyEntity::class],
    version = 17,
    exportSchema = true
)

abstract class MedDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao
    abstract fun remedyDao(): RemedyDao

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
                    .addCallback(RemedyCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class RemedyCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        remedyDatabase(database.remedyDao())
                    }
                }
            }
        }

        private suspend fun remedyDatabase(remedyDao: RemedyDao) {

            var remedy = RemedyEntity(1, "р-р Магния сульфата 25% 5мл.", 10)
            remedyDao.insert(remedy)
            remedy = RemedyEntity(2, "р-р Рингера 250мл.", 0)
            remedyDao.insert(remedy)
            remedy = RemedyEntity(3, "р-р Галоперидол 0,5% 1мл.", 0)
            remedyDao.insert(remedy)
            remedy = RemedyEntity(4, "р-р Димедрол 1% 1мл.", 0)
            remedyDao.insert(remedy)
            remedy = RemedyEntity(5, "р-р Фенибута 10мг/мл 100мл.", 0)
            remedyDao.insert(remedy)
            remedy = RemedyEntity(6, "р-р Тиамина хлорид 5% 1мл.", 0)
            remedyDao.insert(remedy)
            remedy = RemedyEntity(7, "р-р Унитиол 5% 5мл.", 0)
            remedyDao.insert(remedy)
            remedy = RemedyEntity(8, "таб. Соннат 7,5мг.", 0)
            remedyDao.insert(remedy)
            remedy = RemedyEntity(9, "таб. Карбазипин 200мг.", 0)
            remedyDao.insert(remedy)
            remedy = RemedyEntity(10, "пор. Нормогидрон", 0)
            remedyDao.insert(remedy)
            remedy = RemedyEntity(11, "таб. Анаприлин 10мг.", 0)
            remedyDao.insert(remedy)
        }
    }
}