package com.example.bunjang_clone.src.home.models

data class RecommendResponse(
    var isSuccess : Boolean,
    var code : Int,
    var message : String,
    var result : List<RecommendItem>
)
data class RecommendItem(
    var productIdx : Int,
    var productURL : String,
    var price : Int,
    var productName : String,
    var address : String,
    var created : String,
    var heartCount : Int,
    var userHeart : Int,
    var pay : Int
)
