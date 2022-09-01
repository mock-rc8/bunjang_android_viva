package com.example.bunjang_clone.src.home.detail.buy

import com.example.bunjang_clone.src.home.detail.buy.models.BuyResponse
import com.example.bunjang_clone.src.home.detail.buy.models.PaymentResponse
import com.example.bunjang_clone.src.home.detail.buy.models.UserIdx

interface BuyActivityInterface {

    fun onGetBuyDataSuccess(response : BuyResponse)

    fun onGetBuyDataFail(message : String)

    fun onPaymentSuccess(response : PaymentResponse)

    fun onPaymentFail(message : String)

    fun onUserDataSuccess(response : UserIdx)

    fun onUserDataFail(message : String)

}