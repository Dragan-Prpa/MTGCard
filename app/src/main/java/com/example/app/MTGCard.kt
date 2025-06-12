package com.example.app

class MTGCard(
    val title:String = "",
    val manaCost:List<String> = emptyList(),
    var description:String = "",
    var type:String = "",
    var strength:Int = -1,
    var toughness:Int = -1,
    val imageUrl:String = "",
    val fullImageUrl:String = "",    //front
    val backImageUrl:String = ""
)
