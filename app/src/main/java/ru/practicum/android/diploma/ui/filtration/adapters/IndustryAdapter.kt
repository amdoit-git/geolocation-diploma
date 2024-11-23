package ru.practicum.android.diploma.ui.filtration.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.practicum.android.diploma.databinding.FilterIndustryElementBinding
import ru.practicum.android.diploma.domain.models.Industry
import ru.practicum.android.diploma.ui.filtration.adapters.viewholders.IndustryViewHolder

class IndustryAdapter(
    private val listener: IndustryClickListener,
) : ListAdapter<Industry, IndustryViewHolder>(IndustryComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndustryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return IndustryViewHolder(
            FilterIndustryElementBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IndustryViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
        holder.binding.rbIndustryButton.setOnClickListener {
            manageListRadioButtons(item.id)
            listener.onIndustryClick(item)
        }
    }

    private fun manageListRadioButtons(clickedIndustryId: String) {
        val updateList: MutableList<Industry> = currentList.toMutableList()
        updateList.forEachIndexed { index, industry ->
            updateList[index] = industry.copy(
                isSelected = industry.id == clickedIndustryId
            )
        }
        submitList(updateList)
    }

    private class IndustryComparator : DiffUtil.ItemCallback<Industry>() {
        override fun areItemsTheSame(oldItem: Industry, newItem: Industry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Industry, newItem: Industry): Boolean {
            return oldItem.id == newItem.id && oldItem.isSelected == newItem.isSelected
        }
    }

    fun interface IndustryClickListener {
        fun onIndustryClick(item: Industry)
    }
}