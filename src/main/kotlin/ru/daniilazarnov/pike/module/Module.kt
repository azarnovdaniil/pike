package ru.daniilazarnov.pike.module

interface Module<A : Any> {

    fun build(ast: A): String

}