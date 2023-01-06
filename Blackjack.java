package rory.blackjack.bbc;

import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Welcome to Blackjack
		
		System.out.println("Hi! Welcome to Blackjack");
		//Playing Deck
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();
		//Player playing Deck
		Deck playerDeck = new Deck();
		
		Deck dealerDeck = new Deck();
		
		double playerMoney = 100.00;
		
		Scanner userInput = new Scanner (System.in);
		
				
		
		//Game Loop
		while(playerMoney > 0 ) {
			//Taking Players Bet
			System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
			double playerBet = userInput.nextDouble();
			if(playerBet > playerMoney) {
				
				break;
				
			}
			
			boolean endRound = false;
			
			
			//Begin Dealing
			//2 cards for player
		
			playerDeck.draw(playingDeck);
			playerDeck.draw(playingDeck);
			
			
			//2 cards for dealer
			dealerDeck.draw(playingDeck);
			dealerDeck.draw(playingDeck);
			
			
			while(true) {
				System.out.println("Your hand");
				System.out.println(playerDeck.toString());
				System.out.println("The Value of your hand is: " + playerDeck.cardsValue());
				
				//show dealer hand
				System.out.println("Dealer Hand: " +dealerDeck.getCard(0).toString() + "and [Hidden]");
				
				//Player Options
				System.out.println("Would you like to hit (Enter 1) or stand (enter 2)?");
				int response = userInput.nextInt();
				
				//Hit
				if(response ==1) {
					playerDeck.draw(playingDeck);
					System.out.println("You draw a:" + playerDeck.getCard(playerDeck.deckSize()-1).toString());
					//bust
					if(playerDeck.cardsValue()> 21) {
						System.out.println("Bust. Value at: " + playerDeck.cardsValue());
						playerMoney -= playerBet;
						endRound = true;
						break;
					}
				}
				
				if (response==2) {
					break;
					
				}
			}
			//Show Dealer Cards
			System.out.println("Dealer Cards: " + dealerDeck.toString());
			//If dealer has higher score than player
			if(dealerDeck.cardsValue() > playerDeck.cardsValue()&& endRound == false){
				System.out.println("Dealer wins");
				playerMoney -= playerBet;
				endRound = true;
			}
			
			//draws a 16, holds at 17
			while((dealerDeck.cardsValue()< 17 ) && endRound == false) {
				dealerDeck.draw(playingDeck);
				System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
		}
			
			//Show Total Score for Dealer
			System.out.println("Dealer's Hand is valued at:" + dealerDeck.cardsValue());
			//Is Dealer busted?
			if((dealerDeck.cardsValue() > 21)&& endRound == false) {
				System.out.println("Dealer busts! You win");
				playerMoney += playerBet;
				endRound = true; 
			}
			
			//If draw
			if((playerDeck.cardsValue() == dealerDeck.cardsValue())&& endRound == false) {
				System.out.println("Draw");
				endRound = true;
				
			}
			
			if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false){
				System.out.println("You win");
				playerMoney += playerBet;
				endRound = true;
			}
			
			playerDeck.moveAllToDeck(playingDeck);
			dealerDeck.moveAllToDeck(playingDeck);
			System.out.println("End of Hand");
			
		}
		System.out.println("Game over! You are out of money.");
		

	}

}

