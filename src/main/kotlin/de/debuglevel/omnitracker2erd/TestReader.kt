package de.debuglevel.omnitracker2erd

import de.debuglevel.omnitracker2erd.entityrelationship.Attribute
import de.debuglevel.omnitracker2erd.entityrelationship.Cardinality
import de.debuglevel.omnitracker2erd.entityrelationship.Entity
import de.debuglevel.omnitracker2erd.entityrelationship.Relationship

object TestReader : Reader {
    override val entities: MutableList<Entity> = mutableListOf()
    override val relationships: MutableList<Relationship> = mutableListOf()

    init
    {
        val personAttributes = mutableListOf<Attribute>()
        personAttributes.add(Attribute("id", true, false, "int"))
        personAttributes.add(Attribute("name", false, false, "string"))
        personAttributes.add(Attribute("location", false, true, "ref"))
        val personEntity = Entity("Person", personAttributes)
        entities.add(personEntity)

        val locationAttributes = mutableListOf<Attribute>()
        locationAttributes.add(Attribute("id", true, false, "int"))
        locationAttributes.add(Attribute("name", false, false, "string"))
        locationAttributes.add(Attribute("country", false, false, "string"))
        val locationEntity = Entity("Location", locationAttributes)
        entities.add(locationEntity)

        val relationship = Relationship(
                left = personEntity,
                right = locationEntity,
                rightHasThatManyLefts = Cardinality.ZeroOrMore,
                leftHasThatManyRights = Cardinality.One
                )

        relationships.add(relationship)
    }
}