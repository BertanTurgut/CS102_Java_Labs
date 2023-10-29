package cardgame;

import java.util.ArrayList;
import java.util.Random;

// Cards - Maintains a collection of zero or more playing cards.
//         Provides facilities to create a full pack of 52 cards
//         and to shuffle the cards.
// author:
// date:
public class Cards
{
    final int NOOFCARDSINFULLPACK = 52;
    
    // properties
    Card[] cards;
    int    valid;  // number of cards currently in collection
    
    // constructors
    public Cards( boolean fullPack)
    {
        cards = new Card[ NOOFCARDSINFULLPACK ];
        valid = 0;
        
        if (fullPack) {
            createFullPackOfCards();
        }
    }
    
    // methods
    public Card[] getCards() {
        return this.cards;
    }

    public void setCards(Card card, int index) {
        this.cards[index] = card;
    }

    public Card getTopCard()
    {
        Card tmp;

        if ( valid <= 0)
            return null;
        else
        {
            valid--;
            tmp = cards[valid];
            cards[valid] = null;
            return tmp;
        }
    }
    
    public boolean addTopCard( Card c)
    {
        if ( valid < cards.length)
        {
            cards[valid] = c;   // should this be cloned?
            valid++;
            return true;
        }
        return false;
    }
    
    private void createFullPackOfCards()
    {
        // Todo
        int counter = 0;
        for (int i = 0; i < 4; i++) {
            for (int u = 0; u < 13; u++) {
                this.cards[counter] = new Card(u, i);
                counter++;
            }
        }

        this.valid = 52;
    }
    
    public void shuffle()
    {
        // Todo
        ArrayList<Card> clone = new ArrayList<>();
        for (int i = 0; i < this.NOOFCARDSINFULLPACK; i++) {
            clone.add(this.cards[i]);
        }

        Random random = new Random();
        for (int i = 0; i < this.NOOFCARDSINFULLPACK; i++) {
            int a = random.nextInt(clone.size());
            this.cards[i] = clone.get(a);
            clone.remove(a);
        }
    }
    
    
    // For testOnly... remove from production version!
    public void testOnlyPrint()
    {
        for ( int i =0; i < valid; i++)
        {
            System.out.println( cards[i] );
        }
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < this.valid; i++) {
            str += this.cards[i].toString() + "||";
        }

        return str;
    }
    
} // end class Cards
