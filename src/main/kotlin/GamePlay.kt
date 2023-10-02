package minesweeper

class GamePlay(minefield: MutableList<MutableList<String>>, bombs: Int, bombIndexes: MutableList<MutableList<Int>>) {
    private var gameMinefield = minefield
    private var gameBombs = bombs
    private var indexes = bombIndexes

    private fun maskMinefield() {
        for (i in indexes) {
            gameMinefield[i[0]][i[1]] = "."
        }
    }

    fun game() {
        maskMinefield()
        printer(gameMinefield)
        while (this.gameBombs > 0) {

            print("\nSet/delete mines marks (x and y coordinates): ")
            val uInput: String = readln()
            val validatedInput: MutableList<Int> = Validator(uInput).isCoordinate() ?: continue
            val marker = this.gameMinefield[validatedInput[0]][validatedInput[1]]
            if (validatedInput in indexes) when (marker) {
                "." -> {
                    this.gameMinefield[validatedInput[0]][validatedInput[1]] = "*"
                    printer(gameMinefield)
                    this.gameBombs -= 1
                    continue
                }

                "*" -> {
                    this.gameMinefield[validatedInput[0]][validatedInput[1]] = "."
                    printer(gameMinefield)
                    this.gameBombs += 1
                    continue
                }

                else -> {
                    throw Exception("\nSomething went wrong, unexpected character in Minefield\n")
                }
            } else try {
                marker.toInt()
                print("\nThere is a number here!\n")
                continue
            } catch (e: Exception) {
                if (marker == ".") {
                    this.gameMinefield[validatedInput[0]][validatedInput[1]] = "*"
                    printer(gameMinefield)
                    continue
                }else{
                    this.gameMinefield[validatedInput[0]][validatedInput[1]] = "."
                    printer(gameMinefield)
                    continue
                }
            }
        }
        print("\n\nCongratulations! You found all the mines!")

    }


    private fun printer(field: MutableList<MutableList<String>>?) {
        var pfield = " │123456789│\n—│—————————│\n"
        var i = 1
        for (row in field!!) {
            pfield += "$i|"
            for (item in row) {
                pfield += item
            }
            pfield += "|"
            pfield += "\n"
            i++
        }
        pfield += "—│—————————│\n"
        print(pfield)
    }


}