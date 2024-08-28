package io.github.emusute1212.calculator.model.entity

sealed interface Controller {
    val text: String

    enum class Numbers(
        override val text: String,
    ) : Controller {
        Dot(
            text = "."
        ),
        Zero(
            text = "0"
        ),
        One(
            text = "1"
        ),
        Two(
            text = "2"
        ),
        Three(
            text = "3"
        ),
        Four(
            text = "4"
        ),
        Five(
            text = "5"
        ),
        Six(
            text = "6"
        ),
        Seven(
            text = "7"
        ),
        Eight(
            text = "8"
        ),
        Nine(
            text = "9"
        ),
        ;
    }

    enum class Operators(
        override val text: String,
    ) : Controller {
        Divide(
            text = "÷"
        ),
        Times(
            text = "×"
        ),
        Minus(
            text = "-"
        ),
        Plus(
            text = "+"
        ),
        Equal(
            text = "="
        ),
        ;
    }

    enum class Specials(
        override val text: String,
    ) : Controller {
        AllClear(
            text = "AC"
        ),
        Clear(
            text = "C"
        ),
        Switch(
            text = "+/-"
        ),
        Percent(
            text = "%"
        ),
        ;
    }
}
