package ru.daniilazarnov.pike.dialect.builder

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.data.Type
import ru.daniilazarnov.pike.core.unary.Selection
import ru.daniilazarnov.pike.dialect.MathGenerator

class DivisionQBuilderTest {
    private object Person : Relation("Person") {
        val id = Property<Person, Type.Id>("id")
        val name = Property<Person, Type.Str>("name")
        val age = Property<Person, Type.Num>("age")
        val weight = Property<Person, Type.Num>("weight")
        val address = Property<Person, Type.Id>("address")
        val phone = Property<Person, Type.Id>("phone")
    }

    private object Address : Relation("Address") {
        val id = Property<Person, Type.Id>("id")
        val city = Property<Person, Type.Str>("city")
    }

    @Test
    fun `test division`() {
        val expected = "((Person) ÷ (Address))"
        val result = Selection.selection(Person).division(Address).build(MathGenerator())

        Assertions.assertEquals(expected, result)
    }

}