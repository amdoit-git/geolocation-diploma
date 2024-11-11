package ru.practicum.android.diploma.data.search.dto

data class IndustryDto(
    val id:String,
    val name:String,
    val industries:List<IndustryDto>? = null
)
