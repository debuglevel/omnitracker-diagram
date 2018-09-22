package de.debuglevel.omnitracker2erd.entityrelationship

data class Attribute(val name: String,
                     val primaryKey: Boolean,
                     val foreignKey: Boolean,
                     val label: String?) {
}