package com.example.memenator.fragments

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.memenator.R
import com.example.memenator.databinding.FragmentFirstScreenBinding
import com.google.firebase.auth.FirebaseAuth

class FirstScreenFragment : Fragment() {


    private lateinit var binding : FragmentFirstScreenBinding

    private var mAuth: FirebaseAuth? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_first_screen, container, false)
        mAuth = FirebaseAuth.getInstance()
        init()
        return binding.root

    }


    private fun init(){

        btnClickListeners()

    }


    private fun btnClickListeners(){
        binding.registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_firstScreenFragment_to_registerFragment)
        }
        binding.logInBtn.setOnClickListener{
            userLogin()

        }
    }

    private fun userLogin() {
        val email = binding.email.text.toString().trim()
        val password = binding.password.text.toString().trim()

        if (email.isEmpty()){
            binding.email.error = "sheiyvanet Email"
            binding.email.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.email.error = "sheiyvanet swori Email"
            binding.email.requestFocus()
            return
        }
        if (password.isEmpty()){
            binding.password.error = "sheiyvanet Password"
            binding.password.requestFocus()
            return
        }



        if (password.length < 6){
            binding.password.error = "sheiyvanet minimum 6 simbolo"
            binding.password.requestFocus()
            return
        }

        binding.progressBar.visibility = View.VISIBLE

        mAuth?.signInWithEmailAndPassword(email,password)?.addOnCompleteListener { task ->
            if (task.isSuccessful){
                if (FirebaseAuth.getInstance().currentUser?.isEmailVerified == true){
                    binding.email.text.clear()
                    binding.password.text.clear( )
                    findNavController().navigate(R.id.action_firstScreenFragment_to_mainScreenFragment)
                }else{
                    binding.progressBar.visibility = View.GONE
                    FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                    Toast.makeText(context, "daadasturet verifikacia mailze", Toast.LENGTH_SHORT).show()

                }


            }else{
                binding.progressBar.visibility = View.GONE
                Toast.makeText(context, task.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }

    }




}