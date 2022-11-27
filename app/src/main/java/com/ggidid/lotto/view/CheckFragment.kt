package com.ggidid.lotto.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ggidid.lotto.R
import com.ggidid.lotto.databinding.FragmentCheckBinding
import com.ggidid.lotto.viewmodel.CheckViewModel

class CheckFragment : Fragment() {
    lateinit var binding: FragmentCheckBinding
    lateinit var viewModel: CheckViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_check, container, false)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(CheckViewModel::class.java)
        viewModel.getLottoInfoLiveData().observe(viewLifecycleOwner, Observer {resp ->
            if(resp != null){
                binding.roundText.text = "${resp.drwNo} ${getString(R.string.fragement_check_round)}"
                binding.firstNumber.text = resp.drwtNo1
                binding.secondNumber.text = resp.drwtNo2
                binding.thirdNumber.text = resp.drwtNo3
                binding.fourthNumber.text = resp.drwtNo4
                binding.fifthNumber.text = resp.drwtNo5
                binding.sixthNumber.text = resp.drwtNo6
                binding.bonusNumber.text = resp.bnusNo
            }
        })

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLottoInfo()
    }
}