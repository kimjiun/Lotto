package com.ggidid.lotto.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ggidid.lotto.R
import com.ggidid.lotto.databinding.FragmentHistoryBinding
import com.ggidid.lotto.viewmodel.HistoryViewModel

class HistoryFragment : Fragment() {
    lateinit var binding: FragmentHistoryBinding
    lateinit var viewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLottoInfo()

        binding.swipeLayout.setOnRefreshListener {

        }
    }
}