package com.fabiansuarez.micartera.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.fabiansuarez.micartera.R
import com.fabiansuarez.micartera.databinding.ActivityCategoryListBinding

class CategoryListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_list)
        binding.fabAddListCategory.setOnClickListener {
            startActivity(Intent(applicationContext, CategoryFormActivity::class.java))
        }
    }
}