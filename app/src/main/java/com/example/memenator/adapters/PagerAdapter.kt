package com.example.memenator.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter (val fragments : MutableList<Fragment>, fragment : FragmentManager, lifecycle : Lifecycle) : FragmentStateAdapter(fragment,lifecycle){
    override fun getItemCount()=fragments.size

    override fun createFragment(position: Int) = fragments[position]


}