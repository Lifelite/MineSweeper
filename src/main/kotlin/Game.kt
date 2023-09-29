package minesweeper

class MinefieldBuild(
    val rows:Int? = 9,
    val columns: Int? = 9,
    val bombs: Int? = 10)
{
    var minefield: MutableList<MutableList<String>>? = null

    init {
        buildBySize()

    }

    private fun addHints(field:MutableList<MutableList<String>>): MutableList<MutableList<String>> {
        //Need to find each X, note their index, then use that index to determine the position of each X
        TODO()
    }

    private fun buildBySize(): MutableList<MutableList<String>> {
        var i = 0
        var t = 0
        val row:MutableList<String> = mutableListOf()
        val column : MutableList<MutableList<String>> = mutableListOf()

        while (t < rows!!) {
            row.add(".")
            t++
        }
        while (i < columns!!) {
            val newRow: MutableList<String> = mutableListOf()
            row.forEach{ newRow.add(it)}
            column.add(newRow)
            i++
        }
        var b = 0
        while(b < bombs!!) {
            val number:Int = (0..<rows).random()
            val number2:Int = (0..<columns).random()
            if (column[number][number2] == ".") {
                column[number][number2] = "X"
            }else{
                continue
            }
            b++
        }
        minefield = column
        return minefield as MutableList<MutableList<String>>
    }
}



fun printer(field:MutableList<MutableList<String>>?){
    var pfield = ""

    for (row in field!!) {
        for (item in row){
            pfield += item
        }
        pfield += "\n"
    }
    print(pfield)
}

class Validator(var userData:String) {

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
        }
        else{
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
