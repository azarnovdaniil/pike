package ru.daniilazarnov.pike.dialect

interface OperationBuilder<A : Any> {

    fun build(ast: A, generator: Generator)

}