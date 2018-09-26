package de.debuglevel.omnitracker2erd

import de.debuglevel.omnitracker2erd.entityrelationship.Attribute
import de.debuglevel.omnitracker2erd.entityrelationship.Cardinality
import de.debuglevel.omnitracker2erd.entityrelationship.Entity
import de.debuglevel.omnitracker2erd.entityrelationship.Relationship
import de.debuglevel.omnitrackerdatabasebinding.OmnitrackerDatabase
import de.debuglevel.omnitrackerdatabasebinding.models.FieldType

class OmnitrackerReader : Reader {
    fun sanitize(name: String): String {
        return name

        return name.replace(" ", "_")
                .replace("-", "_")
                .replace("(", "_")
                .replace(")", "_")
                .replace(",", "_")
                .replace(":", "_")
                .replace(".", "_")
                .replace("/", "_")
                .replace("\\", "_")
                .replace("&", "_")
                .replace("[", "_")
                .replace("]", "_")
                .replace("%", "_")
    }

    private val omnitrackerDatabase = OmnitrackerDatabase()

    private val fields = omnitrackerDatabase.getFields()
    private val folders = omnitrackerDatabase.getFolders()

    override val entities: List<Entity>
        get() {
            val entities = folders.map { folder ->
                val attributes =
                        fields
                                .filter { it.folder == folder }
                                .map { Attribute(sanitize(it.label), it, false, false) }
                Entity(sanitize("[${folder.id}] ${folder.name}"), folder, attributes)
            }

            return entities
        }

    override val relationships: List<Relationship>
        get() {
            val relationships = mutableListOf<Relationship>()

            for (entity in entities) {
                for (attribute in entity.attributes.filter { it.field.referenceFolder != null }) {
                    val referencedEntity = entities.first { it.folder == attribute.field.referenceFolder }

                    val cardinality = when (attribute.field.type) {
                        FieldType.ObjectReference -> Cardinality.ZeroOrOne
                        FieldType.ObjectReferenceList -> Cardinality.ZeroOrMore
                        else -> Cardinality.Unknown
                    }

                    val relationship = Relationship(entity, referencedEntity, cardinality, Cardinality.One)
                    relationships.add(relationship)
                }
            }

            return relationships
        }
}