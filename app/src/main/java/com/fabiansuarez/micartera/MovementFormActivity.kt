package com.fabiansuarez.micartera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import com.fabiansuarez.micartera.databinding.ActivityMovementFormBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class MovementFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovementFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_movement_form)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movement_form)
        binding.btnBack.setOnClickListener { finish() }



        (binding.autoCompleteTxt as MaterialAutoCompleteTextView).setSimpleItems(arrayOf("Gasto", "Ingreso"))



    }
}