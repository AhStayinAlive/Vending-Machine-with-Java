import java.util.ArrayList;

/**
 * This class represents a single unique item.
 */
public abstract class Item {
    private String name;
    private double price;
    private double calories;
    protected ArrayList<Item> stock;

    /**
     * Class constructor that creates the object from scratch.
     * 
     * @param name  name of the item.
     * @param price  price of the item.
     * @param calories  amount of calories contained in the item.
     */
    public Item(String name, double price, double calories) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.stock = new ArrayList<Item>();
    }

    /**
     * Returns the item name.
     * 
     * @return  a string.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the item.
     * 
     * @return  a double.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Allows the user to set a new price to the item.
     * 
     * @param price  the new price of the item.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the calories contained in the item.
     * 
     * @return  a double.
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Returns the details of the item as a string.
     * 
     * @return  a string.
     */
    public String toString() {
        return String.format("Item: %-10s Price: %-10.2f Calories: %-10.2f", this.name, this.price, this.calories);
    }

    /**
     * Meant to add a new item of the same type as the subclass that invokes this
     */
    public abstract void addItem();

    /**
     * Meant to get the array list that acts as the stock of the subclass item object.
     * @return  an array list
     */
    public abstract ArrayList<Item> getStock();
}
