package cardgame;

// Player - Simple card game player with name and hand of cards
// author:
// date:
public class Player
{
    // properties
    String name;
    Cards hand;
    
    // constructors
    public Player( String name)
    {
        // ToDo
        this.name = name;
        this.hand = new Cards(false);
    }
    
    // methods
    public String getName()
    {
        return name;
    }

    // ToDo Myself*
    public Cards getCards() {
        return this.hand;
    }
    
    public void add( Card c)
    {
        // ToDo
        this.hand.cards[this.hand.valid] = c;
        this.hand.valid++;

        c.setPlayer(this);
    }
    
    public Card playCard()
    {   
        // ToDo
        return this.hand.getTopCard();
    }
    
} // end class Player
