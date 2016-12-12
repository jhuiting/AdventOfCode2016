package util

import java.io.FileInputStream
import java.io.InputStreamReader

class InputUtils {
    fun getInputAsText(input: String): InputStreamReader {
        val stream = FileInputStream(input).buffered()
        return InputStreamReader(stream, "UTF-8")
    }
}