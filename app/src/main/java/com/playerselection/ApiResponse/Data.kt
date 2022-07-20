package com.playerselection.ApiResponse

data class Data(
    val allrounder: List<Allrounder>,
    val batsman: List<Batsman>,
    val bowler: List<Bowler>,
    val wicketkeeper: List<Wicketkeeper>
)