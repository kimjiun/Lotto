
package com.ggidid.lotto.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ggidid.lotto.R
import com.ggidid.lotto.databinding.FragmentGenerateBinding
import com.ggidid.lotto.viewmodel.GenerateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenerateFragment : Fragment() {
    lateinit var binding: FragmentGenerateBinding
    val viewModel: GenerateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_generate, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        
        return binding.root
    }
}