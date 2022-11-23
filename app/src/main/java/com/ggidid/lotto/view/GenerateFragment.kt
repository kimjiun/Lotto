
package com.ggidid.lotto.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ggidid.lotto.R
import com.ggidid.lotto.databinding.FragmentCheckBinding
import com.ggidid.lotto.databinding.FragmentGenerateBinding
import com.ggidid.lotto.viewmodel.CheckViewModel
import com.ggidid.lotto.viewmodel.GenerateViewModel

class GenerateFragment : Fragment() {
    lateinit var binding: FragmentGenerateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_generate, container, false)

        binding.lifecycleOwner = this
        val viewModel = ViewModelProvider(this).get(GenerateViewModel::class.java)
        binding.viewModel = viewModel
        
        return binding.root
    }
}