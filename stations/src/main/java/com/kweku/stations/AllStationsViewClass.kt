package com.kweku.stations

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import com.kweku.andradio.domain.models.Station
import com.kweku.stations.databinding.FragmentAllStationsBinding
import com.kweku.stations.AllStationsViewClassInterface.AllStationsViewClassListener

class AllStationsViewClass(private val layoutInflater: LayoutInflater, private val parent: ViewGroup?,
                           val context: Context,
                           private val allStationsViewClassListener: AllStationsViewClassListener):
    AllStationsViewClassInterface{

    private val fragmentAllStationsBinding: FragmentAllStationsBinding = FragmentAllStationsBinding.inflate(layoutInflater, parent, false)
    private val root: View = fragmentAllStationsBinding.root
    private val allStationsRecyclerview: RecyclerView= fragmentAllStationsBinding.allStationsRecyclerview
    private val allStationsRecyclerAdapter = AllStationsRecyclerAdapter(context, allStationsViewClassListener::playClickedStation)
    private val allStationsLayoutManager: LinearLayoutManager = LinearLayoutManager(context)

    override fun getRoot():View = this.root

    init{
        initRecyclerview()
    }

    private fun initRecyclerview(){

        allStationsRecyclerview.apply {
            layoutManager = allStationsLayoutManager
            adapter = allStationsRecyclerAdapter
            addItemDecoration(SpaceItemDecoration(8))
        }

    }

    override fun setRecyclerData(stations :List<Station>){
        allStationsRecyclerAdapter.submitList(stations)
    }



}