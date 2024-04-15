import org.jsoup.Jsoup
import org.jsoup.nodes.Document

fun main() {
    val wikipediaUrl = "https://ru.wikipedia.org/wiki/Ложе" // Замените на нужную вам страницу

    val allLinks = extractAllLinksFromWikipedia(wikipediaUrl)
    println("Ссылки, найденные на странице Википедии:")
    allLinks.forEach { link ->
        println(link)
    }
}

fun extractAllLinksFromWikipedia(url: String): List<String> {
    return try {
        val document: Document = Jsoup.connect(url).get()
        val links = document.select("a[href]") // Выбираем все ссылки на странице
        links.map { it.attr("href") }
    } catch (e: Exception) {
        println("Ошибка при подключении к Википедии: ${e.message}")
        emptyList()
    }
}
