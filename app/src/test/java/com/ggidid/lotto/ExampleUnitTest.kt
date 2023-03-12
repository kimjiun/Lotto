package com.ggidid.lotto

import androidx.lifecycle.MutableLiveData
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val numbers: MutableLiveData<MutableList<Int>> = MutableLiveData()

        numbers.value = mutableListOf<Int>()
        numbers.value?.clear()

        val list = (1..45).toMutableList()

        repeat(6){
            val index = Random().nextInt(list.size)
            numbers.value?.add(list.get(index))
            list.removeAt(index)
        }

        println("${numbers.value}")
        assertEquals("", numbers.value.toString())
    }
}