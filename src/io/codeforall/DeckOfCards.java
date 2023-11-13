package io.codeforall;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class DeckOfCards {
    private LinkedList<Card> deckOfCards;

    public DeckOfCards() {
        deckOfCards = generateCards();

    }

    public LinkedList<Card> generateCards() {
        deckOfCards = new LinkedList<>();
        for (Card.Suits s : Card.Suits.values()) {
            for (Card.Rank r : Card.Rank.values()) {
                Card c = new Card(s, r);

                deckOfCards.add(c);

            }
        }

        shuffle();
        for (Card c : deckOfCards) {
            System.out.println(c.getRank() + " of " + c.getSuit());
        }
        return deckOfCards;
    }

    private void shuffle() {
        for (int i = 0; i < 7; i++) {
            Collections.shuffle(this.deckOfCards);
        }
    }

    public Card drawCard() {
        Card c = deckOfCards.poll();
        System.out.println("Card drawn is: " + c.toString());
        return c;
    }
    public Card selectInicialCard(LinkedList<Card> cards){
        Card c = cards.poll();
        System.out.println("Card to place now is " + c.toString());
        return c;
    }





        public static void main(String[] args) {
        DeckOfCards c = new DeckOfCards();

    }
}
