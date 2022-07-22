package uk.co.michaelclapham.cardgrid

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.co.michaelclapham.cardgrid.ui.theme.AssassinCardColor
import uk.co.michaelclapham.cardgrid.ui.theme.CardGridTheme
import uk.co.michaelclapham.cardgrid.ui.theme.SpyCardColor
import uk.co.michaelclapham.cardgrid.ui.theme.NeutralCardColor

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

fun colorForCard(card: CardType): Color {
    return when (card) {
        CardType.NEUTRAL -> NeutralCardColor
        CardType.SPY -> SpyCardColor
        CardType.ASSASSIN -> AssassinCardColor
    }
}

@Composable
fun RoundedCornerShapeDemo(){
    ExampleBox(shape = RoundedCornerShape(5.dp))
}

@Composable
fun ExampleBox(shape: Shape){
    Box(
        modifier = Modifier.size(40.dp).clip(shape).background(Color.Red)
    )
}

@Composable
fun CardGrid(board: Board) {

    Column(Modifier.fillMaxWidth()) {
        Text("width = ${board.width}, height = ${board.height}")
        repeat(board.width) { y ->
            Row(Modifier.fillMaxWidth()) {
                repeat(board.height) { x ->
                        val cardType = board.getAtIndex(x, y)
                        GameCard(cardType)
                        // RoundedCornerShapeDemo()
                }
            }
        }
    }
}

@Composable
fun RowScope.GameCard(cardType: CardType) {
    Box(
        modifier = Modifier
            .weight(1f)
            .height(40.dp)

            .clip(RoundedCornerShape(10.dp))
            .background(colorForCard(cardType))
    )

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