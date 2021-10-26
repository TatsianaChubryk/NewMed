package com.example.newmed.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newmed.model.PatientModel
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
    val pricePatient: String,

    @ColumnInfo(name = COLUMN_AlKO)
    val alko: Boolean,

    @ColumnInfo(name = COLUMN_BRAIN)
    val traumaticBrain: Boolean,

    @ColumnInfo(name = COLUMN_DIABETES)
    val diabetes: Boolean,

    @ColumnInfo(name = COLUMN_HYPERTENSION)
    val hypertension: Boolean,

    @ColumnInfo(name = COLUMN_ISHEMIA)
    val ishemiya: Boolean,

    @ColumnInfo(name = COLUMN_ARRHYTMIA)
    val arrhytmia: Boolean,

    @ColumnInfo(name = COLUMN_GEMMA)
    val gemma: Boolean,

    @ColumnInfo(name = COLUMN_CIRRHOSIS)
    val cirrhosis: Boolean,

    @ColumnInfo(name = COLUMN_PULSE)
    val pulse: String
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
        const val COLUMN_AlKO = "alko"
        const val COLUMN_BRAIN = "traumaticBrain"
        const val COLUMN_DIABETES = "diabetes"
        const val COLUMN_HYPERTENSION = "hypertension"
        const val COLUMN_ISHEMIA = "ishemiya"
        const val COLUMN_ARRHYTMIA = "arrhytmia"
        const val COLUMN_GEMMA = "gemma"
        const val COLUMN_CIRRHOSIS = "cirrhosis"
        const val COLUMN_PULSE = "pulse"
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
            pricePatient = it.pricePatient,
            alko = it.alko,
            traumaticBrain = it.traumaticBrain,
            diabetes = it.diabetes,
            hypertension = it.hypertension,
            ishemiya = it.ishemiya,
            arrhytmia = it.arrhytmia,
            gemma = it.gemma,
            cirrhosis = it.cirrhosis,
            pulse = it.pulse
        )
    }
}