package ru.practicum.android.diploma.data.db.converters

import ru.practicum.android.diploma.data.db.models.AreaRoomTemp
import ru.practicum.android.diploma.data.search.dto.model.AreaDto
import ru.practicum.android.diploma.data.search.dto.model.CountryDto
import ru.practicum.android.diploma.domain.models.AreaType

object AreaDtoToTempAreaItemMapper {
    fun map(country: CountryDto): AreaRoomTemp {
        return AreaRoomTemp(
            id = country.id.toInt(),
            name = country.name,
            type = AreaType.COUNTRY.type,
            parentId = 0
        )
    }

    fun map(area: AreaDto, type: AreaType): AreaRoomTemp {
        return AreaRoomTemp(
            id = area.id.toInt(),
            name = area.name,
            parentId = area.parentId?.toInt() ?: 0,
            type = type.type
        )
    }
}
