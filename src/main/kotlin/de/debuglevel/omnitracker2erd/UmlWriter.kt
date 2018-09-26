package de.debuglevel.omnitracker2erd

import de.debuglevel.omnitracker2erd.entityrelationship.Attribute
import de.debuglevel.omnitracker2erd.entityrelationship.Cardinality
import de.debuglevel.omnitracker2erd.entityrelationship.Entity
import de.debuglevel.omnitracker2erd.entityrelationship.Relationship
import net.sourceforge.plantuml.FileFormat
import net.sourceforge.plantuml.FileFormatOption
import net.sourceforge.plantuml.SourceStringReader
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset


class UmlWriter(val entities: List<Entity>, val relationships: List<Relationship>) {
    fun generate(): String {
        var output = ""

        output += "@startuml"

        output += entities.map { generate(it) }.joinToString(separator = "\n\n")
        output += "\n\n"
        //output += relationships.map { generate(it) }.joinToString(separator = "\n")

        output += "@enduml"

        return generateFile(output)

        //return output
    }

    fun generateFile(plantumlCode: String): String {
        val reader = SourceStringReader(plantumlCode)
        val os = ByteArrayOutputStream()


        // Write the first image to "os"
        val desc = reader.generateImage(os, FileFormatOption(FileFormat.SVG))
        os.close()
        val x = os.toString()

        // The XML is stored into svg
        val svg = String(os.toByteArray(), Charset.forName("UTF-8"))

        //println("===========")
        //println(desc)

        //println("===========")
        return svg
    }

    fun generate(entity: Entity): String {
        var output = ""
        //output += "${entity.hashCode()}"
        //output += "[${entity.name}]"
        //output += " {bgcolor: \"${entity.color}\"}\n"
        output += entity.attributes.map { generate(entity, it) }.joinToString(separator = "\n")

        return output
    }

    fun generate(entity: Entity, attribute: Attribute): String {
        var output = ""
        //output += if (attribute.foreignKey) "+" else ""
        //output += if (attribute.primaryKey) "*" else ""
        //output += "${entity.name} : ${attribute.name}"

        val entityname = if (entity.hashCode() < 0) entity.hashCode() * -1 else entity.hashCode()
        val attributename = if (attribute.hashCode() < 0) attribute.hashCode() * -1 else attribute.hashCode()

        output += "$entityname : $attributename"
        //output += if (attribute.label != null) " {label: \"${attribute.label}\"}" else ""

        return output
    }

    fun generate(relationship: Relationship): String {
        var output = ""
        output += "${relationship.left.name}"

        output += when (relationship.rightHasThatManyLefts) {
            Cardinality.One -> "\"1\""
            Cardinality.OneOrMore -> "\"1+\""
            Cardinality.ZeroOrOne -> "\"0,1\""
            Cardinality.ZeroOrMore -> "\"0+\""
            else -> ""
        }
        output += " *-- "
        output += when (relationship.leftHasThatManyRights) {
            Cardinality.One -> "\"1\""
            Cardinality.OneOrMore -> "\"1+\""
            Cardinality.ZeroOrOne -> "\"0,1\""
            Cardinality.ZeroOrMore -> "\"0+\""
            else -> ""
        }
        output += "${relationship.right.name}"
        output += if (relationship.label != null) " : ${relationship.label}" else ""

        return output
    }
}