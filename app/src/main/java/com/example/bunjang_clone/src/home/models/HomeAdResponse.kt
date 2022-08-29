package com.example.bunjang_clone.src.home.models

data class HomeAdResponse(
    var isSuccess : Boolean,
    var code : Int,
    var message : String,
    var result : AdResult
)
data class AdResult(
    var adImageList : List<String>
)
