package com.ggidid.lotto.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide.init
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class GenerateViewModel @Inject constructor(): ViewModel() {
    val TAG = "JIUN/${javaClass.name}"

    var _numbers: MutableLiveData<MutableList<Int>> = MutableLiveData()
    val numbers: LiveData<MutableList<Int>>
        get() = _numbers

    init {
        _numbers.value = mutableListOf<Int>()
    }

    fun generateNumbers(){
        _numbers.value?.clear()

        val list = (1..45).toMutableList()

        repeat(6){
            val index = Random().nextInt(list.size)
            _numbers.value?.add(list.get(index))
            list.removeAt(index)
        }

        Log.d(TAG, "${_numbers.value}")
    }
}