package de.debuglevel.omnitracker2erd.entityrelationship

import de.debuglevel.omnitrackerdatabasebinding.models.Folder
import java.util.*

data class Entity(val name: String, val folder: Folder, val attributes: List<Attribute>) {
    //val attributes = mutableListOf<Attribute>()

    val color: String
        get() {
            val red: Byte = (155 + (0..100).random(name.hashCode().toLong()+1)).toByte()
            val blue = (155 + (0..100).random(name.hashCode().toLong()+2)).toByte()
            val green = (155 + (0..100).random(name.hashCode().toLong()+3)).toByte()

            val hexcolor = String.format("#%02X%02X%02X", red, green, blue)

            return hexcolor
        }
}

fun IntRange.random(seed: Long) =
        Random(seed).nextInt((endInclusive + 1) - start) +  start