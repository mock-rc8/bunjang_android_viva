package com.example.bunjang_clone.src.home.detail.models

data class DetailResponse(
    var isSuccess : Boolean,
    var code : Int,
    var message : String,
    var result : DetailResult
)
data class DetailResult(
    var productIdx : Int,
    var productImgURL : String,
    var price : Int,
    var pay : Int,
    var productName : String,
    var address : String,
    var created : String,
    var views : Int,
    var heart : Int,
    var talk : Int,
    var status : String,
    var quantity : Int,
    var shippingFee : String,
    var exchange : String,
    var productExplaination : String,
    var hashtag : List<String>
)
