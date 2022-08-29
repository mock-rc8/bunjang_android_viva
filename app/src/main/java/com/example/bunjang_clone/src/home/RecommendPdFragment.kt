package com.example.bunjang_clone.src.home

import android.os.Bundle
import android.view.View
import com.example.bunjang_clone.R
import com.example.bunjang_clone.config.BaseFragment
import com.example.bunjang_clone.databinding.FragmentRecommendpdBinding
import com.example.bunjang_clone.src.home.adapter.RecommendPdRvAdapter
import com.example.bunjang_clone.src.home.models.RecommendResponse

class RecommendPdFragment : BaseFragment<FragmentRecommendpdBinding>(FragmentRecommendpdBinding::bind, R.layout.fragment_recommendpd), RecommendActivityInterface {

    private lateinit var recommendAdapter : RecommendPdRvAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRecommendPdList()

    }

    private fun getRecommendPdList() {
        RecommendService(this).pdGetData()

        recommendAdapter = RecommendPdRvAdapter()
        binding.rvHomeRecommendItem.adapter = recommendAdapter
        binding.rvHomeRecommendItem.setHasFixedSize(true)
    }

    override fun onGetDataSuccess(response: RecommendResponse) {
        recommendAdapter.setRecommendItem(response.result.toMutableList())
    }

    override fun onGetDataFail(message: String) {
    }
}