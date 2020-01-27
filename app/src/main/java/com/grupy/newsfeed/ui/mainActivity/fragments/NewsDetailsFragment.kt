package com.grupy.newsfeed.ui.mainActivity.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.grupy.newsfeed.R
import com.grupy.newsfeed.databinding.FragmentNewsDetailsBinding
import com.grupy.newsfeed.model.base.ItemModel
import com.grupy.newsfeed.shared.utils.AppConstants
import com.grupy.newsfeed.shared.utils.ViewUtils
import com.grupy.newsfeed.shared.utils.convertDateToString
import com.grupy.newsfeed.shared.utils.intentOpenWebsite
import com.grupy.newsfeed.ui.base.BaseFragment
import com.grupy.newsfeed.ui.mainActivity.MainActivity

class NewsDetailsFragment : BaseFragment() {

    private lateinit var mBinding: FragmentNewsDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_details, container, false)
        return mBinding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initToolbar()
    }

    private fun initView(){
        val arg = arguments?.getSerializable(AppConstants.ITEM_RESULT_SERACH) as ItemModel?

        arg?.let {
            model -> mBinding.viewModel = model
            val date = convertDateToString(model.webPublicationDate)
            date?.let {  mBinding.webPublicationDate.text = date }
            mBinding.webLink.setOnClickListener{ intentOpenWebsite(requireActivity(), model.webUrl) }
        }
    }

    private fun initToolbar() {
        val activity = requireActivity() as MainActivity
        activity.setSupportActionBar((mBinding.appBarLayout.toolbar))
        mBinding.appBarLayout.toolbar.setNavigationOnClickListener { activity.onBackPressed() }
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ViewUtils.setColorFilter(
            mBinding.appBarLayout.toolbar.navigationIcon!!,
            ContextCompat.getColor(requireContext(), android.R.color.white)
        )
    }
}