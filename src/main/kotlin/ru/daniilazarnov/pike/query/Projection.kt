package ru.daniilazarnov.pike.query

import ru.daniilazarnov.pike.core.builder.ProjectionBuilder
import ru.daniilazarnov.pike.core.data.PropertyIterator
import ru.daniilazarnov.pike.core.data.Relation

class Projection<R : Relation>(
        val projection: Iterable<PropertyIterator<R>>,
        val selection: Selection<R>) : Build {

    infix fun <P : Projection<R>> union(projection2: P): Union<R, Projection<R>> {
        return Union(this, projection2)
    }

    infix fun <P : Projection<R>> intersect(projection2: P): Intersection<R, Projection<R>> {
        return Intersection(this, projection2)
    }

    infix fun <P : Projection<R>> diff(projection2: P): Difference<R, Projection<R>> {
        return Difference(this, projection2)
    }

    override fun build(): String {
        return ProjectionBuilder.build(this)
    }

}
