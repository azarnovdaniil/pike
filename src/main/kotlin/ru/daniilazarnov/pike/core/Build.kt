package ru.daniilazarnov.pike.core

import ru.daniilazarnov.pike.dialect.Writer

interface Build {

    fun build(writer: Writer): String

}