package de.debuglevel.omnitracker2erd

import de.debuglevel.omnitracker2erd.entityrelationship.Attribute
import de.debuglevel.omnitracker2erd.entityrelationship.Cardinality
import de.debuglevel.omnitracker2erd.entityrelationship.Entity
import de.debuglevel.omnitracker2erd.entityrelationship.Relationship

class ErWriter(val entities: List<Entity>, val relationships: List<Relationship>) {
    fun generate(): String
    {
        var output = ""
        output += entities.map { generate(it) }.joinToString(separator = "\n\n")
        output += "\n\n"
        output += relationships.map { generate(it) }.joinToString(separator = "\n")

        return output
    }

    fun generate(entity: Entity): String
    {
        var output = ""
        output += "[${entity.name}]"
        output += " {bgcolor: \"${entity.color}\"}\n"
        output += entity.attributes.map { generate(it) }.joinToString(separator = "\n")

        return output
    }

    fun generate(attribute: Attribute): String
    {
        var output = ""
        output += if (attribute.foreignKey) "+" else ""
        output += if (attribute.primaryKey) "*" else ""
        output += attribute.name
        output += if (attribute.label != null) " {label: \"${attribute.label}\"}" else ""

        return output
    }

    fun generate(relationship: Relationship): String
    {
        var output = ""
        output += "${relationship.left.name} "
        output += when (relationship.rightHasThatManyLefts){
            Cardinality.One -> "1"
            Cardinality.OneOrMore -> "+"
            Cardinality.ZeroOrOne -> "?"
            Cardinality.ZeroOrMore -> "*"
            else -> "???"
        }
        output += "--"
        output += when (relationship.leftHasThatManyRights){
            Cardinality.One -> "1"
            Cardinality.OneOrMore -> "+"
            Cardinality.ZeroOrOne -> "?"
            Cardinality.ZeroOrMore -> "*"
            else -> "???"
        }
        output += " ${relationship.right.name}"
        output += if (relationship.label != null) " {label: \"${relationship.label}\"}" else ""

        return output
    }
}