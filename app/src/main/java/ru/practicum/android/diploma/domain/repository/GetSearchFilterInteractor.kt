package ru.practicum.android.diploma.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.SearchFilter

interface GetSearchFilterInteractor {
    suspend fun getFilter(): Flow<SearchFilter?>

    suspend fun isFilterExists(): Flow<Boolean>

    suspend fun getFilterForNetworkClient(page: Int): SearchFilter?

    suspend fun getTempFilter(): Flow<SearchFilter?>
}
