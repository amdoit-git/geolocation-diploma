package ru.practicum.android.diploma.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.data.db.converters.AreaRoomToAreaMapper
import ru.practicum.android.diploma.data.db.converters.SearchFilterToSearchFilterRoomMapper
import ru.practicum.android.diploma.data.db.dao.SearchFilterDao
import ru.practicum.android.diploma.domain.models.Area
import ru.practicum.android.diploma.domain.models.Geolocation
import ru.practicum.android.diploma.domain.models.Industry
import ru.practicum.android.diploma.domain.models.SearchFilter
import ru.practicum.android.diploma.domain.repository.SearchFilterRepository

class SearchFilterRepositoryImpl(private val dao: SearchFilterDao) : SearchFilterRepository {

    private var activeFilter: SearchFilter? = null
    private var hasActiveFilter: Boolean = false
    private val mapper = SearchFilterToSearchFilterRoomMapper

    override suspend fun getFilter(): Flow<SearchFilter?> {
        return dao.getFilterFlow().map { mapper.map(it) }
    }

    override suspend fun isFilterExists(): Flow<Boolean> {
        return dao.getFilterFlow().map { it != null }
    }

    override suspend fun getFilterForNetworkClient(page: Int): SearchFilter? {

        if (hasActiveFilter && page > 0) {
            return activeFilter
        }

        activeFilter = mapper.map(
            filter = dao.getFilter()
        )

        hasActiveFilter = true

        return activeFilter
    }

    override suspend fun saveCountry(country: Area?) {
        val filter = getFilterOrDefault()
        filter.country = country
        dao.replaceFilter(mapper.map(filter))
    }

    override suspend fun saveRegion(region: Area?) {
        val filter = getFilterOrDefault()
        filter.region = region

        if (region?.parentId != null) {
            dao.getParentArea(region.parentId.toInt())?.let { roomCountry ->
                filter.country = AreaRoomToAreaMapper.map(roomCountry)
            } ?: run {
                filter.country = null
            }
        }

        dao.replaceFilter(mapper.map(filter))
    }

    override suspend fun saveCity(city: Area?) {
        val filter = getFilterOrDefault()

        filter.city = city

        if (city?.parentId != null) {
            dao.getParentArea(city.parentId.toInt())?.let { roomRegion ->
                filter.region = AreaRoomToAreaMapper.map(roomRegion)
            } ?: run {
                filter.region = null
            }
        }

        val regionParentId = filter.region?.parentId

        if (regionParentId != null) {
            dao.getParentArea(regionParentId.toInt())?.let { roomCountry ->
                filter.country = AreaRoomToAreaMapper.map(roomCountry)
            } ?: run {
                filter.country = null
            }
        }

        dao.replaceFilter(mapper.map(filter))
    }

    override suspend fun saveIndustry(industry: Industry?) {
        val filter = getFilterOrDefault()

        filter.industry = industry

        dao.replaceFilter(mapper.map(filter))
    }

    override suspend fun saveSalary(salary: Int?) {
        val filter = getFilterOrDefault()
        filter.salary = salary
        dao.replaceFilter(mapper.map(filter))
    }

    override suspend fun saveOnlyWithSalary(onlyWithSalary: Boolean) {
        val filter = getFilterOrDefault()
        filter.onlyWithSalary = onlyWithSalary
        dao.replaceFilter(mapper.map(filter))
    }

    override suspend fun saveGeolocation(geolocation: Geolocation?) {
        val filter = getFilterOrDefault()
        filter.geolocation = geolocation
        dao.replaceFilter(mapper.map(filter))
    }

    override suspend fun resetFilter() {
        dao.deleteFilter()
    }

    private suspend fun getFilterOrDefault(): SearchFilter {
        dao.getFilter()?.let { filter ->
            mapper.map(filter)?.let {
                return it
            }
        }

        return SearchFilter()
    }
}
