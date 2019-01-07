package ru.daniilazarnov.pike.core

import ru.daniilazarnov.pike.dialect.Generator

interface Operation {

    fun build(generator: Generator): String

}