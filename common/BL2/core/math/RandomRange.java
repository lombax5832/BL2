package BL2.core.math;


/**
 * Borderlands 2
 * 
 * RandomRange
 * 
 * @author lombax5832
 *
 */
public class RandomRange {

    /**
     * @param par1 First Number
     * @param par2 Second Number
     * @return Returns a random int between par1 and par2
     */
    public static int range(int par1, int par2){
        int temp = par2 - par1;
        int temp2;
        int range;
        
        temp2 = (int) (Math.random() * temp);
        
        range = temp2 + temp;
        
        return range;
    }
    
}
