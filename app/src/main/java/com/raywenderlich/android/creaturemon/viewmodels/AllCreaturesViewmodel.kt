package com.raywenderlich.android.creaturemon.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raywenderlich.android.creaturemon.model.Creature
import com.raywenderlich.android.creaturemon.model.CreatureRepository
import com.raywenderlich.android.creaturemon.model.room.RoomRepository

class AllCreaturesViewmodel(private val repository: CreatureRepository = RoomRepository()): ViewModel() {

    private val allCreatures = repository.getAllCreatures()

    fun getLiveDataCreatures() = allCreatures

    fun clearAllCreatures(){
        repository.clearAllCreatures()
    }

}