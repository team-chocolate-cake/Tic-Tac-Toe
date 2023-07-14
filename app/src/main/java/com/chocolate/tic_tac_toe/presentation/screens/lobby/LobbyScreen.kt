package com.chocolate.tic_tac_toe.presentation.screens.lobby

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chocolate.tic_tac_toe.R
import com.chocolate.tic_tac_toe.presentation.screens.composable.ButtonApp
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical12
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical24
import com.chocolate.tic_tac_toe.presentation.screens.composable.SpacerVertical8
import com.chocolate.tic_tac_toe.presentation.screens.composable.TikTakToeScaffold
import com.chocolate.tic_tac_toe.presentation.screens.lobby.components.PlayerContent
import com.chocolate.tic_tac_toe.presentation.screens.lobby.components.TopThreePlayers
import com.chocolate.tic_tac_toe.presentation.theme.DarkOnBackground38
import com.chocolate.tic_tac_toe.presentation.theme.DarkOnBackground87
import com.chocolate.tic_tac_toe.presentation.theme.fingerPaintFamily

@Preview(showSystemUi = true)
@Composable
fun LobbyScreen() {
    LobbyContent()
}

@Composable
fun LobbyContent() {
    TikTakToeScaffold {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .scrollable(
                    state = scrollState, orientation = Orientation.Vertical
                )
        ) {
            Header()
            SpacerVertical24()
            TopThreePlayers()
            SpacerVertical24()
            Players()
            SpacerVertical24()
            ButtonApp(text = "Create Game"
                , onClick = { })

        }
    }
}


@Composable
private fun Players() {
    Text(
        text = "Players",
        style = MaterialTheme.typography.bodyLarge,
        color = DarkOnBackground87,
    )
    SpacerVertical8()
    LazyColumn() {
        items(5) {

            PlayerContent()
            SpacerVertical12()
        }
    }
}




    @Composable
    fun Header() {
        Column() {
            PlayerImage()
        }
        Column() {
            PlayerDetails("jinana",3500)
        }
    }




    @Composable
    fun PlayerDetails(name:String,score:Int){
        Row() {
            Text(text = "player name $name"
                , modifier = Modifier.padding(start = 8.dp)
                ,style = TextStyle(
                    color =DarkOnBackground87 ,
                    fontSize = 14.sp,
                    fontFamily = fingerPaintFamily))
        }
        Row() {
            Column() {
                Text(text = "player score $score"
                    , modifier = Modifier.padding(start = 8.dp)
                    ,style = TextStyle(
                        color =DarkOnBackground38 ,
                        fontSize = 14.sp,
                        fontFamily = fingerPaintFamily))
            }
            
            Column() {
                Image(painter = painterResource(id = R.drawable.ic_fire),
                    contentDescription =""
                , modifier = Modifier.size(12.dp)
                        .padding(start = 4.dp))
            }

        }
}

@Composable
fun PlayerImage(){
    Image(painter = painterResource(id = R.drawable.avatar),
        contentDescription =""
        , modifier = Modifier
            .clip(CircleShape)
            .size(36.dp))
}