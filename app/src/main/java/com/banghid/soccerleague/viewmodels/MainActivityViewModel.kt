package com.banghid.soccerleague.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.banghid.soccerleague.models.LeagueModel
import com.banghid.soccerleague.repositories.LeagueRepository

class MainActivityViewModel : ViewModel() {

    private var mLeagues: MutableLiveData<List<LeagueModel>>? = null
    private var mRepoLeague: LeagueRepository? = null

    fun init(){
        if (mLeagues != null){
            return
        }

        mRepoLeague = LeagueRepository.getInstance()
        mLeagues = mRepoLeague?.getLeagues()
    }

    fun getLeagues():LiveData<List<LeagueModel>>?{
        return mLeagues
    }
}