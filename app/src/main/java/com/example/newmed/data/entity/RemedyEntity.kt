package com.example.newmed.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newmed.data.entity.RemedyEntity.Companion.TABLE_NAME
import com.example.newmed.domain.model.RemedyModel

@Entity(tableName = TABLE_NAME)
data class RemedyEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,

    @ColumnInfo(name = COLUMN_NAMEREMEDY)
    val nameRemedy: String,

    @ColumnInfo(name = COLUMN_AMOUNTREMEDY)
    val amountRemedy: Int
) {
    companion object {
        const val TABLE_NAME = "remedy_table"
        const val COLUMN_ID = "id"
        const val COLUMN_NAMEREMEDY = "nameRemedy"
        const val COLUMN_AMOUNTREMEDY = "amountRemedy"
    }
}

//бизнесс модель моего объкта и делаю map из data слоя в бизнесс слой
fun List<RemedyEntity>.asDomainModel(): List<RemedyModel> {
    return map {
        RemedyModel(
            id = it.id,
            nameRemedy = it.nameRemedy,
            amountRemedy = it.amountRemedy
        )
    }
}