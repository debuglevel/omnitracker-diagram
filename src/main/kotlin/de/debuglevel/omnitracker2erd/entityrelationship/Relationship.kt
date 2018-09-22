package de.debuglevel.omnitracker2erd.entityrelationship

data class Relationship(val left: Entity,
                        val right: Entity,
                        val leftHasThatManyRights: Cardinality,
                        val rightHasThatManyLefts: Cardinality,
                        val label: String? = null) {
}