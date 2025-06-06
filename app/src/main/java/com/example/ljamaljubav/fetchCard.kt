
import android.util.Log
import com.example.ljamaljubav.MTGCard
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

fun fetchCard(cardName:String) : MTGCard? {
    return try {
        val url = URL("https://api.scryfall.com/cards/named?fuzzy=$cardName")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val response = connection.inputStream.bufferedReader().use { it.readText() }
        val card = JSONObject(response)
        val name = card.getString("name")
        val manaCost = card.getString("mana_cost")
            .replace("{", "")
            .split("}")
            .filter { it.isNotBlank() }
        val description = card.getString("oracle_text")
        val type = card.getString("type_line")
        var strength = -1
        var toughness = -1
        if (type.contains("Creature")) {
            strength = card.getInt("power")
            toughness = card.getInt("toughness")
        }

        val imageUrl = card.getJSONObject("image_uris").getString("normal")

        MTGCard(
            title = name,
            manaCost = manaCost,
            description = description,
            type = type,
            strength = strength,
            toughness = toughness
        )

    }
    catch (e: Exception) {
        Log.e("fetchCard", "Error fetching card: ${e.message}", e)
        null
    }


}
