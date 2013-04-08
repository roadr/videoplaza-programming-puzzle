package ijsenese.videoplazatest.knapsack;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the knapsack solver algorithm based on dynamic programming techniques.
 * The algorithm has computational complexity O(n,W) where n is the number of knapsack
 * elements and W is the weight the knapsack is able to carry.
 * The implementation does an optimization related to the weight capacity of the 
 * knapsack and the weight of the elements by reducing them by a factor of the 
 * greatest common divisor.
 * 
 * @author ivo.senese
 */
public class KnapsackSolver {

    private int knapsackSize;
    private List<KnapsackElement> knapsackElements;

    private int[][] memoize_matrix;
    private boolean[][] elements_picked_matrix;

    public KnapsackSolver(List<KnapsackElement> knapsackElements, int knapsackSize) {
        this.knapsackElements = knapsackElements;
        this.knapsackSize = knapsackSize;
    }
    
    private KnapsackElement getKnapsackElementInOrder(int indexPlusOne) {
        return this.knapsackElements.get(indexPlusOne - 1);
    }

    private boolean canElementSizeFitInSubSize(int elementNumber, int subSize) {
        return (this.getKnapsackElementInOrder(elementNumber).getSize() <= subSize);
    }

    private boolean isElementToBeAdded(int elementNumber, int subSize) {
        if(!canElementSizeFitInSubSize(elementNumber, subSize)) {
            return false;
        }
        return (this.getKnapsackElementInOrder(elementNumber).getValue()
                + memoize_matrix[elementNumber-1][subSize - this.getKnapsackElementInOrder(elementNumber).getSize()]
                        ) > (memoize_matrix[elementNumber-1][subSize]);
    }

    /**
     * Solves the knapsack problem using a well known in literature dynamic programming
     * technique.
     * @return 
     */
    public List<KnapsackElement> solve() {
        int numberOfElements = this.knapsackElements.size();
        
        optimizeSizesRatio();
        
        this.memoize_matrix = new int[numberOfElements+1][this.knapsackSize+1];
        this.elements_picked_matrix = new boolean[numberOfElements+1][this.knapsackSize+1];
        
        for(int i = 0; i <= this.knapsackSize; i++) {
            memoize_matrix[0][i] = 0;
        }
        
        for(int i = 1; i <= numberOfElements; i++) {
            for (int subSize = 0; subSize <= this.knapsackSize; subSize++) {

                if(this.isElementToBeAdded(i,subSize)) {
                    memoize_matrix[i][subSize] = this.getKnapsackElementInOrder(i).getValue()
                        + memoize_matrix[i-1][subSize - this.getKnapsackElementInOrder(i).getSize()];
                    elements_picked_matrix[i][subSize] = true;
                } else {
                    memoize_matrix[i][subSize] = memoize_matrix[i-1][subSize];
                    elements_picked_matrix[i][subSize] = false;
                }
            }
        }
        
        int remainingInventory = this.knapsackSize;
        List<KnapsackElement> choosenKnapsackElements = new ArrayList<KnapsackElement>();
        for (int i = numberOfElements; i >= 1; i--) {
            if(elements_picked_matrix[i][remainingInventory]) {
                choosenKnapsackElements.add(this.getKnapsackElementInOrder(i));
                remainingInventory = remainingInventory - this.getKnapsackElementInOrder(i).getSize();
            }
        }
        
        return choosenKnapsackElements;
    }
    
    /**
     * Find the common divisor for knapsack size and element sizes, and then uses it
     * as a factor by which to reduce these sizes.
     */
    private void optimizeSizesRatio() {
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(this.knapsackSize);
        
        for(KnapsackElement element: this.knapsackElements) {
            numbers.add(element.getSize());
        }
        int gcd = numbers.get(0);
        int tmp;
        for(int i = 1; i < numbers.size(); i++) {
            while(numbers.get(i) > 0) {
                tmp = numbers.get(i);
                numbers.set(i, gcd % numbers.get(i));
                gcd = tmp;
            }
        }
        
        this.knapsackSize = this.knapsackSize / gcd;
        for(KnapsackElement element: this.knapsackElements) {
            element.setSize(element.getSize() / gcd);
        }
    }
}
