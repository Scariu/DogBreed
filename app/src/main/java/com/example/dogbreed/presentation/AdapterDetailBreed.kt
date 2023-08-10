package com.example.dogbreed.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dogbreed.R
import com.example.dogbreed.data.local.detail.DogBreedDetailEntity
import com.example.dogbreed.databinding.ItemDetailBinding

class AdapterDetailBreed : RecyclerView.Adapter<AdapterDetailBreed.ViewHolder>() {

    private lateinit var itemBinding: ItemDetailBinding
    private val detailListDog = mutableListOf<DogBreedDetailEntity>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterDetailBreed.ViewHolder {
        itemBinding = ItemDetailBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AdapterDetailBreed.ViewHolder, position: Int) {
        val dog = detailListDog[position]
        holder.bind(dog)
    }

    override fun getItemCount(): Int {
        return detailListDog.size
    }

    fun setDataDetail(detail: List<DogBreedDetailEntity>) {
        this.detailListDog.clear()
        this.detailListDog.addAll(detail)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dogDetail: DogBreedDetailEntity) {
            binding.imageDogsDetail.load(dogDetail.url){
                placeholder(R.drawable.dog_loading)
            }
        }
    }
}