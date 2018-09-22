package de.debuglevel.omnitracker2erd

object Cli {
    fun start()
    {
        println(ErWriter(TestReader.entities, TestReader.relationships).generate())
    }
}

fun main(args : Array<String>) {
  Cli.start()
}