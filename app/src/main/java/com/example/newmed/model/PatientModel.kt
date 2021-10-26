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
    val pricePatient: String,
    val alko: Boolean,
    val traumaticBrain: Boolean,
    val diabetes: Boolean,
    val hypertension: Boolean,
    val ishemiya: Boolean,
    val arrhytmia: Boolean,
    val gemma: Boolean,
    val cirrhosis: Boolean,
    val pulse: String
): Parcelable
