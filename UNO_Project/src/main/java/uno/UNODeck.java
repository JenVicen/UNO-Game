/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uno;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Alejandro Espinoza
 */
public class UNODeck {
    
    private UNOCard[] cards;
    private int cardsInDeck;
    
    public UNODeck()
    {
        cards = new UNOCard[108];
    }
    
    public void reset()
    {
        UNOCard.Color[] colors = UNOCard.Color.values();
        cardsInDeck = 0;
        
        for(int i = 0; i < colors.length - 1; i++)
        {
            UNOCard.Color color = colors [i];
            
            cards[cardsInDeck++] = new UNOCard(color, UNOCard.Value.getValues(0));
            
            for(int j = 0; j < 10; j++)
            {
                cards[cardsInDeck++] = new UNOCard(color, UNOCard.Value.getValues(j));
                cards[cardsInDeck++] = new UNOCard(color, UNOCard.Value.getValues(j));
            }
            UNOCard.Value[] values = new UNOCard.Value[]{UNOCard.Value.DrawTwo,UNOCard.Value.Skip,UNOCard.Value.Reverse};
            for(UNOCard.Value value : values)
            {
                cards[cardsInDeck++] = new UNOCard(color, value);
                cards[cardsInDeck++] = new UNOCard(color, value);
            }
        }
        
        UNOCard.Value[] values = new UNOCard.Value[]{UNOCard.Value.Wild,UNOCard.Value.WildFour};
        for(UNOCard.Value value : values)
            {
                for(int i = 0; i < 4; i++){
                cards[cardsInDeck++] = new UNOCard(UNOCard.Color.Wild, value);
            } 
        }
    }
    
    public void replaceDeckWith(ArrayList<UNOCard> cards){
        this.cards = cards.toArray(new UNOCard[cards.size()]);
        this.cardsInDeck = this.cards.length;
    }
    public boolean isEmpty(){
        return cardsInDeck == 0;
    }
    
    public void shuffle() {
        int n = cards.length;
        Random random = new Random();
        
        for (int i = 0; i < cards.length; i++)
        {
            int randomValue = i + random.nextInt(n - i);
            UNOCard randomCard = cards[randomValue];
            cards[randomValue] = cards[i];
            cards[i] = randomCard;
            
        }
    }
    public UNOCard drawCard() throws IllegalArgumentException {
    
        if (isEmpty()){
            throw new IllegalArgumentException("Cannot draw a card, the deck is empty");
        }
        return cards[--cardsInDeck];
    }
    
    public ImageIcon drawCardImage() throws IllegalArgumentException{
    
        if (isEmpty()){
            throw new IllegalArgumentException("Cannot draw a card, the deck is empty");
        }
        return new ImageIcon(cards[--cardsInDeck].toString() + ".png");
    }
}
