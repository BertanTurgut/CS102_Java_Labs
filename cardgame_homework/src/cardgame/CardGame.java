package cardgame;

import java.util.ArrayList;

// Cardgame
// author:
// date:
public class CardGame
{
    // properties
    Cards             fullPack;
    ArrayList<Player> players;
    ScoreCard         scoreCard;
    Cards[]           cardsOnTable;
    int               roundNo;
    int               turnOfPlayer;
    
    // constructors
    public CardGame( Player p1, Player p2, Player p3, Player p4)
    {
        // ToDo
        this.players = new ArrayList<>();

        this.fullPack = new Cards(true);
        this.fullPack.shuffle();

        this.players.add(p1);
        this.players.add(p2);
        this.players.add(p3);
        this.players.add(p4);

        this.scoreCard = new ScoreCard(this.players.size());

        this.roundNo = 0;
        this.cardsOnTable = new Cards[13];
        for (int i = 0; i < 13; i++) {
            this.cardsOnTable[i] = new Cards(false);
        }       

        this.turnOfPlayer = 0;
        
        for (int i = 0; i < 13; i++) {
            p1.add(this.fullPack.cards[i]);
            // System.out.println(p1.getCards().getCards()[i].toString());
        } 
        // System.out.println("");
        for (int i = 13; i < 26; i++) {
            p2.add(this.fullPack.cards[i]);
            // System.out.println(p2.getCards().getCards()[i - 13].toString());
        }
        // System.out.println("");
        for (int i = 26; i < 39; i++) {
            p3.add(this.fullPack.cards[i]);
            // System.out.println(p3.getCards().getCards()[i - 26].toString());
        }
        // System.out.println("");
        for (int i = 39; i < 52; i++) {
            p4.add(this.fullPack.cards[i]);
            //System.out.println(p4.getCards().getCards()[i - 39].toString());
        }
    }

    // getters and setters -- ToDo MySelf*
    public Cards[] getCardsOnTable() {
        return this.cardsOnTable;
    }

    public void setCardsOnTable(int round, int turn, Card card) {
        this.cardsOnTable[round].cards[turn] = card;
        this.turnOfPlayer++;
        if (this.turnOfPlayer == 4) {
            this.turnOfPlayer = 0;
            this.roundNo++;
        }
    }

    public ArrayList<Player> getPlayerList() {
        return this.players;
    }
    
    // methods
    public boolean playTurn( Player p, Card c)
    {
        // Todo
        return false;
    }
    
    public boolean isTurnOf( Player p)
    {
        // ToDo
        if (this.turnOfPlayer == this.players.indexOf(p)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean isGameOver()
    {
        // ToDo
        if (this.roundNo == 13) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public int getScore( int playerNumber)
    {
        // ToDo
        return this.scoreCard.scores[playerNumber];
    }
    
    public String getName( int playerNumber)
    {
        // ToDo
        return this.players.get(playerNumber).name;
    }
    
    public int getRoundNo()
    {
        // ToDo
        return this.roundNo;
    }

    public void incrementRoundNo(int increment) {
        this.roundNo += increment;
    }
    
    public int getTurnOfPlayerNo()
    {
        // ToDo
        return this.turnOfPlayer;
    }

    public void incrementTurnOfPlayerNo(int increment) {
        this.turnOfPlayer += increment;
    }
    
    public Player[] getWinners()
    {
        // ToDo        
        Player[] roundWinners = new Player[this.roundNo + 1];
        Card winner = null;
        for (int i = 0; i < this.roundNo + 1; i++) {
            Card p1_card = this.cardsOnTable[i].cards[0];
            Card p2_card = this.cardsOnTable[i].cards[1];
            Card p3_card = this.cardsOnTable[i].cards[2];
            Card p4_card = this.cardsOnTable[i].cards[3];

            p1_card.setPlayer(this.players.get(0));
            p2_card.setPlayer(this.players.get(1));
            p3_card.setPlayer(this.players.get(2));
            p4_card.setPlayer(this.players.get(3));
            
            Card semi_winner_1;
            Card semi_winner_2;
            if (p1_card.compareTo(p2_card) > 0) {
                semi_winner_1 = p1_card;
                semi_winner_1.setPlayer(p1_card.getPlayer());
            }
            else {
                semi_winner_1 = p2_card;
                semi_winner_1.setPlayer(p2_card.getPlayer());
            }

            if (p3_card.compareTo(p4_card) > 0) {
                semi_winner_2 = p3_card;
                semi_winner_2.setPlayer(p3_card.getPlayer());
            }
            else {
                semi_winner_2 = p4_card;
                semi_winner_2.setPlayer(p4_card.getPlayer());
            }

            if (semi_winner_1.compareTo(semi_winner_2) > 0) {
                winner = semi_winner_1;
                winner.setPlayer(semi_winner_1.getPlayer());
            }
            else {
                winner = semi_winner_2;
                winner.setPlayer(semi_winner_2.getPlayer());
            }

            roundWinners[i] = winner.getPlayer();
        }

        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        int p4 = 0;
        for (int i = 0; i < roundWinners.length; i++) {
            if (roundWinners[i].name.equals(this.players.get(0).getName())) {
                p1++;
            } 
            else if (roundWinners[i].name.equals(this.players.get(1).getName())) {
                p2++;
            }
            else if (roundWinners[i].name.equals(this.players.get(2).getName())) {
                p3++;
            }
            else if (roundWinners[i].name.equals(this.players.get(3).getName())) {
                p4++;
            }
        }
        this.scoreCard.setPoints(0, p1);
        this.scoreCard.setPoints(1, p2);
        this.scoreCard.setPoints(2, p3);
        this.scoreCard.setPoints(3, p4);

        return roundWinners;
    }
    
    public String showScoreCard()
    {
        return scoreCard.toString();
    }

    public int[] getScoreCard() {
        return this.scoreCard.scores;
    }
    
}