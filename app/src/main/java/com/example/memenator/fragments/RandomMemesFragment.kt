package com.example.memenator.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memenator.R
import com.example.memenator.adapters.RecyclerAdapter
import com.example.memenator.databinding.FragmentRandomMemesBinding
import com.example.memenator.models.ResponseModel
import com.example.memenator.viewmodels.MainViewModel

class RandomMemesFragment : Fragment() {


    private lateinit var binding : FragmentRandomMemesBinding

    private val viewModel: MainViewModel by viewModels()

    private lateinit var  adapter : RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_random_memes, container, false)
        init()

        return binding.root
    }

    fun init(){

        viewModel.init()
        initRecycler()
        observes()



    }

    private fun initRecycler(){
        adapter = RecyclerAdapter()
        binding.recycler1.layoutManager = LinearLayoutManager(requireActivity())
        binding.recycler1.adapter = adapter

    }

    private fun observes(){

        viewModel._itemsLiveData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it.memes as MutableList<ResponseModel.Memes>)
        })


    }



}