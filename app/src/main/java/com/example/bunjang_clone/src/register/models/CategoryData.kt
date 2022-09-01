package com.example.bunjang_clone.src.register.models

data class CategoryData(
    var isSuccess : Boolean,
    var code : Int,
    var message : String,
    var result : List<CategoryResult>
)
data class CategoryResult(
    var mainCategoryIdx : Int,
    var categoryName : String
)
