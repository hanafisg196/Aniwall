package com.apptesting.test.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomePagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    lateinit var fragments: ArrayList<Fragment>
    private var titles: ArrayList<String>? = null


    override fun getItemCount(): Int {
        return fragments!!.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments!![position]
    }


}