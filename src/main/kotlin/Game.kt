package minesweeper

import kotlin.random.Random



class MinefieldBuild(
    private val rows:Int? = 9,
    private val columns: Int? = 9,
    private val bombs: Int? = 10)
{
    var minefield: MutableList<MutableList<String>>? = null

    init {
        buildBySize()

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
            val number:Int = (0 until rows).random()
            val number2:Int = (0 until columns).random()
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


fun main() {
    var pfield = ""
    val minefield = MinefieldBuild().minefield
    for (row in minefield!!) {
        for (item in row){
            pfield += item
        }
        pfield += "\n"
    }
    print(pfield)
}
