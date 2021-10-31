package com.example.newmed.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PatientModel (
    val id: Int,
    val date: String,
    val nameCall: String,
    val numberCall: String,
    val addressPatient: String,
    val namePatient: String,
    val agePatient: String,
    val numberPatient: String,
    val pricePatient: Int,
    val alko: Boolean,
    val traumaticBrain: Boolean,
    val diabetes: Boolean,
    val hypertension: Boolean,
    val ishemiya: Boolean,
    val arrhytmia: Boolean,
    val gemma: Boolean,
    val cirrhosis: Boolean,
    val magnia: Int,
    val ringera: Int,
    val galoperidol: Int,
    val dimedrol: Int,
    val fenibut: Int,
    val tiamin: Int,
    val unitiol: Int,
    val sonnat: Int,
    val karbazipin: Int,
    val normogidron: Int,
    val anaprilin: Int
): Parcelable