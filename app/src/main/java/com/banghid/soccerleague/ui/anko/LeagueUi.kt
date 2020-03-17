package com.banghid.soccerleague.ui.anko

import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.view.ViewManager
import android.widget.ImageView
import android.widget.LinearLayout
import com.banghid.soccerleague.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.custom.ankoView

class LeagueUi : AnkoComponent<ViewGroup> {
    companion object {
        val tvCountryId = 1
        val tvNameId = 2
        val cvLeagueId = 3
        val imgLeagueId = 4
    }


    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {

        frameLayout {
            cardView {
                lparams(matchParent, wrapContent)
                id =
                    cvLeagueId
                background = resources.getDrawable(R.drawable.shape_rounded_card)
                var layoutParams =
                    layoutParams as MarginLayoutParams
                layoutParams.setMargins(20, 20, 20, 20)
                
                requestLayout()

                verticalLayout {
                    lparams(matchParent, matchParent)

                    imageView {
                        id =
                            imgLeagueId
                        layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }

                    verticalLayout {
                        lparams(matchParent, wrapContent)
                        padding = 10
                        layoutParams.setMargins(10, 10, 10, 10)
                        textView {
                            layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                            id =
                                tvNameId
                            textSize = 15F
                            padding = 3
                        }

                        textView {
                            layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                            id =
                                tvCountryId
                            textSize = 12F
                            padding = 3
                        }
                    }
                }
            }
        }


    }
}