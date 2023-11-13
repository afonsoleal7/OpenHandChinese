package io.codeforall;

public class Card {
    private Suits suit;
    private Rank rank;


    public Card(Suits suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    public Suits getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
    public String toString() {
        return rank.letter + " of " + suit;
    }
    public String toStringShort(){
        return rank.letter + suit.letter;
    }



        protected enum Suits {
        SPADES('S'),
        HEARTS('H'),
        DIAMONDS('D'),
        CLUBS('C');

        private char letter;

        public char getLetter() {
            return letter;
        }

        Suits(char letter) {
            this.letter = letter;
        }
    }
    protected enum Rank {
        TWO("2", 2),
        THREE("3", 3),
        FOUR("4", 4),
        FIVE("5", 5),
        SIX("6",6),
        SEVEN("7", 7),
        EIGHT("8", 8),
        NINE("9", 9),
        TEN("10", 10),
        JACK("J", 11),
        QUEEN("Q", 12),
        KING("K", 13),
        ACE("A", 14);

        private String letter;

        private int value;

        public String getLetter() {
            return letter;
        }

        Rank(String letter, int value) {
            this.letter = letter;
            this.value = value;
        }
    }
}
