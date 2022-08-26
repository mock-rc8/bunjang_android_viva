package com.example.bunjang_clone.src.home.detail

import com.example.bunjang_clone.src.home.detail.models.DetailResponse

interface DetailActivityInterface {

    fun onGetDetailDataSuccess(response : DetailResponse)

    fun onGetDetailDataFail(message : String)

}