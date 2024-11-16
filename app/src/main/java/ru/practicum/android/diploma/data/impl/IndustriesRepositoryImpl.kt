package ru.practicum.android.diploma.data.impl

import ru.practicum.android.diploma.data.db.converters.IndustryRoomToIndustryMapper
import ru.practicum.android.diploma.data.db.dao.IndustriesDao
import ru.practicum.android.diploma.domain.models.Industry
import ru.practicum.android.diploma.domain.repository.IndustriesRepository

class IndustriesRepositoryImpl(private val dao: IndustriesDao) : IndustriesRepository {
    override suspend fun getAllIndustries(search: String?): List<Industry> {
        search?.let {
            return IndustryRoomToIndustryMapper.map(dao.getIndustriesByName(search))
        } ?: run {
            return IndustryRoomToIndustryMapper.map(dao.getIndustries())
        }
    }
}
