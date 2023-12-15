package com.fabiansuarez.micartera.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.fabiansuarez.micartera.R
import com.fabiansuarez.micartera.databinding.ActivityAccountFormBinding
import com.fabiansuarez.micartera.databinding.ActivityCategoryFormBinding
import com.fabiansuarez.micartera.viewmodel.AccountFormActivityViewModel
import com.fabiansuarez.micartera.viewmodel.CategoryFormActivityViewModel

class CategoryFormActivity : AppCompatActivity() {

    private val viewModel: CategoryFormActivityViewModel by viewModels()
    private lateinit var binding: ActivityCategoryFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_form)
        val adapter = ArrayAdapter<String>(
            applicationContext,
            android.R.layout.simple_dropdown_item_1line,
            viewModel.categoryTypes.map { it.displayName }
        )
        binding.autoCompleteTxt.setAdapter(adapter)
        binding.autoCompleteTxt.setOnItemClickListener { adapterView, view, position, l ->
            viewModel.onTypeCategorySelected(viewModel.categoryTypes[position])
        }

        binding.viewModel = viewModel

    }
}