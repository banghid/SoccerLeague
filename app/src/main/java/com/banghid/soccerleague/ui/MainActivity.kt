package com.banghid.soccerleague.ui

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.banghid.soccerleague.R
import com.banghid.soccerleague.adapters.LeagueAdapter
import com.banghid.soccerleague.viewmodels.MainActivityViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.nestedScrollView


class MainActivity : AppCompatActivity() {

    private lateinit var mMainActivityViewModel: MainActivityViewModel
    private lateinit var mLeagueAdapter: LeagueAdapter
    lateinit var rvLeague: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainUi().setContentView(this)

        rvLeague = findViewById(MainUi.rvLeague)

        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        mMainActivityViewModel.init()

        mMainActivityViewModel.getLeagues()?.observe(this, Observer {
            mLeagueAdapter.notifyDataSetChanged()
        })

        initView()
    }

    private fun initView() {
        val data = mMainActivityViewModel.getLeagues()?.value
        if (data != null) {
            mLeagueAdapter = LeagueAdapter(data, this)
        }

        rvLeague.layoutManager = GridLayoutManager(application, 2)
        rvLeague.adapter = mLeagueAdapter
    }
}

class MainUi : AnkoComponent<MainActivity> {
    companion object{
        const val mainAppbar = 5
        const val rvLeague = 6
    }
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        coordinatorLayout {
            backgroundColor = resources.getColor(R.color.colorPrimary)
            //tools:context = .ui.MainActivity //not support attribute
            appBarLayout {
                id = mainAppbar
                collapsingToolbarLayout {
                    backgroundColor = resources.getColor(R.color.colorTransparent)
                    textView {
                        text = resources.getString(R.string.app_name)
                        textColor = resources.getColor(R.color.colorWhite)
                        textSize = 20f //sp
                        //app:layout_collapseMode = parallax //not support attribute
                        setTypeface(typeface, Typeface.BOLD)
                    }.lparams {
                        gravity = Gravity.CENTER
                        collapseMode = COLLAPSE_MODE_PARALLAX
                    }
                }.lparams(width = matchParent, height = dip(125)) {
                    scrollFlags =
                        AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                }
            }.lparams(width = matchParent)
            nestedScrollView {
                backgroundResource = R.drawable.shape_rounded_top
                recyclerView {
                    id = rvLeague
                }.lparams(width = matchParent)
            }.lparams(width = matchParent) {
                behavior =
                    Class.forName(resources.getString(R.string.appbar_scrolling_view_behavior))
                        .newInstance() as CoordinatorLayout.Behavior<*>?
            }
        }
    }

}
