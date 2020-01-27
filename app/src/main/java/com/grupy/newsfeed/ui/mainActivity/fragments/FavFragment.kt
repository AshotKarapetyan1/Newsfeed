package com.grupy.newsfeed.ui.mainActivity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupy.newsfeed.R
import com.grupy.newsfeed.databinding.FragmentFavBinding
import com.grupy.newsfeed.shared.utils.AppConstants
import com.grupy.newsfeed.shared.utils.ViewUtils
import com.grupy.newsfeed.ui.adapters.NewsListAdapter
import com.grupy.newsfeed.ui.base.BaseFragment
import com.grupy.newsfeed.ui.mainActivity.MainActivity
import com.grupy.newsfeed.viewModels.mainActivity.MainActivityViewModel

class FavFragment : BaseFragment() {

    private lateinit var mBinding: FragmentFavBinding
    private lateinit var mAdapter: NewsListAdapter

    private val mActivityViewModel: MainActivityViewModel by lazy {
        (requireActivity() as MainActivity).createViewModel(MainActivityViewModel::class.java, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_fav, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModels()
    }

    private fun initViewModels() {

        mActivityViewModel.favNewListLiveData.observe(viewLifecycleOwner, Observer {
            mAdapter.setData(it)
            mBinding.isEmpty = it.isNullOrEmpty()
        })
    }

    private fun initRecyclerView() {
        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            mAdapter = NewsListAdapter(arrayListOf(),{ item ->
                val bundle = bundleOf(AppConstants.ITEM_RESULT_SERACH to item)
                view?.findNavController()?.navigate(R.id.action_navigation_dashboard_to_newsDetailsFragment, bundle)
            }, { item -> mActivityViewModel.update(item)})
            adapter = mAdapter
        }

        mBinding.recyclerView.setPadding(0, resources.getDimension(R.dimen.activity_vertical_margin).toInt(),
            0, ViewUtils.getBottomNavigationBarHeight(requireContext())+ resources.getDimension(R.dimen.activity_vertical_margin).toInt())
    }
}