package com.example.newmed.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PatientModel (
    val id: Int,
    val date: String,
    val active: Boolean,
    val nameCall: String,
    val numberCall: String,
    val addressPatient: String,
    val namePatient: String,
    val agePatient: String,
    val numberPatient: String,
    val pricePatient: Int,
    val dayNight: Boolean,
    val alko: Boolean,
    val distance: Int,
    val time: Int,
    val min: Int,
    val traumaticBrain: Boolean,
    val diabetes: Boolean,
    val hypertension: Boolean,
    val ishemiya: Boolean,
    val arrhytmia: Boolean,
    val gemma: Boolean,
    val cirrhosis: Boolean
): Parcelable