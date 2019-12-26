package com.kweku.stations

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.api.get
import coil.api.load
import com.kweku.andradio.domain.models.Station
import com.kweku.stations.databinding.ViewAllStationsBinding
import com.kweku.stations.model.StationDiffUtil

class AllStationsRecyclerAdapter(val context:Context,
                                 private val clickListener: (Station) -> Unit):
    ListAdapter<Station,
            AllStationsRecyclerAdapter.AllStationsRecyclerViewHolder>(StationDiffUtil()) {

private lateinit var binding: ViewAllStationsBinding


    inner class AllStationsRecyclerViewHolder(val view: View): RecyclerView.ViewHolder(view){
          lateinit var stationNameTextView: TextView
          lateinit var stationCountryTextView: TextView
          lateinit var stationIconImageView: ImageView
          lateinit var stationLanguageTextView: TextView
          lateinit var stationBitrateTextView: TextView
          lateinit var stationTagsTextView: TextView
          lateinit var stationView: ConstraintLayout

        fun bind(station: Station){
            stationNameTextView.text = station.stationName
            stationCountryTextView.text = station.country
            stationIconImageView.load(station.favicon)

            stationLanguageTextView.text = station.stationLanguage
            stationBitrateTextView.text = station.bitrate.toString()
            stationTagsTextView.text = station.stationTags
            stationView.setOnClickListener {
                clickListener(station)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllStationsRecyclerViewHolder {
        binding = ViewAllStationsBinding.inflate(LayoutInflater.from(context), parent, false)
        val allStationsRecyclerViewHolder = AllStationsRecyclerViewHolder(binding.root)

        allStationsRecyclerViewHolder.stationNameTextView =  binding.stationNameTextView
        allStationsRecyclerViewHolder.stationCountryTextView = binding.stationCountry
        allStationsRecyclerViewHolder.stationIconImageView = binding.StationIcon
        allStationsRecyclerViewHolder.stationLanguageTextView = binding.stationLanguage
        allStationsRecyclerViewHolder.stationBitrateTextView  = binding.stationBitrate
        allStationsRecyclerViewHolder.stationTagsTextView = binding.stationTags
        allStationsRecyclerViewHolder.stationView = binding.StationViewConstrainLayout

        return allStationsRecyclerViewHolder
    }

    override fun onBindViewHolder(holder: AllStationsRecyclerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}