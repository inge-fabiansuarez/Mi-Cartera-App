package com.fabiansuarez.micartera.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fabiansuarez.micartera.model.entity.Category
import com.fabiansuarez.micartera.util.OnOperationCallback
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CategoryRepository {
    private val NAME_COLLECTION: String = "categories"
    var category: MutableLiveData<Category> = MutableLiveData()
    var categoriesList: MutableLiveData<List<Category>> = MutableLiveData()

    private val firestore: FirebaseFirestore = Firebase.firestore

    init {
        loadAll()
    }

    fun add(category: Category, callback: OnOperationCallback? = null) {
        firestore.collection(NAME_COLLECTION).add(category).addOnSuccessListener {
            callback?.let {
                it.onAccountAdded()
            }
        }.addOnFailureListener { exception ->
            callback?.let {
                it.onAccountAddError(exception)
            }
        }
    }

    fun getAll(): LiveData<List<Category>> {
        return categoriesList;
    }

    fun loadAll() {
        firestore.collection(NAME_COLLECTION).get().addOnSuccessListener { result ->
            val categoryLis: ArrayList<Category> = arrayListOf()
            if (!result.isEmpty) {
                for (document in result.documents) {
                    val myCategory: Category? = document.toObject(Category::class.java)
                    myCategory?.let {
                        it.id = document.id
                        categoryLis.add(it)
                    }
                }
                categoriesList.value = categoryLis
            }
        }
    }


}