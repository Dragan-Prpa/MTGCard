
import android.util.Log
import com.example.app.MTGCard
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

fun FetchCard(cardName:String) : List<MTGCard>? {
    return try {
        val url = URL("https://api.scryfall.com/cards/search?q=$cardName")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val response = connection.inputStream.bufferedReader().use { it.readText() }

        val cards = mutableListOf<MTGCard>()
        val responseObject = JSONObject(response)
        val cardsArray = responseObject.getJSONArray("data")

        for (i in 0 until cardsArray.length()) {
            val card = cardsArray.getJSONObject(i)

            var strength = -1
            var toughness = -1
            var name = ""
            var manaCost = emptyList<String>()
            var description = ""
            var type = ""
            var imageUrl = ""
            var fullImageUrl = ""
            var backImageUrl= ""
//            if(card.getString("layout")=="normal"){
//                 name = card.getString("name")
//                 manaCost = card.getString("mana_cost")
//                    .replace("{", "")
//                    .split("}")
//                    .filter { it.isNotBlank() }
//                 description = card.getString("oracle_text")
//                 type = card.getString("type_line")
//                 if(type.contains("Creature")) {
//                      strength = card.getInt("power")
//                    toughness = card.getInt("toughness")
//                 }
//                 imageUrl = card.getJSONObject("image_uris").getString("art_crop")
//            }


            if(card.has("card_faces") && !card.has("image_uris")){
                fullImageUrl = card.getJSONArray("card_faces").getJSONObject(0).getJSONObject("image_uris").getString("large")
                backImageUrl = card.getJSONArray("card_faces").getJSONObject(1).getJSONObject("image_uris").getString("large")
            }
            else {
                fullImageUrl = card.getJSONObject("image_uris").getString("large")
            }


            val MTGCard = MTGCard(
                title = name,
                manaCost = manaCost,
                description = description,
                type = type,
                strength = strength,
                toughness = toughness,
                imageUrl = imageUrl,
                fullImageUrl = fullImageUrl,
                backImageUrl = backImageUrl
            )
            cards.add(MTGCard)
        }
         return cards

    }
    catch (e: Exception) {
        Log.e("fetchCard", "Error fetching card: ${e.message}", e)
        null
    }


}
