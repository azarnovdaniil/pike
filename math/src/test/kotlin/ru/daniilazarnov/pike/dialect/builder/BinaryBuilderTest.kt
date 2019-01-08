package ru.daniilazarnov.pike.dialect.builder

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.daniilazarnov.pike.core.data.Relation
import ru.daniilazarnov.pike.core.data.Type
import ru.daniilazarnov.pike.core.eq
import ru.daniilazarnov.pike.core.operation.unary.Selection.Companion.selection
import ru.daniilazarnov.pike.dialect.MathGenerator

class BinaryBuilderTest {
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

    private object Contact : Relation("Contact") {
        val id = Property<Contact, Type.Id>("id")
        val phone = Property<Contact, Type.Str>("phone")
    }

    @Test
    fun `test natural join`() {
        val expected = "(Person ⋈ Address)"
        val result = selection(Person).naturalJoin(Address).build(MathGenerator())

        assertEquals(expected, result)
    }

    @Test
    fun `test join`() {
        val expected = "(Person ⋈(Person.address = Address.id) Address)"
        val result = selection(Person)
                .equiJoin(Address, Person.address.eq(Address.id))
                .build(MathGenerator())

        assertEquals(expected, result)
    }

    @Test
    fun `test double join`() {
        val expected = "(Person ⋈(Person.address = Address.id) Address) ⋈(Person.phone = Contact.id) Contact)"
        val result = selection(Person)
                .equiJoin(Contact, Person.phone.eq(Contact.id))
                .equiJoin(Address, Person.address.eq(Address.id))
                .build(MathGenerator())

        assertEquals(expected, result)
    }

}
