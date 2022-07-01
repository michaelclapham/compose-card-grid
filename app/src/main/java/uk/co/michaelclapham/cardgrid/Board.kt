package uk.co.michaelclapham.cardgrid

import kotlin.random.Random

class Board(val width: Int, val height: Int, numSpy: Int, numAssassin: Int) {

    private var grid: Array<Array<Card>> = Array(height) { _ -> Array(width) { _ -> Card.NEUTRAL } }

    init {
        val deck = (0..(width * height)).mapIndexed { index, _ -> index }.toMutableList()
        repeat(numSpy) {
            drawFromDeck(deck, Card.SPY)
        }
        repeat(numAssassin) {
            drawFromDeck(deck, Card.ASSASSIN)
        }
    }

    private fun drawFromDeck(deck: MutableList<Int>, card: Card) {
        val rand = Random.nextInt(deck.size)
        val randIndex = deck.removeAt(rand)
        val x = randIndex / width
        val y = randIndex % height
        grid[x][y] = card
    }

    fun getAtIndex(x: Int, y: Int): Card {
        return grid[y][x]
    }

}

enum class Card {
    SPY,
    ASSASSIN,
    NEUTRAL
}