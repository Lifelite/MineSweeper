package minesweeper

class MinefieldBuild(
    val rows: Int? = 9,
    val columns: Int? = 9,
    val bombs: Int? = 10
) {
    var minefield: MutableList<MutableList<String>>? = null
    var bombIndexes: MutableList<MutableList<Int>>? = mutableListOf()

    init {
        buildBySize()

    }

    private fun hintHelper(item: String): String {
        return when (item) {
            "X" -> "X"
            "." -> "1"
            else -> {
                var i = item.toInt()
                i += 1
                return i.toString()
            }
        }
    }

    private fun addHints(indexes: MutableList<MutableList<Int>>?) {
        for (coordinates in indexes!!) {
            when (coordinates[0]) {
                0 -> {
                    when (coordinates[1]) {
                        0 -> {
                            minefield!![0][1] = hintHelper(minefield!![0][1])
                            minefield!![1][1] = hintHelper(minefield!![1][1])
                            minefield!![1][0] = hintHelper(minefield!![1][0])
                        }

                        columns!! - 1 -> {
                            minefield!![0][columns - 2] = hintHelper(minefield!![0][columns - 2])
                            minefield!![1][columns - 2] = hintHelper(minefield!![1][columns - 2])
                            minefield!![1][columns - 1] = hintHelper(minefield!![1][columns - 1])
                        }

                        else -> {
                            minefield!![0][coordinates[1] - 1] = hintHelper(minefield!![0][coordinates[1] - 1])
                            minefield!![0][coordinates[1] + 1] = hintHelper(minefield!![0][coordinates[1] + 1])
                            minefield!![1][coordinates[1]] = hintHelper(minefield!![1][coordinates[1]])
                            minefield!![1][coordinates[1] - 1] = hintHelper(minefield!![1][coordinates[1] - 1])
                            minefield!![1][coordinates[1] + 1] = hintHelper(minefield!![1][coordinates[1] + 1])
                        }
                    }
                }

                rows!! - 1 -> {
                    when (coordinates[1]) {
                        0 -> {
                            minefield!![rows - 1][1] = hintHelper(minefield!![rows - 1][1])
                            minefield!![rows - 2][1] = hintHelper(minefield!![rows - 2][1])
                            minefield!![rows - 2][0] = hintHelper(minefield!![rows - 2][0])
                        }

                        columns!! - 1 -> {
                            minefield!![rows - 1][columns - 2] = hintHelper(minefield!![rows - 1][columns - 2])
                            minefield!![rows - 2][columns - 2] = hintHelper(minefield!![rows - 2][columns - 2])
                            minefield!![rows - 2][columns - 1] = hintHelper(minefield!![rows - 2][columns - 1])
                        }

                        else -> {
                            minefield!![rows - 1][coordinates[1] - 1] =
                                hintHelper(minefield!![rows - 1][coordinates[1] - 1])
                            minefield!![rows - 1][coordinates[1] + 1] =
                                hintHelper(minefield!![rows - 1][coordinates[1] + 1])
                            minefield!![rows - 2][coordinates[1]] = hintHelper(minefield!![rows - 2][coordinates[1]])
                            minefield!![rows - 2][coordinates[1] - 1] =
                                hintHelper(minefield!![rows - 2][coordinates[1] - 1])
                            minefield!![rows - 2][coordinates[1] + 1] =
                                hintHelper(minefield!![rows - 2][coordinates[1] + 1])
                        }
                    }

                }

                else -> {
                    when (coordinates[1]) {
                        0 -> {
                            minefield!![coordinates[0] - 1][0] = hintHelper(minefield!![coordinates[0] - 1][0])
                            minefield!![coordinates[0] - 1][1] = hintHelper(minefield!![coordinates[0] - 1][1])
                            minefield!![coordinates[0]][1] = hintHelper(minefield!![coordinates[0]][1])
                            minefield!![coordinates[0] + 1][0] = hintHelper(minefield!![coordinates[0] + 1][0])
                            minefield!![coordinates[0] + 1][1] = hintHelper(minefield!![coordinates[0] + 1][1])
                        }

                        columns!! - 1 -> {
                            minefield!![coordinates[0] - 1][columns - 1] =
                                hintHelper(minefield!![coordinates[0] - 1][columns - 1])
                            minefield!![coordinates[0] - 1][columns - 2] =
                                hintHelper(minefield!![coordinates[0] - 1][columns - 2])
                            minefield!![coordinates[0]][columns - 2] =
                                hintHelper(minefield!![coordinates[0]][columns - 2])
                            minefield!![coordinates[0] + 1][columns - 1] =
                                hintHelper(minefield!![coordinates[0] + 1][columns - 1])
                            minefield!![coordinates[0] + 1][columns - 2] =
                                hintHelper(minefield!![coordinates[0] + 1][columns - 2])
                        }

                        else -> {
                            minefield!![coordinates[0] + 1][coordinates[1]] =
                                hintHelper(minefield!![coordinates[0] + 1][coordinates[1]])
                            minefield!![coordinates[0] + 1][coordinates[1] - 1] =
                                hintHelper(minefield!![coordinates[0] + 1][coordinates[1] - 1])
                            minefield!![coordinates[0] + 1][coordinates[1] + 1] =
                                hintHelper(minefield!![coordinates[0] + 1][coordinates[1] + 1])
                            minefield!![coordinates[0] - 1][coordinates[1]] =
                                hintHelper(minefield!![coordinates[0] - 1][coordinates[1]])
                            minefield!![coordinates[0] - 1][coordinates[1] - 1] =
                                hintHelper(minefield!![coordinates[0] - 1][coordinates[1] - 1])
                            minefield!![coordinates[0] - 1][coordinates[1] + 1] =
                                hintHelper(minefield!![coordinates[0] - 1][coordinates[1] + 1])
                            minefield!![coordinates[0]][coordinates[1] + 1] =
                                hintHelper(minefield!![coordinates[0]][coordinates[1] + 1])
                            minefield!![coordinates[0]][coordinates[1] - 1] =
                                hintHelper(minefield!![coordinates[0]][coordinates[1] - 1])
                        }
                    }


                }
            }
        }
    }

    private fun buildBySize(): MutableList<MutableList<String>> {
        var i = 0
        var t = 0
        val row: MutableList<String> = mutableListOf()
        val column: MutableList<MutableList<String>> = mutableListOf()

        while (t < rows!!) {
            row.add(".")
            t++
        }
        while (i < columns!!) {
            val newRow: MutableList<String> = mutableListOf()
            row.forEach { newRow.add(it) }
            column.add(newRow)
            i++
        }
        var b = 0
        while (b < bombs!!) {
            val number: Int = (0..<rows).random()
            val number2: Int = (0..<columns).random()
            if (column[number][number2] == ".") {
                column[number][number2] = "X"
                bombIndexes?.add(mutableListOf(number, number2))
            } else {
                continue
            }
            b++
        }
        minefield = column
        addHints(bombIndexes)
        return minefield as MutableList<MutableList<String>>
    }
}

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

