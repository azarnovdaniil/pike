package ru.daniilazarnov.pike.core.data

open class Relation(private val name: String) {

    inner class Property<R : Relation, T : Type>(private val name: String) : PropertyIterator<R> {

        val relation: Relation
            get() = this@Relation

        override fun toString(): String {
            return name
        }
    }

    override fun toString(): String {
        return name
    }
}

operator fun <R : Relation> Relation.Property<R, *>.rangeTo(property: Relation.Property<R, *>): Iterable<Relation.Property<R, *>> {
    return listOf(this, property)
}

operator fun <R : Relation> Iterable<Relation.Property<R, *>>.rangeTo(property: Relation.Property<R, *>): Iterable<Relation.Property<R, *>> {
    return this.plusElement(property)
}

