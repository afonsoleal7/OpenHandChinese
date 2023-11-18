package io.codeforall;



import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class Player {
    private static DeckOfCards deckOfCards = new DeckOfCards();

    private String username;
    protected Card[][] cards;
    protected Socket clientSocket;

    protected BufferedReader in;

    protected BufferedWriter out;

    protected PrintStream outout;

    protected boolean hasUsername = false;

    protected boolean hasPlayed = false;

    protected Prompt prompt;



    public Player(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.outout = new PrintStream(clientSocket.getOutputStream());
        this.prompt = new Prompt(clientSocket.getInputStream(), outout);

        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        this.cards = new Card[3][5];
    }




    public String getUsername() {
        return username;
    }

    public String pickWheretoPlaceACard()  {

        return ("Pick where to place this card:" + "\n" +  "1 - TOP" + "\n" + "2 - MIDDLE" + "\n" + "3 - BOTTOM" + "\n");

    }

    public boolean addCardtoTopRow(Card card) {
        for (int i = 0; i < 5; i++) {
            if (cards[0][i] == null) {
                cards[0][i] = card;
                try {
                    sendMessage(card.toString() + " was added to the top row");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
        }
        try {
            sendMessage("Not possible to add card as row is already full" );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean addCardtoMiddleRow(Card card) {
        for (int i = 0; i < 5; i++) {
            if (cards[1][i] == null) {
                cards[1][i] = card;
                try {
                    sendMessage(card.toString() + " was added to the top row" );

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
        }
        try {
            sendMessage("Not possible to add card as row is already full" );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean addCardtoBottomRow(Card card) {
        for (int i = 0; i < 5; i++) {
            if (cards[2][i] == null) {
                cards[2][i] = card;
                try {
                    sendMessage(card.toString() + " was added to the top row");


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
        }
        try {
            sendMessage("Not possible to add card as row is already full" );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;

    }

    public void displayCards() throws IOException {

        for (int i = 0; i < 3; i++) {
            sendMessage("------------------------" + "\n");

            StringBuilder s = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                if (cards[i][j] != null) {
                    s.append(cards[i][j].toStringShort());
                    s.append(" | ");
                }
            }

            sendMessage(s + "\n");
        }
    }
    public boolean areThereCardsLeft() {
       for (int i = 0; i < 3; i++){
           for (int j = 0; i < 5; i++){
               if (cards[i][j] == null){
                   return true;
               }
           }
       }
       return false;
    }



    public Card drawCard(DeckOfCards deckOfCards) throws IOException {
        Card c = deckOfCards.drawCard();
        sendMessage("Card drawn is: " + c.toString());
        return c;

    }

    public void enterUsername() {

        try {
            sendMessage("Enter your username");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            username = in.readLine();
            sendMessage("Welcome to the game " + username);
            hasUsername = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void setInicialCards() throws IOException {
        LinkedList<Card> fiveInitialCards = new LinkedList<>();
        for (int i = 0; i < 5; i++){
            fiveInitialCards.add(drawCard(deckOfCards));
        }
        while (fiveInitialCards.size() > 0){
            Card c = deckOfCards.selectInicialCard(fiveInitialCards);
            sendMessage("Card to place now is " + c.toString());
            setInicialCard(c);



        }
    }

    public void playRound() throws IOException {
        Card c = drawCard(deckOfCards);
        sendMessage(pickWheretoPlaceACard());
        int input = -1;
        try {
            input = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        switch (input) {
            case 1:
                addCardtoTopRow(c);
                System.out.println("Card added to the top");
                break;
            case 2:
                addCardtoMiddleRow(c);
                System.out.println("Card added to the middle");
                break;
            case 3:
                addCardtoBottomRow(c);
                System.out.println("Card added to the bottom");
                break;
        }
        try {
            displayCards();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void playRound2() throws IOException {
        Card c = drawCard(deckOfCards);
        String[] options = {"TOP", "MIDDLE", "BOTTOM"};
        MenuInputScanner scanner = new MenuInputScanner(options);
        scanner.setMessage("Pick where to play your card");
        int input = prompt.getUserInput(scanner);
        switch (input){
            case 1: addCardtoTopRow(c);
            break;
            case 2: addCardtoMiddleRow(c);
            break;
            case 3: addCardtoBottomRow(c);
            break;
        }
        displayCards();


    }
    public void setInicialCard(Card c) throws IOException {
        sendMessage(pickWheretoPlaceACard());




        int input = 0;
        try {
            input = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        switch (input) {
            case 1:
                addCardtoTopRow(c);
                System.out.println("Card added to the top");
                break;
            case 2:
                addCardtoMiddleRow(c);
                System.out.println("Card added to the middle");
                break;
            case 3:
                addCardtoBottomRow(c);
                System.out.println("Card added to the bottom");
                break;
        }
        try {
            displayCards();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void sendMessage(String message) throws IOException {
        out.write(message);
        out.newLine();
        out.flush();

    }

    public static void main(String[] args) throws IOException {


        //Player p2 = new Player();
        //p.setInicialCards();
        //p2.setInicialCards();
        //for (int i = 0; i < 5; i++){
           // p.playRound();
            //p2.playRound();
        }
    }






