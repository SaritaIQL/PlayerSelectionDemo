package com.playerselection.ApiResponse

data class PlayerListResponse(
    val allrounder: ArrayList<Allrounder>,
    val batsman: ArrayList<Batsman>,
    val bowler: ArrayList<Bowler>,
    val wicketkeeper: ArrayList<Wicketkeeper>
)

data class Allrounder(
    val created_at_timestamp: String,
    val id: String,
    val image: String,
    val is_selected: Int,
    val name: String,
    val point: String,
    val team_name: String,
    val type: String
)

data class Batsman(
    val created_at_timestamp: String,
    val id: String,
    val image: String,
    val is_selected: Int,
    val name: String,
    val point: String,
    val team_name: String,
    val type: String
)

data class Bowler(
    val created_at_timestamp: String,
    val id: String,
    val image: String,
    val is_selected: Int,
    val name: String,
    val point: String,
    val team_name: String,
    val type: String
)

data class Wicketkeeper(
    val created_at_timestamp: String,
    val id: String,
    val image: String,
    val is_selected: Int,
    val name: String,
    val point: String,
    val team_name: String,
    val type: String
)