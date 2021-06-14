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
import com.example.memenator.databinding.FragmentRegisterBinding
import com.example.memenator.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class RegisterFragment : Fragment() {

    private lateinit var binding : FragmentRegisterBinding
    private var mAuth: FirebaseAuth? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_register, container, false)
        mAuth = FirebaseAuth.getInstance()
        init()
        return binding.root
    }

    private fun init(){
        btnListeners()
    }

    private fun btnListeners(){

        binding.button.setOnClickListener{
            register()
        }

        binding.goBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun register(){
        val email  = binding.email.text.toString().trim()
        val name  = binding.userName.text.toString().trim()
        val age = binding.age.text.toString().trim()
        val password = binding.passwordd.text.toString().trim()

        if (name.isEmpty()){
            binding.userName.error = "sheiyvanet UserName"
            binding.userName.requestFocus()
            return
        }

        if (age.isEmpty()){
            binding.age.error = "sheiyvanet Asaki"
            binding.age.requestFocus()
            return
        }

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
            binding.passwordd.error = "sheiyvanet Password"
            binding.passwordd.requestFocus()
            return
        }



        if (password.length < 6){
            binding.passwordd.error = "sheiyvanet minimum 6 simbolo"
            binding.passwordd.requestFocus()
            return
        }

        binding.progressBarr.visibility = View.VISIBLE

        mAuth?.createUserWithEmailAndPassword(email,password)?.addOnCompleteListener { task ->
            if(task.isSuccessful){
                val user = User(name,age,email)
                FirebaseAuth.getInstance().currentUser?.let {
                    FirebaseDatabase.getInstance().getReference("Users")
                        .child(it.uid).setValue(user)
                }




                Toast.makeText(context, "warmatebit daregistrirdit", Toast.LENGTH_SHORT).show()
                binding.progressBarr.visibility = View.GONE
                findNavController().navigateUp()

            }else{
                findNavController().navigateUp()
                Toast.makeText(context, task.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }



    }
}