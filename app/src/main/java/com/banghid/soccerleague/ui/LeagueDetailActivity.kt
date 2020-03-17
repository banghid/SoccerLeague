package com.banghid.soccerleague.ui

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.banghid.soccerleague.R
import com.banghid.soccerleague.models.LeagueModel
import com.banghid.soccerleague.ui.anko.makeStatusBarTransparent
import com.google.android.material.appbar.AppBarLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.support.v4.nestedScrollView
import java.io.IOException
import java.io.InputStream

class LeagueDetailActivity : AppCompatActivity() {

    lateinit var imgLeague: ImageView
    lateinit var tvNameLeague: TextView
    lateinit var tvCountryLeague: TextView
    lateinit var tvDetailLeague: TextView
    private lateinit var leagueData: LeagueModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LeagueDetailUi().setContentView(this)

        makeStatusBarTransparent()
        imgLeague = findViewById(LeagueDetailUi.detailImg)
        tvNameLeague = findViewById(LeagueDetailUi.detailName)
        tvCountryLeague = findViewById(LeagueDetailUi.detailCountry)
        tvDetailLeague = findViewById(LeagueDetailUi.detailDesc)

        leagueData = intent?.extras?.getParcelable("LEAGUE_DATA")!!

        try {
            val ims: InputStream = assets.open(leagueData.imagePath)
            val d = Drawable.createFromStream(ims, null)
            imgLeague.setImageDrawable(d)
            ims.close()
        } catch (ex: IOException) {
            Log.d("LeagueDetailActivity", "image: " + ex.message)
        }

        tvNameLeague.text = leagueData.name
        tvDetailLeague.text = leagueData.detail
        tvCountryLeague.text = leagueData.country

        toast("Open " + leagueData.name)

    }
}

class LeagueDetailUi : AnkoComponent<LeagueDetailActivity> {

    companion object{
        const val detailAppbar = 7
        const val  detailImg = 8
        const val  detailName = 9
        const val detailCountry = 10
        const val detailDesc = 11
    }

    override fun createView(ui: AnkoContext<LeagueDetailActivity>) = with(ui) {
        coordinatorLayout {
            backgroundColor = resources.getColor(R.color.colorWhite)
            //tools:context = .ui.LeagueDetailActivity //not support attribute
            appBarLayout {
                //style = @style/ThemeOverlay.AppCompat.Dark.ActionBar //not support attribute
                backgroundResource = R.drawable.shape_appbar_detail
                id = detailAppbar
                collapsingToolbarLayout {
                    //app:contentScrim = @color/colorPrimary //not support attribute
                    imageView {
                        id = detailImg
                        scaleType = ImageView.ScaleType.CENTER_CROP
                        backgroundResource = R.drawable.shape_frame_image_detail
                        contentDescription = resources.getString(R.string.appbar_image_cd)
                    }.lparams(width = matchParent, height = dip(250))
                }.lparams(width = matchParent, height = dip(250)) {
                    scrollFlags =
                        AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                }
                relativeLayout {
                    textView {
                        id = detailName
                        textSize = 20f //sp
                        textColor = resources.getColor(R.color.colorWhite)
                        setTypeface(typeface, Typeface.BOLD)
                        text = resources.getString(R.string.league_name_dummy)
                    }.lparams {
                        bottomMargin = dip(2)
                    }
                    textView {
                        id = detailCountry
                        textSize = 18f //sp
                        text = resources.getString(R.string.league_country_dummy)
                        textColor = resources.getColor(R.color.colorWhite)
                    }.lparams {
                        below(detailName)
                        topMargin = dip(4)
                    }
                }.lparams(width = matchParent) {
                    marginStart = dip(20)
                    topMargin = dip(12)
                    bottomMargin = dip(8)
                    marginEnd = dip(20)
                }
            }.lparams(width = matchParent)
            nestedScrollView {
                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    textView {
                        textColor = resources.getColor(R.color.colorText)
                        textSize = 18f //sp
                        text = resources.getString(R.string.league_detail_string)
                    }.lparams {
                        bottomMargin = dip(10)
                    }
                    textView {
                        textSize = 15f //sp
                        textColor = resources.getColor(R.color.colorText)
                        text = resources.getString(R.string.detail_league_dummy)
                        id = detailDesc
                    }.lparams(width = matchParent)
                }.lparams(width = matchParent, height = matchParent) {
                    margin = dip(25)
                }
            }.lparams(width = matchParent) {
                behavior =
                    Class.forName(resources.getString(R.string.appbar_scrolling_view_behavior))
                        .newInstance() as CoordinatorLayout.Behavior<*>?
            }
        }
    }

}
