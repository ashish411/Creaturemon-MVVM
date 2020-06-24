package com.raywenderlich.android.creaturemon.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raywenderlich.android.creaturemon.model.AttributeStore
import com.raywenderlich.android.creaturemon.model.AttributeStore.ENDURANCE
import com.raywenderlich.android.creaturemon.model.AttributeStore.STRENGTH
import com.raywenderlich.android.creaturemon.model.AttributeType
import com.raywenderlich.android.creaturemon.model.Creature
import com.raywenderlich.android.creaturemon.model.CreatureAttributes
import com.raywenderlich.android.creaturemon.model.CreatureGenerator
import com.raywenderlich.android.creaturemon.model.CreatureRepository
import com.raywenderlich.android.creaturemon.model.room.RoomRepository

class CreatureViewmodel(private val generator: CreatureGenerator = CreatureGenerator(),
                        private val repository: CreatureRepository = RoomRepository()) : ViewModel() {

    private val creatureLiveData = MutableLiveData<Creature>()

    fun getCreatureLiveData(): LiveData<Creature> = creatureLiveData

    var name = ""
    var drawable = 0
    var intelligence = 0
    var strength = 0
    var endurance = 0

    lateinit var creature: Creature

    fun updateCreature() {
        val attributes = CreatureAttributes(intelligence, strength, endurance)
        creature = generator.generateCreature(attributes, name, drawable)

        creatureLiveData.postValue(creature)
    }

    fun attributeSelected(attributeType: AttributeType, position: Int) {
        when (attributeType) {
            AttributeType.INTELLIGENCE -> intelligence = AttributeStore.INTELLIGENCE[position].value
            AttributeType.STRENGTH -> strength = STRENGTH[position].value
            AttributeType.ENDURANCE -> endurance = ENDURANCE[position].value
        }
        updateCreature()
    }

    fun drawableSelected(drawable: Int) {
        this.drawable = drawable
        updateCreature()
    }

    private fun canSaveCreature(): Boolean {
        return intelligence != 0 && strength != 0 && endurance != 0 && name.isNotEmpty() && drawable != 0
    }

    fun saveCreature(): Boolean {
        return if (canSaveCreature()) {
            creatureLiveData.value?.let {
                repository.saveCreature(it)
            }
            true
        } else {
            false
        }
    }

}