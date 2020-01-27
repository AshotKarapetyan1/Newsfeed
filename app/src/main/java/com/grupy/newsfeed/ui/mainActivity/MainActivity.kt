package com.grupy.newsfeed.ui.mainActivity

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.grupy.newsfeed.R
import com.grupy.newsfeed.ui.base.BaseActivity
import com.grupy.newsfeed.viewModels.mainActivity.MainActivityViewModel


class MainActivity : BaseActivity() {

    private val mViewModel: MainActivityViewModel by lazy {
        createViewModel(MainActivityViewModel::class.java, this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        mViewModel.search()

    }

}
