package com.oheyadam.mavericks.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.oheyadam.mavericks.databinding.VehicleItemBinding
import com.oheyadam.mavericks.list.VehiclesAdapter.VehicleViewHolder
import com.oheyadam.mavericks.list.model.VehicleItem

object VehicleItemDiffUtil : DiffUtil.ItemCallback<VehicleItem>() {
    override fun areItemsTheSame(oldItem: VehicleItem, newItem: VehicleItem) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: VehicleItem, newItem: VehicleItem) = oldItem == newItem
}

class VehiclesAdapter : ListAdapter<VehicleItem, VehicleViewHolder>(VehicleItemDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VehicleItemBinding.inflate(inflater, parent, false)
        return VehicleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class VehicleViewHolder(private val binding: VehicleItemBinding) : ViewHolder(binding.root) {

        fun bind(vehicleItem: VehicleItem) {
            binding.vehicleModel.text = vehicleItem.model
            binding.root.setOnClickListener {
                vehicleItem.onItemClick(vehicleItem.id)
            }
        }
    }
}
