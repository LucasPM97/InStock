package com.lucas.instock.domain.models.format

data class PathElement(
    val element: String,
    val className: String? = null
)

class HtmlString(val text: String)

fun String.htmlString() = HtmlString(this)

fun <T> String.htmlString(init: HtmlString.() -> T): T = HtmlString(this).init()

private fun HtmlString.elementStartingPointIndex(fromIndex: Int): Int {
    var elementsFound = 0
    var lastSlashFoundIndex = -1
    var lastMoreThanKeyFoundIndex = -1

    for (i in fromIndex downTo 0) {
        when (text[i]) {
            '>' -> {
                lastMoreThanKeyFoundIndex = i
            }
            '<' -> {
                if (lastSlashFoundIndex == (i + 1)) {
                    elementsFound += 1
                } else {
                    elementsFound -= 1
                }
            }
            '/' -> {
                lastSlashFoundIndex = i
                if (lastMoreThanKeyFoundIndex == (i + 1)) {
                    elementsFound += 1
                }
            }
        }

        if (elementsFound < 0) {
            return i
        }
    }

    throw Exception("No other element found")
}

private fun HtmlString.elementEndingPointIndex(fromIndex: Int): Int {
    var elementsFound = 0
    var lastSlashFoundIndex = -1
    var lastLessThanKeyFoundIndex = -1

    for (i in fromIndex until text.length) {
        when (text[i]) {
            '>' -> {
                if (lastSlashFoundIndex == (i - 1)) {
                    elementsFound -= 1
                }

                if (elementsFound == 0) {
                    return i
                }
            }
            '<' -> {
                lastLessThanKeyFoundIndex = i
                elementsFound += 1
            }
            '/' -> {
                lastSlashFoundIndex = i
                if (lastLessThanKeyFoundIndex == (i - 1)) {
                    elementsFound -= 2
                }
            }
        }
        if (i == text.lastIndex) {
            return i
        }
    }

    throw Exception("Error trying to get the Ending Point of an element")
}

fun HtmlString.findElement(path: List<PathElement>): HtmlString? {

    var elementsFound: MutableList<HtmlString> = mutableListOf()

    path.forEach { pathElement ->

        if (elementsFound.isEmpty()) {
            searchAllElementsIndex(pathElement.element, pathElement.className)
                .forEach {
                    elementsFound.add(this.elementAt(it))
                }
        } else {
            val tempElementsFound: MutableList<HtmlString> = mutableListOf()

            elementsFound.forEach { elementHtml ->
                elementHtml
                    .searchAllElementsIndex(pathElement.element, pathElement.className)
                    .forEach {
                        if (elementHtml.isParentOf(it)) {
                            tempElementsFound.add(
                                elementHtml.elementAt(it)
                            )
                        }

                    }
            }


            if (tempElementsFound.isEmpty()) return null

            elementsFound = tempElementsFound
        }
    }

    print(elementsFound)

    return elementsFound.firstOrNull()
}

fun HtmlString.searchAllElementsIndex(elementTag: String, className: String?): List<Int> {
    val searchBy: String = "<${elementTag}" +
            if (className != null) {
                ".*class(?==\"$className\")"
            } else ""

    return Regex(searchBy)
        .findAll(this.text)
        .map {
            it.range.first
        }.toList()
}

fun HtmlString.elementAt(index: Int): HtmlString {
    try {
        val end = elementEndingPointIndex(index)
        return text.substring(index..end).htmlString()

    } catch (e: Exception) {
        println(e)
        throw e
    }
}

fun HtmlString.isParentOf(startPoint: Int): Boolean {
    var elementsFound = 0

    for (i in startPoint downTo 1) {
        when (text[i]) {
            '>' -> {
                if (text[i - 1] == '/') {
                    elementsFound += 1
                }
            }
            '<' -> {
                if (text[i + 1] == '/') {
                    elementsFound += 1
                } else {
                    elementsFound -= 1
                }
            }
        }
        if (elementsFound < 0) {
            return false
        }
    }

    return true
}

