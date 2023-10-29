package cardgame;

/**
 * Card - a single playing card
 * @author
 * @version
 */
public class Card
{
    // constants - to do in lectures
    final String[] SUITS = { "Hearts", "Diamonds", "Spades", "Clubs"};
    final String[] FACES = { "A", "2", "3", "4", "5",
                             "6", "7", "8", "9", "10",
                             "J", "Q", "K"};
    
    final int NOOFCARDSINSUIT = 13;
    
    // properties - to do in lectures
    int  cardNo;
    Player player;
    
    // constructors  - to do in lectures
    public Card( int faceValue, int suit )
    {
        cardNo = faceValue + suit * NOOFCARDSINSUIT;
    }
    
    public Card( int cardNumber)
    {
        cardNo = cardNumber;
    }
    
    public int getFaceValue()
    {
        return cardNo % NOOFCARDSINSUIT;
    }
    
    public int getSuit()
    {
        return cardNo / NOOFCARDSINSUIT;
    }

    // ToDo MySelf*
    public Player getPlayer() {
        return this.player;
    }

    // ToDo MySelf*
    public void setPlayer(Player player) {
        this.player = player;
    }

    public String toString()
    {
        return FACES[ getFaceValue() ] + " of " + SUITS[ getSuit() ];
    }
    
    public boolean equals( Card c)
    {
        // ToDo
        if (this.getFaceValue() == c.getFaceValue()) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public int compareTo( Card c)
    {
        // ToDo
        // Clubs > Spades > Diamonds > Hearts
        if (this.equals(c)) {
            return this.cardNo - c.cardNo;
        }
        else {
            return this.getFaceValue() - c.getFaceValue();
        }
    }
}