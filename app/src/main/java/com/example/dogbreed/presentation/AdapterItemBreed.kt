package com.example.dogbreed.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dogbreed.R
import com.example.dogbreed.data.local.list.DogBreedEntity
import com.example.dogbreed.databinding.ItemBreedBinding

class AdapterItemBreed: RecyclerView.Adapter<AdapterItemBreed.ViewHolder>() {
    private lateinit var itemBinding: ItemBreedBinding
    private val lisItemBreed = mutableListOf<DogBreedEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterItemBreed.ViewHolder {
        itemBinding = ItemBreedBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AdapterItemBreed.ViewHolder, position: Int) {
        val breed = lisItemBreed[position]
        holder.bind(breed)
    }

    override fun getItemCount(): Int {
        return lisItemBreed.size
    }

    fun setData(breeds: List<DogBreedEntity>){
        this.lisItemBreed.clear()
        this.lisItemBreed.addAll(breeds)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemBreedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(breedView: DogBreedEntity) {
            binding.tvBreed.text = breedView.breed
            binding.cvItemBreed.setOnClickListener {
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_firstFragmentBreedList_to_secondFragmentDetalle)
            }
        }
    }
}
