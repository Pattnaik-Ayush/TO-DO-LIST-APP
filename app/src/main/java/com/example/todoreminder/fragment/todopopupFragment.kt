package com.example.todoreminder.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.todoreminder.databinding.FragmentTodopopupBinding
import com.google.android.material.textfield.TextInputEditText

class todopopupFragment : DialogFragment() {

    private lateinit var binding : FragmentTodopopupBinding
    private lateinit var listener: DialogNextBtnClickListener

    fun setListner(listener: HomeFragment){
        this.listener = listener
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTodopopupBinding.inflate(inflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerEvent()
    }
    private fun registerEvent(){
        binding.todoNextBtn.setOnClickListener {
            val todotask = binding.todoEt.text.toString()
            if(todotask.isNotEmpty()){
                Toast.makeText(context,"Task added succesfully",Toast.LENGTH_SHORT)
                listener.onSaveTask(todotask , binding.todoEt)
            }else{
                Toast.makeText(context,"Please add some task",Toast.LENGTH_SHORT)
            }
        }
        binding.todoClose.setOnClickListener {
            dismiss()
        }
    }
    interface DialogNextBtnClickListener{
        fun onSaveTask(todo: String, todoEt: TextInputEditText)
    }
}