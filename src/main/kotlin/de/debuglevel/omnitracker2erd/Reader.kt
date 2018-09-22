package de.debuglevel.omnitracker2erd

import de.debuglevel.omnitracker2erd.entityrelationship.Entity
import de.debuglevel.omnitracker2erd.entityrelationship.Relationship

interface Reader {
    val entities: List<Entity>
    val relationships: List<Relationship>
}