package com.example.memenator.fragments

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memenator.R
import com.example.memenator.adapters.SavedRecyclerAdapter
import com.example.memenator.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding

    private lateinit var adapter : SavedRecyclerAdapter

    private var database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private var myRef : DatabaseReference = database.getReference("UserSaved")
    private var userId = FirebaseAuth.getInstance().currentUser?.uid

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        init()
        return binding.root
    }

    private fun init(){

        getDataDatabase()
        initRecycler()

    }


    private fun initRecycler(){
        adapter = SavedRecyclerAdapter()
        binding.recycler.layoutManager = LinearLayoutManager(requireActivity())
        binding.recycler.adapter = adapter

    }



    private fun getDataDatabase(){



        userId?.let {
            myRef.child(it).addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {


                    var item = snapshot.value.toString().dropLast(1)

                    var items = item.split(",")





                    adapter.getitems(items as MutableList<String>)

                }

                override fun onCancelled(error: DatabaseError) {
                    d("databaseError","$error")
                }

            })

        }

    }




}
