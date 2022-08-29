package com.example.bunjang_clone.src.home.detail.buy

import com.example.bunjang_clone.src.home.detail.models.DetailResponse

interface BuyActivityInterface {

    fun onGetDetailDataSuccess(response : DetailResponse)

    fun onGetDetailDataFail(message : String)

}