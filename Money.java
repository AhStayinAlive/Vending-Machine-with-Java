/**
 * This class represent a money with a specific value denomination and a given count/stack.
 */
public class Money {
    private double value;
    private int count;

    /**
     * Class Constructor.
     * 
     * @param value  a valid denomination.
     * @param count  the number of count there is as a stack.
     */
    public Money(double value, int count) {
        this.value = value;
        this.count = count;
    }

    /**
     * Returns the denomination of this object
     * 
     * @return  a double value.
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Returns the count of this object as a stack.
     * 
     * @return  an int value.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Allows the user to change the count of the stack.
     * 
     * @param amount  the amount to be added to the current count.
     */
    public void changeCount(int amount) {
        this.count += amount;
    }

    /**
     * Returns the string format of all the details in about this object.
     * 
     * @return  a string.
     */
    public String toString() {
        String denomination = null;

        if(this.value >= 20) {
            denomination = "peso bill";
        }

        else if(this.value >= 1) {
            denomination = "peso coin";
        }

        else{
            denomination = "cent";
        }

        return this.value + " " + denomination + " ---- " + this.count + " remaining";
    }
}
