package com.chocolate.tic_tac_toe.data.local

import javax.inject.Inject

class PlayerAvatars @Inject constructor() {
    fun getPlayerAvatars(): List<String> {
        return listOf(
            "https://cdn.discordapp.com/attachments/812835553522483250/1130838240304312382/avatar_1.png",
            "https://cdn.discordapp.com/attachments/812835553522483250/1130838240526602261/avatar_2.png",
            "https://cdn.discordapp.com/attachments/812835553522483250/1130838240757305386/avatar_3.png",
            "https://cdn.discordapp.com/attachments/812835553522483250/1130838241034121216/avatar_4.png",
            "https://cdn.discordapp.com/attachments/812835553522483250/1130838241239633982/avatar_5.png",
            "https://cdn.discordapp.com/attachments/812835553522483250/1130838241457754152/avatar_6.png",
            "https://cdn.discordapp.com/attachments/812835553522483250/1130838241692626944/avatar_7.png",
        )
    }
}