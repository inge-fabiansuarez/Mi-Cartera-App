package com.fabiansuarez.micartera.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.fabiansuarez.micartera.model.entity.Account
import com.fabiansuarez.micartera.model.entity.Category
import com.fabiansuarez.micartera.model.enums.TypeAccount
import com.fabiansuarez.micartera.model.enums.TypeCategory
import com.fabiansuarez.micartera.model.repository.AccountRepository
import com.fabiansuarez.micartera.model.repository.CategoryRepository
import com.fabiansuarez.micartera.util.OnOperationCallback

class CategoryFormActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val categoryRepository = CategoryRepository()
    var category: Category = Category()

    // Lista de opciones del enumerado
    var categoryTypes: List<TypeCategory> = TypeCategory.values().toList()
    var callback: OnOperationCallback? = null

    fun onSaveButtonClick() {
        categoryRepository.add(category, object : OnOperationCallback {
            override fun onAccountAdded() {
                callback?.let {
                    it.onAccountAdded()
                }
            }

            override fun onAccountAddError(error: Exception) {
                callback?.let {
                    it.onAccountAdded()
                }
            }

        })
    }

    public fun onTypeCategorySelected(selectedType: TypeCategory) {
        category?.let {
            it.typeCategory = selectedType
        }
    }
}