package de.debuglevel.omnitracker2erd

import de.debuglevel.omnitracker2erd.entityrelationship.Entity
import de.debuglevel.omnitracker2erd.entityrelationship.Relationship
import guru.nidi.graphviz.engine.Format
import guru.nidi.graphviz.engine.Graphviz
import java.io.File

class SvgDotWriter(val entities: List<Entity>, val relationships: List<Relationship>) {
    fun generate() {
        val dot = DotWriter(entities, relationships).generate()
        println(dot)
        visualizeGraph(dot)
    }

    private fun visualizeGraph(dot: String) {
        val graph = guru.nidi.graphviz.parse.Parser.read(dot)
        Graphviz.fromGraph(graph)
                .render(Format.SVG)
                .toFile(File("UML.svg"))
    }
}