package com.raywenderlich.android.creaturemon.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raywenderlich.android.creaturemon.model.AttributeStore
import com.raywenderlich.android.creaturemon.model.AttributeStore.ENDURANCE
import com.raywenderlich.android.creaturemon.model.AttributeStore.INTELLIGENCE
import com.raywenderlich.android.creaturemon.model.AttributeStore.STRENGTH
import com.raywenderlich.android.creaturemon.model.AttributeType
import com.raywenderlich.android.creaturemon.model.AttributeType.ENDURANCE
import com.raywenderlich.android.creaturemon.model.AttributeType.INTELLIGENCE
import com.raywenderlich.android.creaturemon.model.AttributeType.STRENGTH
import com.raywenderlich.android.creaturemon.model.Creature
import com.raywenderlich.android.creaturemon.model.CreatureAttributes
import com.raywenderlich.android.creaturemon.model.CreatureGenerator

class CreatureViewmodel(private val generator: CreatureGenerator = CreatureGenerator()): ViewModel() {

    private val creatureLiveData = MutableLiveData<Creature>()

    fun getCreatureLiveData(): LiveData<Creature> = creatureLiveData

    var name = ""
    var drawable = 0
    var intelligence = 0
    var strength = 0
    var endurance = 0

    lateinit var creature: Creature

    fun updateCreature(){
        val attributes = CreatureAttributes(intelligence,strength,endurance)
        creature = generator.generateCreature(attributes,name,drawable)

        creatureLiveData.postValue(creature)
    }

    fun attributeSelected(attributeType: AttributeType,position: Int){
        when(attributeType){
            AttributeType.INTELLIGENCE -> intelligence =  AttributeStore.INTELLIGENCE[position].value
            AttributeType.STRENGTH -> strength = STRENGTH[position].value
            AttributeType.ENDURANCE -> endurance = ENDURANCE[position].value
        }
        updateCreature()
    }

    fun drawableSelected(drawable: Int){
        this.drawable = drawable
        updateCreature()
    }

}