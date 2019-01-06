package ru.daniilazarnov.pike.update

import ru.daniilazarnov.pike.core.builder.RenameBuilder
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.query.Build

class Rename<R : Relation>(
        val relation: R,
        val properties: Iterable<Relation.Property<R, *>>,
        val newNames: Iterable<String>
) : Build {

    companion object {
        fun <R : Relation, P : Relation.Property<R, *>> rename(relation: R, property: P, newName: String): Rename<R> {
            return Rename(relation, listOf(property), listOf(newName))
        }

        fun <R : Relation, P : Relation.Property<R, *>> rename(relation: R, properties: Iterable<Relation.Property<R, *>>, newNames: Iterable<String>): Rename<R> {
            return Rename(relation, properties, newNames)
        }
    }

    override fun build(): String {
        return RenameBuilder.build(this)
    }
}