package ru.daniilazarnov.pike.core.data

interface PropertyIterator<R> : Iterable<PropertyIterator<R>> {
    override fun iterator(): Iterator<PropertyIterator<R>> {
        return object : Iterator<PropertyIterator<R>> {
            var valid = true
            override fun hasNext(): Boolean {
                return valid
            }

            override fun next(): PropertyIterator<R> {
                valid = false
                return this@PropertyIterator
            }
        }
    }
}
