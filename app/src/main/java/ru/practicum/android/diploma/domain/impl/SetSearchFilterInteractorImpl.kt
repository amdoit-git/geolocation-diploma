package ru.practicum.android.diploma.domain.impl

import ru.practicum.android.diploma.domain.models.Area
import ru.practicum.android.diploma.domain.models.Geolocation
import ru.practicum.android.diploma.domain.models.Industry
import ru.practicum.android.diploma.domain.repository.SetSearchFilterInteractor
import ru.practicum.android.diploma.domain.repository.SetSearchFilterRepository

class SetSearchFilterInteractorImpl(private val repository: SetSearchFilterRepository) : SetSearchFilterInteractor {
    override suspend fun saveCountry(country: Area?) {
        repository.saveCountry(country)
    }

    override suspend fun saveRegion(region: Area?) {
        repository.saveRegion(region)
    }

    override suspend fun saveCity(city: Area?) {
        repository.saveCity(city)
    }

    override suspend fun saveIndustry(industry: Industry?) {
        repository.saveIndustry(industry)
    }

    override suspend fun saveSalary(salary: Int?) {
        repository.saveSalary(salary)
    }

    override suspend fun saveOnlyWithSalary(onlyWithSalary: Boolean) {
        repository.saveOnlyWithSalary(onlyWithSalary)
    }

    override suspend fun saveGeolocation(geolocation: Geolocation?) {
        repository.saveGeolocation(geolocation)
    }

    override suspend fun resetFilter() {
        repository.resetFilter()
    }
}