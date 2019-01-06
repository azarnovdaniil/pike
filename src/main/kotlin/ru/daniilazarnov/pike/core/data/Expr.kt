package ru.daniilazarnov.pike.core.data

interface Expr<out Relation>

class NotExpr<R : Relation>(val param: Any?) : Expr<R>
class AndExpr<R : Relation>(val left: Any?, val right: Any?) : Expr<R>
class OrExpr<R : Relation>(val left: Any?, val right: Any?) : Expr<R>

fun <R : Relation> not(expr: Expr<R>): NotExpr<R> {
    return NotExpr(expr)
}

fun <R : Relation> Expr<R>.and(expr: Expr<R>): AndExpr<R> {
    return AndExpr(this, expr)
}

fun <R : Relation> Expr<R>.or(expr: Expr<R>): OrExpr<R> {
    return OrExpr(this, expr)
}

class EqExpr<R : Relation>(val left: Any?, val right: Any?) : Expr<R>

fun <R : Relation> Relation.Property<R, out Type>.eq(property: Relation.Property<R, out Type>): EqExpr<R> {
    return EqExpr(this, property)
}

fun <R : Relation> Relation.Property<R, Type.Str>.eq(str: String?): EqExpr<R> {
    return EqExpr(this, str)
}

fun <R : Relation> Relation.Property<R, Type.Num>.eq(num: Number): EqExpr<R> {
    return EqExpr(this, num)
}

fun <R : Relation> Relation.Property<R, Type.Bool>.eq(flag: Boolean): EqExpr<R> {
    return EqExpr(this, flag)
}

class LtExpr<R : Relation>(val left: Any?, val right: Any?) : Expr<R>
class LteExpr<R : Relation>(val left: Any?, val right: Any?) : Expr<R>
class GtExpr<R : Relation>(val left: Any?, val right: Any?) : Expr<R>
class GteExpr<R : Relation>(val left: Any?, val right: Any?) : Expr<R>

fun <R : Relation> Relation.Property<R, Type.Num>.lt(property: Relation.Property<R, Type.Num>): LtExpr<R> {
    return LtExpr(this, property)
}

fun <R : Relation> Relation.Property<R, Type.Num>.lt(num: Number): LtExpr<R> {
    return LtExpr(this, num)
}

fun <R : Relation> Relation.Property<R, Type.Num>.lte(property: Relation.Property<R, Type.Num>): LteExpr<R> {
    return LteExpr(this, property)
}

fun <R : Relation> Relation.Property<R, Type.Num>.lte(num: Number): LteExpr<R> {
    return LteExpr(this, num)
}

fun <R : Relation> Relation.Property<R, Type.Num>.gt(property: Relation.Property<R, Type.Num>): GtExpr<R> {
    return GtExpr(this, property)
}

fun <R : Relation> Relation.Property<R, Type.Num>.gt(num: Number): GtExpr<R> {
    return GtExpr(this, num)
}

fun <R : Relation> Relation.Property<R, Type.Num>.gte(property: Relation.Property<R, Type.Num>): GteExpr<R> {
    return GteExpr(this, property)
}

fun <R : Relation> Relation.Property<R, Type.Num>.gte(num: Number): GteExpr<R> {
    return GteExpr(this, num)
}
