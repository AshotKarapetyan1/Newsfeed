package com.grupy.newsfeed.viewModels.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grupy.newsfeed.repository.networking.NetworkError
import com.hadilq.liveevent.LiveEvent

open class BaseViewModel : ViewModel() {

  private val mServerErrorLiveData  by lazy { LiveEvent<Boolean>() }
  private val mNetworkErrorLiveData by lazy { LiveEvent<Boolean>() }
  private val mToastMessageLiveData by lazy { LiveEvent<String>() }
  private val mSnackBarMessageLiveData by lazy { LiveEvent<String>() }
  private val mIsLoadingLiveDada by lazy { LiveEvent<Boolean>() }

  fun errorView(error: Throwable?) {
    error?.let {
      if (NetworkError.isNetworkError(it)){ mNetworkErrorLiveData.value = true ; return}

      if (NetworkError.isServerError(it)){ it.message?.let { mServerErrorLiveData.value = true; return } }

      it.message?.let { errorToast(it) }
    }
  }

  protected fun errorToast(errorMessage: String) { mToastMessageLiveData.value = errorMessage}

  protected fun errorSnackBar(errorMessage:String) { mSnackBarMessageLiveData.value  = errorMessage }

  protected fun isLoading(isLoading: Boolean) { mIsLoadingLiveDada.value = isLoading }

  fun getIsLoadingLiveData(): LiveEvent<Boolean> { return mIsLoadingLiveDada }

  fun getServerErrorLiveData(): LiveEvent<Boolean> { return mServerErrorLiveData }

  fun getNetworkErrorLiveData(): LiveEvent<Boolean> { return mNetworkErrorLiveData }

  fun getToastMessageLiveData(): MutableLiveData<String> { return mToastMessageLiveData }

  fun getSnackBarMessageLiveData(): MutableLiveData<String> { return mSnackBarMessageLiveData }
}