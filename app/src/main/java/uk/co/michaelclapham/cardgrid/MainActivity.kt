package uk.co.michaelclapham.cardgrid

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import uk.co.michaelclapham.cardgrid.ui.theme.AssassinCardColor
import uk.co.michaelclapham.cardgrid.ui.theme.CardGridTheme
import uk.co.michaelclapham.cardgrid.ui.theme.SpyCardColor
import uk.co.michaelclapham.cardgrid.ui.theme.NeutralCardColor
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val board = Board(5, 5, 9, 3)
        setContent {
            CardGridTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CardGrid(board)
                }
            }
        }
    }
}

fun randomColor(): Color {
    val random = Random.nextFloat()
    return if (random < 0.1) {
        AssassinCardColor
    } else if (random < 0.5) {
        SpyCardColor
    } else {
        NeutralCardColor
    }
}

fun colorForCard(card: Card): Color {
    return when (card) {
        Card.NEUTRAL -> NeutralCardColor
        Card.SPY -> SpyCardColor
        Card.ASSASSIN -> AssassinCardColor
    }
}

@Composable
fun CardGrid(board: Board) {

    Column(Modifier.fillMaxWidth()) {
        Text("width = ${board.width}, height = ${board.height}")
        repeat(board.width) { y ->
            Row() {
                repeat(board.height) { x ->
                    Column() {
                        val card = board.getAtIndex(x, y)
                        Text(
                            text = "($x, $y)",
                            color = Color.Black,
                            fontSize = 30.sp,
                            modifier = Modifier.background(colorForCard(card))
                        )
                    }
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true)
@Composable
fun DefaultPreview() {
    val board = Board(5, 5, 9, 3)
    CardGridTheme {
        CardGrid(board)
    }
}