package com.example.memenator.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.memenator.R
import com.example.memenator.adapters.PagerAdapter
import com.example.memenator.databinding.FragmentMainScreenBinding
import com.google.firebase.auth.FirebaseAuth


class MainScreenFragment : Fragment() {


    private lateinit var binding : FragmentMainScreenBinding
    private lateinit var viewPagerAdapter: PagerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main_screen, container, false)
        init()
        return binding.root
    }

    private fun init(){

        initViewPager()

    }


    private fun initViewPager(){
        val fragments = mutableListOf<Fragment>()
        fragments.add(RandomMemesFragment())
        fragments.add(ProfileFragment())
        viewPagerAdapter = PagerAdapter(fragments,requireActivity().supportFragmentManager,lifecycle)
        binding.viewPager.adapter = viewPagerAdapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.navBar.menu.getItem(position).isChecked = true
            }
        })

        binding.navBar.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.randomMemesFragment ->binding.viewPager.currentItem = 0
                R.id.wholsomeMemesFragment ->binding.viewPager.currentItem = 1
                R.id.profileFragment ->{
                    FirebaseAuth.getInstance().signOut()
                    findNavController().navigateUp()}



            }

            true
        }
    }




}