package com.example.bunjang_clone.src.home.models

import com.example.bunjang_clone.src.login.models.LoginResult

data class RecommendResponse(
    var isSuccess : Boolean,
    var code : Int,
    var message : String,
    var result : List<RecommendItem>
)
data class RecommendItem(
    var productURL : String,
    var price : Int,
    var productName : String,
    var address : String,
    var created : String,
    var heartCount : Int,
    var userHeart : Int,
    var pay : Int
)
