package de.debuglevel.omnitracker2erd.entityrelationship

enum class Cardinality {
    Unknown,
    ZeroOrOne, // maps to ?
    One, // maps to 1
    ZeroOrMore, // maps to *
    OneOrMore // maps to +
}
