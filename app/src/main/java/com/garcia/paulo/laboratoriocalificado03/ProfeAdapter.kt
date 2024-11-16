package com.garcia.paulo.laboratoriocalificado03


import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.garcia.paulo.laboratoriocalificado03.databinding.ItemProfeBinding


class ProfeAdapter(var list: List<ProfeResponse>) : RecyclerView.Adapter<ProfeAdapter.ProfeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfeViewHolder {
        val binding = ItemProfeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfeViewHolder, position: Int) {
        val profe = list[position]
        holder.bind(profe)
    }

    override fun getItemCount(): Int = list.size

    class ProfeViewHolder(private val binding: ItemProfeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(profe: ProfeResponse) {
            binding.name.text = profe.name
            binding.email.text = profe.email
            binding.phone.text = profe.phone
            Log.d("ProfeAdapter", "Photo URL: ${profe.imageUrl}")
            Glide.with(itemView.context)
                .load(profe.imageUrl)
                .placeholder(R.drawable.default_avatar)
                .into(binding.photo)

            binding.btnEmail.setOnClickListener {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${profe.email}")
                }
                itemView.context.startActivity(emailIntent)
            }

            binding.btnPhone.setOnClickListener {
                val phoneIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${profe.phone}")
                }
                itemView.context.startActivity(phoneIntent)
            }
        }
    }
}
