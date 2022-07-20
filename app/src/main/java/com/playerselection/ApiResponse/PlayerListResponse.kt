package com.playerselection.ApiResponse

data class PlayerListResponse(
    val allrounder: ArrayList<player_data>,
    val batsman: ArrayList<player_data>,
    val bowler: ArrayList<player_data>,
    val wicketkeeper: ArrayList<player_data>
)

data class player_data(
    val created_at_timestamp: String,
    val id: String,
    val image: String,
    val is_selected: Int,
    val name: String,
    val point: String,
    val team_name: String,
    val type: String,
    var count: Int=0,
)


