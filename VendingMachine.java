import java.util.ArrayList;

/**
 * Class that represents a regular vending machine that has vending and maintenance feature. This can store money and items,
 * as well as receive payment and calculate change.
 * 
 * @author Ken Ivan Cheng
 * @author Joseph Dean Enriquez
 * @since Aug 3, 2023
 */
public class VendingMachine {
    private ObjectPopulator objectPopulator = new ObjectPopulator();
    private ArrayList<Money> moneyStored;
    private ArrayList<Item> transactions;
    private ArrayList<Integer> initialInventory;
    private ArrayList<Integer> finalInventory;
    private ArrayList<Money> paymentList;
    private ArrayList<Money> changeList; 
    private Item[] itemList, patties, addOns, condiments;
    private double totalInserted;
    private double[] denominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1, 0.5, 0.25};

    /**
     * Class Constructor
     */
    public VendingMachine() {
        this.transactions = new ArrayList<Item>();
        this.initialInventory = new ArrayList<Integer>();
        this.finalInventory = new ArrayList<Integer>();
        this.paymentList = new ArrayList<Money>();
        this.changeList = new ArrayList<Money>();
        this.moneyStored = objectPopulator.getMoneyStored();
        this.itemList = objectPopulator.getItemList();
        this.patties = objectPopulator.getPatties();
        this.addOns = objectPopulator.getAddOns();
        this.condiments = objectPopulator.getCondiments();
        updateInitialInventory();
    }

    /**
     * Returns the inital inventory of the machine.
     * @return  an array list
     */
    public ArrayList<Integer> getInitialInventory() {
        return this.initialInventory;
    }

    /**
     * Returns the final inventory of the machine.
     * @return  an array list
     */
    public ArrayList<Integer> getFinalInventory() {
        return this.finalInventory;
    }

    /**
     * Returns the transaction list of the machine.
     * @return  an array list
     */
    public ArrayList<Item> getTransactions() {
        return this.transactions;
    }

    /**
     * Returns the items available in the machine.
     * @return  an item array
     */
    public Item[] getItemList() {
        return itemList;
    }

    /**
     * Returns the patty items in the machine.
     * @return  an item array
     */
    public Item[] getPatties() {
        return patties;
    }
    
    /**
     * Returns the add-ons items in the machine.
     * @return  an item array
     */
    public Item[] getAddOns() {
        return addOns;
    }

    /**
     * Returns the condiments items in the machine.
     * @return  an item array
     */
    public Item[] getCondiments() {
        return condiments;
    }

    /**
     * Returns the total amount of money, inserted by the user, in the machine
     * @return  a double
     */
    public double getTotalInserted() {
        return totalInserted;
    }

    /**
     * Increments the total amount inserted, inserted by the user, in the machine
     * @param amount  to be added to the total amount
     */
    public void setTotalInserted(double amount) {
        totalInserted += amount;
    }

    /**
     * Displays the total amount of money the machine has for change dispensing.
     */
    public double displayMoneyStored() {
        double total = 0;
        for (Money money : this.moneyStored) {
            total += money.getCount() * money.getValue();
        }
        return total;
    }

    /**
     * Updates the payment made by the current user and updates the total inserted
     */
    public void insertMoney(double amount) {
        this.paymentList.add(new Money(amount, 1));
        setTotalInserted(amount);
    }

    /**
     * Finds the item in the item list of the machine.
     * When found, changes the price of the item.
     * @param itemName  name of the item that is trying to be accessed
     * @param newPrice  double value to be made as the item's new price
     * @return  a boolean value
     */
    public boolean setItemPrice(String itemName, double newPrice) {
        for (Item item : this.itemList) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                item.setPrice(newPrice);
                updateInitialInventory();
                return true;
            }
        } 
        return false;
    }
    
    /**
     * Allows the user to see and collect the total payment and clears the payment array.
     */
    public double collectPayment(){
        double total = 0;
        for (Money money : paymentList) {
            total += money.getValue()*money.getCount();
        }
        paymentList.clear();
        return total;
    }

    /**
     * Maintenance method that allows the user to replenish the money stored in the machine.
     * 
     * @param addMoney  object of type Money to be replenished
     */
    public void replenishMoney(Money addMoney) {
        int found = -1;
       for(int i = 0; i < this.moneyStored.size(); i++) {
            if(addMoney.getValue() == this.moneyStored.get(i).getValue())
                found = i;
       }
       if(found != -1)
            this.moneyStored.get(found).changeCount(addMoney.getCount());
       else
            this.moneyStored.add(addMoney);
    }

    /**
     * Maintenance method that allows the user to replenish the stock of an existing item in the machine.
     * Updates the inventory of the machine.
     * @param itemName  string name of the item that the user wants to access
     * @param amount  int value representing the amount to be added
     */
    public void replenishStock(String itemName, int amount) {
        for (Item item : this.itemList) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                for(int i = 0; i < amount; i++)
                    item.addItem();
            }
        }
        updateInitialInventory();
    }

    /**
     * Helper / internal method that updates the list of transactions whenever a successful transactions occurs.
     * Updates the inventory of the machine.
     * @param itemName  string name of the item that the user wants to access  
     */
    private void updateTransactions(String itemName) {
        for (Item item : this.itemList) {
            if(item.getName().equals(itemName)) {
                this.transactions.add(item);
                item.getStock().remove(0);
            }
        }
        updateFinalInventory();
    }
    
     /**
     * Helper / internal method that finds a specific item from the itemList. 
     * This returns a new item with similar details besides the amount, or null if there 
     * is not enough of the item in store. The amount of the new item is set to the amount the user specified.
     * 
     * @param itemName  string name of the item that the user wants to access.  
     * @return  an item Item. New item when there is enough in stock, otherwise returns nothing.
     */
    private Item getItem(String name) {
        for (Item item : itemList) {
            if (item.getName().equalsIgnoreCase(name))
                return item;
        }
        return null;
    }

    /**
     * Helper / internal method that updates the initial inventory during restocking and repricing.
     */
    private void updateInitialInventory() {
        this.initialInventory.clear();
        this.transactions.clear();
        for (Item item : this.itemList)
            initialInventory.add(item.getStock().size());
        updateFinalInventory();
    }

    /**
     * Helper / internal method that updates the final inventory after a transaction.
     */
    private void updateFinalInventory() {
        this.finalInventory.clear();
        
        for (Item item : this.itemList)
            finalInventory.add(item.getStock().size());
    }

    public ArrayList<Item> getStock(String itemName) {
        ArrayList<Item> stock = new ArrayList<Item>();
        for (Item item : this.itemList) {
            if(item.getName().equalsIgnoreCase(itemName))
                stock = item.getStock();
        }
        return stock;
    }

    /**
     * Calculates the change after a transaction.
     * 
     * @param totalInserted  the amount of money inside the payment stack at the moment.
     * @return  an array of money that is a collection of the change to be dispensed.
     */
    public ArrayList<Money> calculateChange(double totalInserted) {
        int count;

        changeList.clear(); //clear the changeList after every exit
        for (double denomination : denominations) {
            count = (int) (totalInserted / denomination);

            for (Money money : moneyStored) {
                if (money.getValue() == denomination && money.getCount() >= count) {
                    if (count > 0) {
                        changeList.add(new Money(denomination, count));
                        money.changeCount(-count);
                        totalInserted -= count * denomination;
                    }
                }
            }
        }
        if (totalInserted == 0) {
            return changeList;
        } else {
            return null;
        }
    }

    /**
     * Allows the user to purchase an item that is stored inside the machine.
     * 
     * @param itemName  string name of the item that the user wants to access.  
     * @param totalInserted  the amount of money inside the payment stack at the moment.
     * @return  a double value.
     */
    public double purchaseItem(String itemName, double totalInserted) {
        int count;
        double extraAmount, temp;
    
        Item item = getItem(itemName);
        extraAmount = totalInserted - item.getPrice();
        temp = extraAmount;
    
        for (double denomination : denominations) {
            count = (int) (temp / denomination);
            boolean check = false; // Reset check for each denomination
    
            for (Money money : moneyStored) {
                if (money.getValue() == denomination && money.getCount() >= count) {
                    check = true;
                    break; // Break out of the loop if enough denomination is found
                }
            }
            if(check)
                temp %= denomination*count;
                
            else if (!check) 
                return totalInserted;
        }
        updateTransactions(itemName);
        return extraAmount;
    }
}