package ru.daniilazarnov.pike.dialect

open class MathGenerator : Generator() {

    override val factory: BuilderFactory = MathBuilderFactory

}