package ru.daniilazarnov.pike.dialect.builder

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.data.Type
import ru.daniilazarnov.pike.core.operation.unary.Selection
import ru.daniilazarnov.pike.dialect.MathGenerator

class SetBuilderTest {
    private object Person : Relation("Person") {
        val id = Property<Person, Type.Id>("id")
        val name = Property<Person, Type.Str>("name")
        val age = Property<Person, Type.Num>("age")
        val weight = Property<Person, Type.Num>("weight")
        val address = Property<Person, Type.Id>("address")
        val phone = Property<Person, Type.Id>("phone")
    }

    private object Address : Relation("Address") {
        val id = Property<Address, Type.Id>("id")
        val city = Property<Address, Type.Str>("city")
    }

    @Test
    fun `test union`() {
        val expected = "((Person) ∪ (Person))"
        val result = Selection.selection(Person).union(Selection.selection(Person)).build(MathGenerator())

        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `test division`() {
        val expected = "((Person) ÷ (Address))"
        val result = Selection.selection(Person).division(Address).build(MathGenerator())

        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `test cartesian`() {
        val expected = "((Person) × (Address))"
        val result = Selection.selection(Person).cartesian(Address).build(MathGenerator())

        Assertions.assertEquals(expected, result)
    }

}