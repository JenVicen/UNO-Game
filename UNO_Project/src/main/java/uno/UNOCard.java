/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uno;

/**
 *
 * @author Alejandro Espinoza
 */
public class UNOCard 
{
    enum Color 
    {
        Red, Blue , Green, Yellow, Wild;
        
        private static final Color[] colors = Color.values();
        public static Color getColor(int i)
        {
            return Color.colors[i];
        }
    }   
    
    enum Value 
    {
        Zero, One , Two, Three, Four, Five, Six, Seven, Eight, Nine, DrawTwo, Skip, Reverse, WildFour, Wild; 
        
        private static final Value[] values = Value.values();
        public static Value getValues(int i)
        {
            return Value.values[i];
        }
    }
    
    private final Color color;
    private final Value value;

    public UNOCard(final Color color, final Value value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return this.color;
    }

    public Value getValue() {
        return this.value;
    }

    public String toString() {
        return color + " " + value;
    }
    
    
}
