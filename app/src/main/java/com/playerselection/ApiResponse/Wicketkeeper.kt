package com.playerselection.ApiResponse

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