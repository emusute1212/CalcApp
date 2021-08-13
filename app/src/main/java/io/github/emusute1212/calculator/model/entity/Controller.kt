package io.github.emusute1212.calculator.model.entity

sealed interface Controller {
    val order: Int

    /**
     * @param order 上から何番目に表示するかという順番
     */
    enum class Operators(
        override val order: Int
    ) : Controller {
        DIVIDE(0),
        TIMES(1),
        MINUS(2),
        PLUS(3),
        EQUAL(4);

        companion object {
            fun getOperatorsFromOrder(order: Int): Operators? {
                return values().find {
                    it.order == order
                }
            }
        }
    }

    enum class Specials(
        override val order: Int
    ) : Controller {
        CLEAR(0),
        SWITCH(1),
        PERCENT(2);

        companion object {
            fun getSpecialsFromOrder(order: Int): Specials? {
                return values().find {
                    it.order == order
                }
            }
        }
    }
}

fun Controller.Operators.toStr(): String {
    return when (this) {
        Controller.Operators.DIVIDE -> "÷"
        Controller.Operators.TIMES -> "×"
        Controller.Operators.MINUS -> "-"
        Controller.Operators.PLUS -> "+"
        Controller.Operators.EQUAL -> "="
    }
}