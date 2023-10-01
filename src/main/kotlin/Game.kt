package minesweeper

class MinefieldBuild(
    val rows: Int? = 9,
    val columns: Int? = 9,
    val bombs: Int? = 10
) {
    var minefield: MutableList<MutableList<String>>? = null
    private var bombIndexes: MutableList<IntArray>? = null

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

    private fun addHints(indexes: MutableList<IntArray>?) {
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
                bombIndexes?.add(intArrayOf(number, number2))
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


fun printer(field: MutableList<MutableList<String>>?) {
    var pfield = ""

    for (row in field!!) {
        for (item in row) {
            pfield += item
        }
        pfield += "\n"
    }
    print(pfield)
}

class Validator(var userData: String) {

    private fun isBlank(userData: String): Boolean {
        return userData != ""

    }

    fun isInt(): Int {
        if (isBlank(userData)) {
            while (true)
                try {
                    return userData.toInt()
                } catch (e: Exception) {
                    print(e)
                    print("Enter in a valid number")
                    this.userData = readln()
                    continue
                }
        } else {
            return 10
        }
    }
}


fun main() {
    print("How many mines do you want on the field? ")
    val userInput = readln()
    val v = Validator(userInput).isInt()
    val minefield = MinefieldBuild(bombs = v).minefield
    printer(minefield)
}
