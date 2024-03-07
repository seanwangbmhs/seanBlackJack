import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {

    private Deck deck;
    private ArrayList<Card> player;
    private ArrayList<Card> dealer;

    Scanner kb;

    public BlackJack() {
        deck = new Deck();
        player = new ArrayList<>();
        dealer = new ArrayList<>();
        kb = new Scanner(System.in);
    }

    public static void main(String[] args) {
        BlackJack game = new BlackJack();
        game.run();
    }

    private void run() {
        deck.shuffle();


        player.add(deck.getCard());
        dealer.add(deck.getCard());
        player.add(deck.getCard());
        dealer.add(deck.getCard());

        System.out.println("Dealer hand: " + dealer.get(0) + " [?]");
        System.out.println("Player hand: " + player.get(0) + " " + player.get(1));


        boolean playerBusted = false;

        //loop game to play multiple hands
        while (true) {
            System.out.println("Hit or stay?");
            String response = kb.nextLine();
            if (response.equals("hit")) {
                player.add(deck.getCard());

                System.out.println("Player hand: " + getPlayerHand());
                System.out.println("Hand value: " + getHandValue(player));
                if (getHandValue(player) > 21) {
                    System.out.println("Busted! You lose.");
                    System.out.println("Do you want to play another round?");
                    playerBusted = true;
                    checkPlayAgain();
                    break;


                }
            } else if (response.equals("stay")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'hit' or 'stay'.");
            }
        }


        if (!playerBusted) {
            System.out.println("\nDealer's turn:");
            System.out.println("Dealer hand: " + getDealerHand());
            while (getHandValue(dealer) < 17) {
                dealer.add(deck.getCard());
                System.out.println("Dealer hits: " + dealer.get(dealer.size() - 1));
                System.out.println("Dealer hand: " + getDealerHand());
            }


            int playerValue = getHandValue(player);
            int dealerValue = getHandValue(dealer);

            System.out.println("\nPlayer's hand value: " + playerValue);
            System.out.println("Dealer's hand value: " + dealerValue);

            if (dealerValue > 21 || playerValue > dealerValue) {
                System.out.println("Player wins!");
                System.out.println("Do you want to play another round?");
                checkPlayAgain();
            } else if (playerValue == dealerValue) {
                System.out.println("It's a tie!");
                System.out.println("Do you want to play another round?");
                checkPlayAgain();

            } else {
                System.out.println("Dealer wins!");
                System.out.println("Do you want to play another round?");
                checkPlayAgain();
            }
        }
    }

    private String getPlayerHand() {
        String playerHandString = "";
        for (Card card : player) {
            playerHandString += card + " ";
        }
        return playerHandString;
    }

    private String getDealerHand() {
        String dealerHandString = "";
        for (int i = 0; i < dealer.size(); i++) {
            if (i == 0) {
                dealerHandString += "[?] ";
            } else {
                dealerHandString += dealer.get(i) + " ";
            }
        }
        return dealerHandString;
    }


    private int getHandValue(ArrayList<Card> hand) {
        int value = 0;
        int numAces = 0;
        for (Card card : hand) {
            value += card.getValue();
            if (card.getValue() == 11) {
                numAces++;
            }
        }

        while (value > 21 && numAces > 0) {
            value -= 10;
            numAces--;
        }
        return value;
    }
    private void checkPlayAgain() {
        String reply = kb.nextLine();
        if (reply.equals("yes")) {
            player.clear();
            dealer.clear();
            run();
        } else if (reply.equals("no")) {
            System.out.println("Thank you for playing, goodbye!");
            System.exit(0);

        } else {
            System.out.println("input error, please enter yes or no");
        }


    }



}
