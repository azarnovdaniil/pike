package ru.daniilazarnov.pike.core

import ru.daniilazarnov.pike.query.Ordering
import ru.daniilazarnov.pike.query.Projection

open class Relation(private val name: String) {

    inner class Property(val name: String) : Projection {

        val relation: Relation
            get() = this@Relation

        val asc: Ordering
            get() = Ordering.By(this, true)

        val desc: Ordering
            get() = Ordering.By(this, false)

        override fun toString(): String {
            return name
        }
    }

    override fun toString(): String {
        return name
    }
}

operator fun Relation.Property.rangeTo(property: Relation.Property): Iterable<Relation.Property> {
    return listOf(this, property)
}

operator fun Iterable<Relation.Property>.rangeTo(property: Relation.Property): Iterable<Relation.Property> {
    return this.plusElement(property)
}

operator fun Iterable<Ordering>.rangeTo(ordering: Ordering): Iterable<Ordering> {
    return if (this is Ordering) listOf(this, ordering) else this.plusElement(ordering)
}
