package com.example.newmed.database

import androidx.room.*
import com.example.newmed.database.RemedyEntity.Companion.COLUMN_AMOUNTREMEDY
import com.example.newmed.database.RemedyEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface RemedyDao {

    @Query("SELECT * FROM $TABLE_NAME ORDER BY id ASC")
    fun getAllRemedy(): Flow<List<RemedyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remedy: RemedyEntity)

    @Query("UPDATE $TABLE_NAME SET $COLUMN_AMOUNTREMEDY = :amountRemedy WHERE ${RemedyEntity.COLUMN_ID} = :id")
    suspend fun updateAmountRemedy(id: Int, amountRemedy: Int)
}