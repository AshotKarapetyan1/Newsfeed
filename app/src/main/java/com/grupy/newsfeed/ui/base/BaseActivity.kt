package com.grupy.newsfeed.ui.base

import android.app.Activity
import android.graphics.Color
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.google.android.material.snackbar.Snackbar
import com.grupy.newsfeed.R
import com.grupy.newsfeed.viewModels.base.BaseViewModel

abstract class BaseActivity : AppCompatActivity() , IBaseView {

    inline fun <reified T : BaseViewModel> createViewModel(modelClass: Class<T>, view: IBaseView): T {
        val viewModel=  ViewModelProvider(this).get(modelClass)
        viewModel.getServerErrorLiveData().observe(this, Observer { view.showServerError() })
        viewModel.getNetworkErrorLiveData().observe(this, Observer { view.showNetworkError() })
        viewModel.getToastMessageLiveData().observe(this, Observer { text -> view.onToast(text) })
        viewModel.getSnackBarMessageLiveData().observe(this, Observer { text -> view.onSnackBar(text) })
        viewModel.getIsLoadingLiveData().observe(this, Observer { isLoading -> view.isLoading(isLoading) })
        return viewModel
    }

    override fun showServerError() { onSnackBar(getString(R.string.default_error_message)) }

    override fun showNetworkError() { onSnackBar(getString(R.string.default_network_error_message)) }

    override fun onToast(message: String) {
        Toast.makeText(applicationContext, if (message.isEmpty()) getString(R.string.default_error_message) else message, Toast.LENGTH_SHORT).show()
    }

    override fun isLoading(isLoading: Boolean) {}

    override fun onSnackBar(message: String) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content),
            if (message.isEmpty()) getString(R.string.default_error_message) else message, Snackbar.LENGTH_LONG)
        snackbar.setActionTextColor(Color.BLUE)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.WHITE)
        val textView = snackbarView.findViewById(R.id.snackbar_text) as TextView
        textView.setTextColor(Color.BLACK)
        textView.textSize = 16f
        snackbar.show()
    }
}