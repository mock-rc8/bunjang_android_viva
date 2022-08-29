package com.example.bunjang_clone.src.home.detail.buy

import com.example.bunjang_clone.src.home.detail.buy.models.BuyResponse

interface BuyActivityInterface {

    fun onGetBuyDataSuccess(response : BuyResponse)

    fun onGetBuyDataFail(message : String)

}