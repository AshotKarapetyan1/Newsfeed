package com.grupy.newsfeed.ui.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.grupy.newsfeed.viewModels.base.BaseViewModel

abstract class BaseFragment : Fragment(), IBaseView {

    inline fun <reified T : BaseViewModel> createViewModel(
        modelClass: Class<T>,
        view: IBaseView
    ): T {
        val viewModel = ViewModelProvider(this).get(modelClass)
        viewModel.getServerErrorLiveData().observe(this, Observer { view.showServerError() })
        viewModel.getNetworkErrorLiveData().observe(this, Observer { view.showNetworkError() })
        viewModel.getToastMessageLiveData().observe(this, Observer { text -> view.onToast(text) })
        viewModel.getSnackBarMessageLiveData()
            .observe(this, Observer { text -> view.onSnackBar(text) })
        viewModel.getIsLoadingLiveData()
            .observe(this, Observer { isLoading -> view.isLoading(isLoading) })
        return viewModel
    }

    override fun showServerError() {
        if (activity != null) (activity as BaseActivity).showServerError()
    }

    override fun showNetworkError() {
        if (activity != null) (activity as BaseActivity).showNetworkError()
    }

    override fun onToast(message: String) {
        if (activity != null) (activity as BaseActivity).onToast(message)
    }

    override fun onSnackBar(message: String) {
        if (activity != null) (activity as BaseActivity).onSnackBar(message)
    }

    override fun isLoading(isLoading: Boolean) {
        if (activity != null) (activity as BaseActivity).isLoading(isLoading)
    }
}