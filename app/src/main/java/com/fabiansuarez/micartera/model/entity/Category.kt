package com.fabiansuarez.micartera.model.entity

import com.fabiansuarez.micartera.model.enums.TypeCategory

data class Category(
    var id: String = "",
    var name: String = "",
    var typeCategory: TypeCategory = TypeCategory.SPENT,
    var userId: String = ""
)
