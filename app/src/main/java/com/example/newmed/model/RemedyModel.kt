package com.example.newmed.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RemedyModel(

    val id: Int,
    val nameRemedy: String,
    val amountRemedy: Int
   // val augmentRemedy: Int
): Parcelable