package com.grupy.newsfeed.ui.base

interface IBaseView {
    fun onToast(message: String)

    fun onSnackBar(message: String)

    fun showServerError()

    fun showNetworkError()

    fun isLoading(isLoading: Boolean)
}