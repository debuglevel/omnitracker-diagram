package de.debuglevel.omnitracker2erd

object Cli {
    fun start()
    {
        println(SvgDotWriter(OmnitrackerReader().entities, OmnitrackerReader().relationships).generate())
    }
}

fun main(args : Array<String>) {
  Cli.start()
}