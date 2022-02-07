package com.example.newmed

import com.example.newmed.data.database.RemedyEntity
import com.example.newmed.domain.model.RemedyModel
import org.junit.Test

internal class RemedyTest {

    @Test
    fun infoRemedy() {
        val remedyList = mutableListOf<RemedyModel>()
        remedyList.add(RemedyModel(0, "р-р Димедрол 1% 1мл.", 10))
        remedyList.add(RemedyModel(1, "пор. Нормогидрон", 6))
        remedyList.add(RemedyModel(2, "р-р Унитиол 5% 5мл", 7))
        remedyList.add(RemedyModel(3, "таб. Соннат 7,5мг", 0))

        val remedyEntity = RemedyEntity(
            id = 4,
            nameRemedy = "таб. Анаприлин 10мг.",
            amountRemedy = 3
        )

        val remedyObject = remedyEntity

        assert(
            remedyObject.id == remedyEntity.id
                    && remedyObject.nameRemedy == remedyEntity.nameRemedy
                    && remedyObject.amountRemedy == remedyEntity.amountRemedy
        )
    }
}