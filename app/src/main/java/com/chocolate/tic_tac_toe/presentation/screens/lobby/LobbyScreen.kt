package com.chocolate.tic_tac_toe.presentation.screens.lobby

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chocolate.tic_tac_toe.R
import com.chocolate.tic_tac_toe.presentation.ui.theme.DarkOnBackground38
import com.chocolate.tic_tac_toe.presentation.ui.theme.DarkOnBackground87
import com.chocolate.tic_tac_toe.presentation.ui.theme.fingerPaintFamily

@Preview(showSystemUi = true)
@Composable
fun LobbyScreen(){

}

@Composable
 fun Header(playerName:String){
     Row() {


     }
 }

@Composable
fun PlayerImage(){
    Image(painter = painterResource(id = R.drawable.avatar)
        , contentDescription =""
    , modifier = Modifier
            .clip(CircleShape)
            .size(36.dp))
}

@Composable
fun PlayerDetails(name:String){
    Row() {
        Text(text = "player name $name"
        , modifier = Modifier.padding(start = 8.dp)
        ,style = TextStyle(
                color =DarkOnBackground87 ,
                fontSize = 14.sp,
                fontFamily = fingerPaintFamily))
    }
    Row() {
        Text(text = "player name $name"
            , modifier = Modifier.padding(start = 8.dp)
            ,style = TextStyle(
                color =DarkOnBackground38 ,
                fontSize = 14.sp,
                fontFamily = fingerPaintFamily))

    }

}