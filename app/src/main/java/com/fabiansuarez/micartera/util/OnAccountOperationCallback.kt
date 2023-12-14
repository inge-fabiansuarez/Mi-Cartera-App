package com.fabiansuarez.micartera.util

interface OnAccountOperationCallback {
    fun onAccountAdded()
    fun onAccountAddError(error: Exception)
}