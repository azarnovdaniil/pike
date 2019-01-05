package ru.daniilazarnov.pike.update

import ru.daniilazarnov.pike.core.Relation

interface Definition : Iterable<Definition> {

    val property: Relation.Property
    val type: String

    override fun iterator(): Iterator<Definition> {
        return object : Iterator<Definition> {
            var valid = true
            override fun hasNext(): Boolean {
                return valid
            }
            override fun next(): Definition {
                valid = false
                return this@Definition
            }
        }
    }

    open class Column(override val property: Relation.Property, override val type: String) : Definition
}