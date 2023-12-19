package com.fabiansuarez.micartera.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.fabiansuarez.micartera.model.entity.Category
import com.fabiansuarez.micartera.model.enums.TypeCategory
import com.fabiansuarez.micartera.model.repository.CategoryRepository
import com.fabiansuarez.micartera.util.OnOperationCallback
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class CategoryFormActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val categoryRepository = CategoryRepository()
    var category: Category = Category()

    // Lista de opciones del enumerado
    var categoryTypes: List<TypeCategory> = TypeCategory.values().toList()
    var callback: OnOperationCallback? = null

    private val auth = Firebase.auth
    private val currentUser = auth.currentUser


    init {
        category.userId = currentUser?.uid ?: "Anonimo"
    }

    fun onSaveButtonClick() {
        categoryRepository.add(category, object : OnOperationCallback {
            override fun onAccountAdded() {
                callback?.let {
                    it.onAccountAdded()
                }
            }

            override fun onAccountAddError(error: String) {
                callback?.let {
                    it.onAccountAdded()
                }
            }

        })
    }

    fun onTypeCategorySelected(selectedType: TypeCategory) {
        category?.let {
            it.typeCategory = selectedType
        }
    }
}