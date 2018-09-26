package de.debuglevel.omnitracker2erd.entityrelationship

import de.debuglevel.omnitrackerdatabasebinding.models.Field

data class Attribute(val name: String,
                     val field: Field,
                     val primaryKey: Boolean,
                     val foreignKey: Boolean,
                     val label: String? = null)