package com.example.newmed.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RemedyModel(
    val id: Int,
    val nameRemedy: String,
    val amountRemedy: Int
): Parcelable