package ru.daniilazarnov.pike.core

interface Predicate

/**
 * Not expression
 */
class NotExpression(val param: Any?) : Predicate

fun not(predicate: Predicate): NotExpression {
    return NotExpression(predicate)
}

/**
 * And expression
 */
class AndExpression(val left: Any?, val right: Any?) : Predicate

infix fun Predicate.and(predicate: Predicate): AndExpression {
    return AndExpression(this, predicate)
}

/**
 * Or expression
 */
class OrExpression(val left: Any?, val right: Any?) : Predicate

infix fun Predicate.or(predicate: Predicate): OrExpression {
    return OrExpression(this, predicate)
}

/**
 * Equals expression
 */
class EqExpression(val left: Any?, val right: Any?) : Predicate

infix fun Relation.Property.eq(property: Relation.Property): EqExpression {
    return EqExpression(this, property)
}

infix fun Relation.Property.eq(str: String?): EqExpression {
    return EqExpression(this, str)
}

infix fun Relation.Property.eq(num: Number): EqExpression {
    return EqExpression(this, num)
}

infix fun Relation.Property.eq(flag: Boolean): EqExpression {
    return EqExpression(this, flag)
}

/**
 * Irrelevant expression
 */
class IrrelExpression : Predicate

fun irrelevant(): IrrelExpression {
    return IrrelExpression()
}

/**
 * Not equals expression
 */
class NeExpression(val left: Any?, val right: Any?) : Predicate

infix fun Relation.Property.ne(property: Relation.Property): NeExpression {
    return NeExpression(this, property)
}

infix fun Relation.Property.ne(str: String?): NeExpression {
    return NeExpression(this, str)
}

infix fun Relation.Property.ne(num: Number): NeExpression {
    return NeExpression(this, num)
}

infix fun Relation.Property.ne(flag: Boolean): NeExpression {
    return NeExpression(this, flag)
}

/**
 * Less than expression
 */
class LtExpression(val left: Any?, val right: Any?) : Predicate

infix fun Relation.Property.lt(property: Relation.Property): LtExpression {
    return LtExpression(this, property)
}

infix fun Relation.Property.lt(str: String?): LtExpression {
    return LtExpression(this, str)
}

infix fun Relation.Property.lt(num: Number): LtExpression {
    return LtExpression(this, num)
}

/**
 * Less than or equal expression
 */
class LteExpression(val left: Any?, val right: Any?) : Predicate

infix fun Relation.Property.lte(property: Relation.Property): LteExpression {
    return LteExpression(this, property)
}

infix fun Relation.Property.lte(str: String?): LteExpression {
    return LteExpression(this, str)
}

infix fun Relation.Property.lte(num: Number): LteExpression {
    return LteExpression(this, num)
}

/**
 * Greater than expression
 */
class GtExpression(val left: Any?, val right: Any?) : Predicate

infix fun Relation.Property.gt(property: Relation.Property): GtExpression {
    return GtExpression(this, property)
}

infix fun Relation.Property.gt(str: String?): GtExpression {
    return GtExpression(this, str)
}

infix fun Relation.Property.gt(num: Number): GtExpression {
    return GtExpression(this, num)
}

/**
 * Greater than or equal expression
 */
class GteExpression(val left: Any?, val right: Any?) : Predicate

infix fun Relation.Property.gte(property: Relation.Property): GteExpression {
    return GteExpression(this, property)
}

infix fun Relation.Property.gte(str: String?): GteExpression {
    return GteExpression(this, str)
}

infix fun Relation.Property.gte(num: Number): GteExpression {
    return GteExpression(this, num)
}