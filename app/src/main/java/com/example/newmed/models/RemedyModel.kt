package com.example.newmed.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RemedyModel(

    val id: Int,
    val nameRemedy: String,
    val amountRemedy: Int
): Parcelable