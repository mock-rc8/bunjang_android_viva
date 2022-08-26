package com.example.bunjang_clone.src.home

import com.example.bunjang_clone.src.home.models.RecommendResponse

interface RecommendActivityInterface {

    fun onGetDataSuccess(response : RecommendResponse)

    fun onGetDataFail(message : String)

}