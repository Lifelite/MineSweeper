package minesweeper

fun main() {
    print("How many mines do you want on the field? ")
    val userInput = readln()
    val v = Validator(userInput).isInt()
    val m = MinefieldBuild(bombs = v)
    val minefield = m.minefield
    val gp = GamePlay(minefield!!, v, m.bombIndexes!!)
    gp.game()
}
