package com.fabiansuarez.micartera.model.entity

import com.fabiansuarez.micartera.TypeCategory

data class Category(
    var id: Int = 0,
    var name: String = "",
    var typeCategory: TypeCategory = TypeCategory.SPENT
)
