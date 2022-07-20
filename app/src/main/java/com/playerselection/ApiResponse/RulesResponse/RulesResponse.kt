package com.playerselection.ApiResponse.RulesResponse

data class RulesResponse(
    val allrounder: Max_min,
    val batsman: Max_min,
    val bowler: Max_min,
    val max_team_selection: Int,
    val team1: String,
    val team2: String,
    val total_credit: Int,
    val wicketkeeper: Max_min
)

data class Max_min(
    val max: Int,
    val min: Int
)