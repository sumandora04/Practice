package com.notepoint.practice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.notepoint.practice.network.Heroes
import kotlinx.android.synthetic.main.cell.view.*

class HeroesAdapter(val heroesList: List<Heroes>, val herosClick:HeroesClickListener) : RecyclerView.Adapter<HeroesAdapter.HeroesHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.cell, parent, false)
        return HeroesHolder(view)
    }

    override fun getItemCount(): Int {
        return heroesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: HeroesHolder, position: Int) {
        val item = heroesList[position]
        holder.hero_text.text = item.heroName
        holder.hero_bio.text = item.bioData

        holder.itemView.setOnClickListener {
            herosClick.onHeroesClick(item)
        }

    }

    class HeroesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hero_text = itemView.name_text
        val hero_bio = itemView.bio_text
    }

    class HeroesClickListener(private val clickListener:(heroes:Heroes)->Unit){
        fun onHeroesClick(selectedHero:Heroes) =clickListener(selectedHero)
    }
}