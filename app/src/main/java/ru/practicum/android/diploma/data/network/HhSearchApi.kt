package ru.practicum.android.diploma.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.data.dto.AreaDto
import ru.practicum.android.diploma.data.dto.CountryDto
import ru.practicum.android.diploma.data.dto.IndustryDto
import ru.practicum.android.diploma.data.dto.VacancyDetailedDto
import ru.practicum.android.diploma.data.dto.VacancyDto

interface HhSearchApi {

    @GET("vacancies")
    suspend fun searchVacancies(@QueryMap options: Map<String, String>): List<VacancyDto>

    @GET("vacancies/{id}")
    suspend fun getVacancyDetails(@Path("id") vacancyId: String): VacancyDetailedDto

    @GET("areas/countries")
    suspend fun getCountries(): List<CountryDto>

    @GET("areas/{id}")
    suspend fun getAreasByCountry(@Path("id") countryId: String): AreaDto

    @GET("industries")
    suspend fun getIndustries(): List<IndustryDto>

}
