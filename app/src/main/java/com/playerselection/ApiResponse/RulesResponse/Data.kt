package com.playerselection.ApiResponse.RulesResponse

data class Data(
    val allrounder: Allrounder,
    val batsman: Batsman,
    val bowler: Bowler,
    val max_team_selection: Int,
    val team1: String,
    val team2: String,
    val total_credit: Int,
    val wicketkeeper: Wicketkeeper
)