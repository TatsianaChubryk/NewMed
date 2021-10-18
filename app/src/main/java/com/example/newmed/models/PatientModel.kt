package com.example.newmed.models

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
    val pricePatient: String
): Parcelable
