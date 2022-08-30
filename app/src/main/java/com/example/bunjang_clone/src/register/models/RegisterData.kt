package com.example.bunjang_clone.src.register.models

data class RegisterData(
    var userIdx : Int,
    var productsName : String,
    var imageURL : String,
    var subImageURL : List<String>,
    var address : String,
    var mainCategory : String,
    var subCategory : String,
    var hashtagText : String,
    var price : Int,
    var shoppingFee : String,
    var quantity : Int,
    var productStatus : String,
    var exchange : String,
    var productExplaination : String,
    var pay : Int
)