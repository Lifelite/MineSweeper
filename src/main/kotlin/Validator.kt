package minesweeper

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