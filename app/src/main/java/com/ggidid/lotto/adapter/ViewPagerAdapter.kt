package com.ggidid.lotto.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ggidid.lotto.view.HomeFragment
import com.ggidid.lotto.view.GenerateFragment
import com.ggidid.lotto.view.HistoryFragment
import com.ggidid.lotto.view.MainActivity

class ViewPagerAdapter(activty: MainActivity): FragmentStateAdapter(activty) {
    var list: ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = list.get(position)

    init {
        list.add(HomeFragment())
        list.add(HistoryFragment())
        list.add(GenerateFragment())
    }
}