class Validator(private var userData: String) {

    private fun isBlank(userData: String): Boolean {
        return userData != ""

    }

    fun isInt(): Int {
        if (isBlank(userData)) {
            while (true)
                try {
                    return userData.toInt()
                } catch (e: Exception) {
                    print("\nEnter in a valid number: ")
                    this.userData = readln()
                    continue
                }
        } else {
            return 10
        }
    }

    fun isCoordinate(): MutableList<Int>? {
        try {
            val userCoordinates = this.userData.split(" ").toTypedArray()
            if (userCoordinates.size != 2) {
                print("\nInvalid Entry, only 2 coordinates allowed, separated by space:  ")
                return null
            }
            val xCoordinate = userCoordinates[1].toInt() - 1
            val yCoordinate = userCoordinates[0].toInt() - 1
            return mutableListOf(
                xCoordinate,
                yCoordinate
            )
        } catch (e: Exception) {
            print("\nInvalid Entry, make sure to only type the coordinates separated by space:  ")
            return null
        }

    }
}


fun main() {
    print("How many mines do you want on the field? ")
    val userInput = readln()
    val v = Validator(userInput).isInt()
    val m = MinefieldBuild(bombs = v)
    val minefield = m.minefield
    val gp = GamePlay(minefield!!, v, m.bombIndexes!!)
    gp.game()
}
