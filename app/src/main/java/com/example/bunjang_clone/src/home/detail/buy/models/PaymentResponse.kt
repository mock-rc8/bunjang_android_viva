package com.example.bunjang_clone.src.home.detail.buy.models

data class PaymentResponse(
    var isSuccess : Boolean,
    var code : Int,
    var message : String,
    var result : Payment
)
data class Payment(
    var productIdx : Int,
    var productName : String,
    var finalPrice : Int
)
