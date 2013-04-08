package ijsenese.videoplazatest.knapsack;

/**
 * KnapsackElement is the class representing the elements from which to choose when
 * trying to fill the knapsack in the knapsack problem.
 * 
 * @author ivo.senese
 */
public class KnapsackElement {

    private String name;
    private int size;
    private int value;
    
    public KnapsackElement(String name, int impressions, int value) {
        this.name = name;
        this.size = impressions;
        this.value = value;
    }

    public void setSize(int impressions) {
        this.size = impressions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public int getValue() {
        return this.value;
    }
}