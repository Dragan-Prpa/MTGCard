package com.example.ljamaljubav

class MTGCard(
    val title:String = "",
    val manaCost:List<String> = emptyList(),
    var description:String = "",
    var type:String = "",
    var strength:Int = -1,
    var toughness:Int = -1
)
