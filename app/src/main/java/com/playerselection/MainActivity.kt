package com.playerselection

import android.util.Log
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.playerselection.ApiResponse.player_data
import com.playerselection.Appbase.BaseActivity
import com.playerselection.Appbase.ReusedMethod.Companion.displayMessage
import com.playerselection.adapter.PlayerAdapter
import com.playerselection.adapter.TeamPlayersAdapter
import com.playerselection.common.Config
import com.playerselection.databinding.ActivityMainBinding
import com.playerselection.util.ApiConstant
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val mViewModel: MainActivityModule by viewModel()
    var playerAdapter: PlayerAdapter? = null
    var teamPlayersAdapter: TeamPlayersAdapter? = null
    var allrounder_list = ArrayList<player_data>()
    var batsman_list = ArrayList<player_data>()
    var bowler_list = ArrayList<player_data>()
    var wicketkeeper_list = ArrayList<player_data>()
    var players_list = ArrayList<player_data>()
    override fun getResource(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        mBinding = getBinding()

        mBinding.txtTotalPlayerSelected.text = "0"
        setTeamsPlayerAdapter()
        mViewModel.getPlayerList(
            true,
            this@MainActivity,
        )
        mViewModel.getPlayerMAX_MIN_INFO(
            true,
            this@MainActivity,
        )
        mBinding.myViewModel = mViewModel
        mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText("A"))
        mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText("W"))
        mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText("BL"))
        mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText("BT"))

        mBinding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                when (tab.text.toString()) {
                    "A" -> {
                        setAdapter(allrounder_list)
                        mViewModel.getSelectionPlayerRange("A")
                    }
                    "W" -> {
                        setAdapter(wicketkeeper_list)
                        mViewModel.getSelectionPlayerRange("W")

                    }
                    "BL" -> {
                        setAdapter(bowler_list)
                        mViewModel.getSelectionPlayerRange("BL")

                    }
                    "BT" -> {
                        setAdapter(batsman_list)
                        mViewModel.getSelectionPlayerRange("BT")

                    }

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun setTeamsPlayerAdapter() {
        mBinding.recSelectTeamProcess.adapter = null
        teamPlayersAdapter = TeamPlayersAdapter(
            this,
            players_list,
            object : TeamPlayersAdapter.onItemClicklisteners {
                override fun onItemClick(position: Int?) {

                }

            })
        mBinding.recSelectTeamProcess.adapter = teamPlayersAdapter
    }

    override fun initObserver() {
        mViewModel.getPlayerList().observe(this) { response ->
            response.let { requestState ->
                showLoadingIndicator(requestState.progress)
                requestState.apiResponse?.let {
                    it.data?.let { data ->
                        if (it.status == 1) {
                            allrounder_list.addAll(data.allrounder)
                            batsman_list.addAll(data.batsman)
                            bowler_list.addAll(data.bowler)
                            wicketkeeper_list.addAll(data.wicketkeeper)
                            setAdapter(allrounder_list)
                        }
                    }

                    requestState.error?.let { errorObj ->
                        when (errorObj.errorState) {
                            Config.NETWORK_ERROR ->
                                displayMessage(
                                    this,
                                    getString(R.string.text_error_network)
                                )

                            Config.CUSTOM_ERROR ->
                                errorObj.customMessage
                                    ?.let {
                                        if (errorObj.code == ApiConstant.API_401) {
                                            displayMessage(this, it)
                                        } else {

                                            displayMessage(this, it)
                                        }
                                    }
                        }
                    }
                }

            }

        }
        mViewModel.getPlayerRules().observe(this) { response ->
            response.let { requestState ->
                showLoadingIndicator(requestState.progress)
                requestState.apiResponse?.let {
                    it.data?.let { data ->
                        if (it.status == 1) {
                            mViewModel.batsman.value = data.batsman
                            mViewModel.wicketkeeper.value = data.wicketkeeper
                            mViewModel.bowler.value = data.bowler
                            mViewModel.allRoounderSize.value = data.allrounder
                        } else {
                            Log.e("dataApi", "response not ")

                        }
                    }

                    requestState.error?.let { errorObj ->
                        when (errorObj.errorState) {
                            Config.NETWORK_ERROR ->
                                displayMessage(
                                    this,
                                    getString(R.string.text_error_network)
                                )

                            Config.CUSTOM_ERROR ->
                                errorObj.customMessage
                                    ?.let {
                                        if (errorObj.code == ApiConstant.API_401) {
                                            displayMessage(this, it)
                                        } else {

                                            displayMessage(this, it)
                                        }
                                    }
                        }
                    }
                }

            }

        }


    }

    private fun setAdapter(array: ArrayList<player_data>) {
        mBinding.rvPlayerList.adapter = null
        playerAdapter = PlayerAdapter(this, array, object : PlayerAdapter.onItemClicklisteners {
            override fun onItemClick(position: Int?) {

            }

            override fun onItemPlus(position: Int?) {
                if (players_list.size == 11) {
                    displayMessage(this@MainActivity, "Team is Full")
                } else {
                    when (mBinding.tabLayout.selectedTabPosition) {
                        0 -> {
                            players_list.add(allrounder_list[position!!])
//                        allrounder_list[position!!].count = allrounder_list[position].count!! + 1
                            allrounder_list.removeAt(position)
                        }
                        1 -> {
                            players_list.add(wicketkeeper_list[position!!])
//                        wicketkeeper_list[position!!].count =
//                            wicketkeeper_list[position].count!! + 1
                            wicketkeeper_list.removeAt(position)
                        }
                        2 -> {
                            players_list.add(bowler_list[position!!])
//                        bowler_list[position!!].count = bowler_list[position].count!! + 1
                            bowler_list.removeAt(position)
                        }
                        3 -> {
                            players_list.add(batsman_list[position!!])
//                        batsman_list[position!!].count = batsman_list[position].count!! + 1
                            batsman_list.removeAt(position)
                        }
                    }
                    mBinding.txtTotalPlayerSelected.text = players_list.size.toString()
                }
                playerAdapter?.notifyDataSetChanged()
                teamPlayersAdapter?.notifyDataSetChanged()
            }

            override fun ontItemMinus(position: Int) {
//                when (mBinding.tabLayout.selectedTabPosition) {
//                    0 -> {
//                        setAddRemoveCount(allrounder_list, position)
//                    }
//                    1 -> {
//                        setAddRemoveCount(wicketkeeper_list, position)
//                    }
//                    2 -> {
//                        setAddRemoveCount(bowler_list, position)
//                    }
//                    3 -> {
//                        setAddRemoveCount(batsman_list, position)
//                    }
//                }
            }

        })
        mBinding.rvPlayerList.adapter = playerAdapter
    }

    private fun setAddRemoveCount(array: ArrayList<player_data>, position: Int?) {
        if (array[position!!].count != 0) {
            array[position].count = array[position].count - 1
        } else {
            displayMessage(this, "Limit Reached")
        }
        playerAdapter?.notifyDataSetChanged()
    }

    override fun handleListener() {

    }
}