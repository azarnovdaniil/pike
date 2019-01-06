package ru.daniilazarnov.pike.dialect

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.data.Type
import ru.daniilazarnov.pike.core.data.rangeTo
import ru.daniilazarnov.pike.core.update.Rename

internal class RenameBuilderTest {

    private object Person : Relation("Person") {
        val id = Property<Person, Type.Id>("id")
        val name = Property<Person, Type.Str>("name")
        val age = Property<Person, Type.Num>("age")
        val weight = Property<Person, Type.Num>("weight")
        val address = Property<Person, Type.Id>("address")
        val phone = Property<Person, Type.Id>("phone")
    }

    @Test
    fun `test rename`() {
        val expected = "ρ(age -> old)(Person)"
        val result = Rename.rename(Person, Person.age, "old").build(MathGenerator())

        assertEquals(expected, result)
    }

    @Test
    fun `test renames`() {
        val expected = "ρ(age, weight -> old, fat)(Person)"
        val result = Rename.rename(Person, Person.age..Person.weight, listOf("old", "fat")).build(MathGenerator())

        assertEquals(expected, result)
    }
}