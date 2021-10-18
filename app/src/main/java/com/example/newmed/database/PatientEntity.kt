package com.example.newmed.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newmed.models.PatientModel
import com.example.newmed.database.PatientEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class PatientEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,

    @ColumnInfo(name = COLUMN_DATE)
    val date: String,

    @ColumnInfo(name = COLUMN_NAMECALL)
    val nameCall: String,

    @ColumnInfo(name = COLUMN_NUMBERCALL)
    val numberCall: String,

    @ColumnInfo(name = COLUMN_ADDRESSPATIENT)
    val addressPatient: String,

    @ColumnInfo(name = COLUMN_NAMEPATIENT)
    val namePatient: String,

    @ColumnInfo(name = COLUMN_AGEPATIENT)
    val agePatient: String,

    @ColumnInfo(name = COLUMN_NUMBERPATIENT)
    val numberPatient: String,

    @ColumnInfo(name = COLUMN_PRICEPATIENT)
    val pricePatient: String
) {
    companion object {
        const val TABLE_NAME = "patient_table"
        const val COLUMN_ID = "id"
        const val COLUMN_DATE = "date"
        const val COLUMN_NAMECALL = "nameCall"
        const val COLUMN_NUMBERCALL = "numberCall"
        const val COLUMN_ADDRESSPATIENT = "addressPatient"
        const val COLUMN_NAMEPATIENT = "namePatient"
        const val COLUMN_AGEPATIENT = "agePatient"
        const val COLUMN_NUMBERPATIENT = "numberPatient"
        const val COLUMN_PRICEPATIENT = "pricePatient"
    }
}

//бизнесс модель моего объкта и делаю map из data слоя в бизнесс слой
fun List<PatientEntity>.asDomainModel(): List<PatientModel> {
    return map {
        PatientModel(
            id = it.id,
            date = it.date,
            nameCall = it.nameCall,
            numberCall = it.numberCall,
            addressPatient = it.addressPatient,
            namePatient = it.namePatient,
            agePatient = it.agePatient,
            numberPatient = it.numberPatient,
            pricePatient = it.pricePatient
        )
    }
}