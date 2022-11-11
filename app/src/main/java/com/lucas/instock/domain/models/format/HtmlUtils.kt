package com.lucas.instock.domain.models.format

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

data class PathElement(
    val element: String,
    val className: String? = null,
    val id: String? = null,
)

fun String.jsoupElement(): Document =
    Jsoup.parse(this)

fun <T> String.jsoupElement(init: Document.() -> T): T = jsoupElement().init()

fun mapPath(path: String): List<PathElement> {
    return path.split("/").map {
        if (it.contains(".")) {
            it.split(".").let { (element, className) ->
                PathElement(
                    element = element,
                    className = className
                )
            }
        } else if (it.contains("#")) {
            it.split("#").let { (element, id) ->
                PathElement(
                    element = element,
                    id = id
                )
            }
        } else {
            PathElement(
                element = it
            )
        }
    }
}

fun PathElement.classNameQueryFormat(): String? = className?.replace(
    "\\s".toRegex(), "."
)

fun Element.findElement(path: String): Element? {
    val pathList = mapPath(path)
    var elementsFound = emptyList<Element>()

    pathList.forEach { pathElement ->

        if (elementsFound.isEmpty()) {
            elementsFound = findAllElements(pathElement)

        } else {
            val tempElementsFound = mutableListOf<Element>()

            elementsFound.forEach { elementHtml ->

                val result =elementHtml.findAllDirectChildElements(pathElement)
                tempElementsFound.addAll(result)
            }

            if (tempElementsFound.isEmpty())
                return null

            elementsFound = tempElementsFound
        }
    }

    return elementsFound.firstOrNull()
}

private fun Element.findAllElements(pathElement: PathElement): List<Element> =
    select(
        pathElement.mapForQuery()
    ).filterByClassName(pathElement.className)

private fun Element.findAllDirectChildElements(pathElement: PathElement): List<Element> {
    val query = ":root > ${pathElement.mapForQuery()}"
    return select(query)
        .filterByClassName(pathElement.className)
}

// Filter by specific className
private fun List<Element>.filterByClassName(expectedClassName: String?) = filter {
    expectedClassName == null ||
            it.className().trim().lowercase() == expectedClassName.trim().lowercase()
}


private

fun PathElement.mapForQuery(): String {
    return element +
            if (className != null) {
                ".${classNameQueryFormat()}"
            } else if (id != null) {
                "#$id"
            } else ""
}