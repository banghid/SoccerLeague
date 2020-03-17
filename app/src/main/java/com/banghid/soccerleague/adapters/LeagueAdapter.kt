package com.banghid.soccerleague.adapters

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.banghid.soccerleague.models.LeagueModel
import com.banghid.soccerleague.ui.LeagueDetailActivity
import com.banghid.soccerleague.ui.anko.LeagueUi
import org.jetbrains.anko.AnkoContext
import java.io.IOException
import java.io.InputStream


class LeagueAdapter(var list: List<LeagueModel> = arrayListOf(), val context: Context) :
    RecyclerView.Adapter<LeagueHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueHolder {
        return LeagueHolder(
            LeagueUi()
                .createView(AnkoContext.create(parent.context, parent))
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: LeagueHolder, position: Int) {
        holder.bindItem(list[position], context)
    }


}

class LeagueHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvCountry: TextView = view.findViewById(LeagueUi.tvCountryId)
    private val tvName: TextView = view.findViewById(LeagueUi.tvNameId)
    private val imgLeague: ImageView = view.findViewById(LeagueUi.imgLeagueId)
    private val mViewContent: View = view.findViewById(LeagueUi.cvLeagueId)

    fun bindItem(data: LeagueModel, context: Context) {
        val resId = context.resources.getIdentifier(data.imagePath, "drawable", context.packageName)
        imgLeague.setImageResource(resId)

        try {
            val ims: InputStream = context.assets.open(data.imagePath)
            val d = Drawable.createFromStream(ims, null)
            imgLeague.setImageDrawable(d)
            ims.close()
        } catch (ex: IOException) {
            Log.d("LeagueAdapter", "image: " + ex.message)
        }
        tvName.text = data.name
        tvCountry.text = data.country

        mViewContent.setOnClickListener {
            val detailIntent = Intent(context, LeagueDetailActivity::class.java)
            detailIntent.putExtra("LEAGUE_DATA", data)
            context.startActivity(detailIntent)
        }

    }
}