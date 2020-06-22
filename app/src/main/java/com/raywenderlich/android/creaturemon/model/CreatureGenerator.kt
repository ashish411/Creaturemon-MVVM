package com.raywenderlich.android.creaturemon.model

class CreatureGenerator {

    fun generateCreature(attributes: CreatureAttributes, name: String = "", drawable: Int = 0): Creature {
        val hitpoints = 5 * attributes.intelligence + 4 * attributes.endurance + 3 * attributes.strength
        return Creature(attributes, hitpoints, name, drawable)
    }
}