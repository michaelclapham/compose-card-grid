package uk.co.michaelclapham.cardgrid

import java.lang.Exception
import kotlin.random.Random

class Board(val width: Int, val height: Int, numSpy: Int, numAssassin: Int) {

    private var grid: Array<Array<CardType>> = Array(height) { _ -> Array(width) { _ -> CardType.NEUTRAL } }

    init {
        val deck = (0 until(width * height)).mapIndexed { index, _ -> index }.toMutableList()
        repeat(numSpy) {
            drawFromDeck(deck, CardType.SPY)
        }
        repeat(numAssassin) {
            drawFromDeck(deck, CardType.ASSASSIN)
        }
    }

    private fun drawFromDeck(deck: MutableList<Int>, card: CardType) {
        var rand = Random.nextInt(deck.size)
        var randIndex = -1
        var x = -1
        var y = -1
        try {
            rand = Random.nextInt(deck.size)
            randIndex = deck.removeAt(rand)
            x = randIndex / width
            y = randIndex % height
            grid[x][y] = card
        } catch (ex: Exception) {
            println("Draw from deck exception $ex")
            // do nothing
        }

    }

    fun getAtIndex(x: Int, y: Int): CardType {
        try {
            return grid[x][y]
        } catch (ex: Exception) {
            println("ex $ex")
            return CardType.NEUTRAL
        }

    }

}

enum class CardType {
    SPY,
    ASSASSIN,
    NEUTRAL
}