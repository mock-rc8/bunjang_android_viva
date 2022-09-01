package com.example.bunjang_clone.src.home.detail.buy.models

data class PaymentData(
    var userIdx: Int,
    var productIdx: Int,
    var transactionMethod: String,
    var requirement: String,
    var commissionPrice: Int,
    var usePoint: Int,
    var finalPrice: Int,
    var paymentMethod: String
)