package com.fabiansuarez.micartera.util

interface OnOperationCallback {
    fun onAccountAdded()
    fun onAccountAddError(error: Exception)
}