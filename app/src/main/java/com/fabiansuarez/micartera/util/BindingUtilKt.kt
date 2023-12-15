package com.fabiansuarez.micartera.util

import android.R
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseMethod
import com.fabiansuarez.micartera.model.enums.TypeAccount
import com.fabiansuarez.micartera.model.enums.TypeCategory
import com.fabiansuarez.micartera.viewmodel.AccountFormActivityViewModel
import com.fabiansuarez.micartera.viewmodel.CategoryFormActivityViewModel
import kotlin.math.log

//import com.bumptech.glide.Glide

@BindingAdapter("image")
fun loadImage(imageView: ImageView, url: String) {
    //Glide.with(imageView.context).load(url).into(imageView)
}

@InverseMethod("stringToDouble")
fun doubleToString(value: Double): String {
    return value.toString()
}

@InverseMethod("doubleToString")
fun stringToDouble(value: String): Double {
    if (value == "") {
        return 0.0
    }
    return value.toDouble()
}

@BindingAdapter("app:adapter", "app:viewModel")
fun setAdapter(
    view: AutoCompleteTextView,
    items: List<TypeAccount>?,
    viewModel: AccountFormActivityViewModel
) {
    items?.let {
        val adapter = ArrayAdapter(view.context, R.layout.simple_dropdown_item_1line, it.map { it ->
            it.displayName
        })
        view.setAdapter(adapter)
        view.setOnItemClickListener { adapterView, view, position, l ->
            viewModel.onTypeAccountSelected(items[position])
        }
    }
}