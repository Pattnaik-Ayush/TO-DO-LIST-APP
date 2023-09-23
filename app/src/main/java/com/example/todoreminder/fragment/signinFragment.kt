package com.example.todoreminder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todoreminder.R
import com.example.todoreminder.databinding.FragmentSigninBinding
import com.google.firebase.auth.FirebaseAuth


class signinFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController
    private lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSigninBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View,savedInstanceState: Bundle?){
        init(view)
        registerEvent()
    }
    private fun init(view: View){
        navController = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()

    }

    private fun registerEvent(){
        binding.btn.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val pass = binding.pass.text.toString().trim()

            if(email.isNotEmpty() && pass.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(context, "Signing Successfully", Toast.LENGTH_SHORT).show()
                        navController.navigate(R.id.action_signinFragment_to_homeFragment)
                    } else {
                        Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
        binding.textView.setOnClickListener{
                    navController.navigate(R.id.action_signinFragment_to_signupFragment)
        }
    }
}