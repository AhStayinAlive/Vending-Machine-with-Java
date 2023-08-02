import java.util.ArrayList;

public class SpecialMachine extends VendingMachine{
    private ArrayList<Item> customOrder;

    /**
     * Class constructor
     */
    public SpecialMachine() {
        super();
        this.customOrder = new ArrayList<Item>();
    }
    
    /**
     * Adds an item into the custom order inventory.
     * @param addComponent  item to be added
     */
    public void customizeOrder(Item addComponent) {
        this.customOrder.add(addComponent);
    }

    /**
     * Finds the first instance of the item and removes it from the custom order inventory
     * @param itemName  name of the item to be removed
     */
    public void removeComponent(String itemName) {
        int flag = 0;
        for (int i = 0; i < customOrder.size() && flag == 0; i++) {
            if(customOrder.get(i).getName().equals(itemName)) {
                this.customOrder.remove(customOrder.get(i));
                flag = 1;
            }
        }
    }

    /**
     * Wipes the inventory clean of any item
     */
    public void cancelOrder() {
        this.customOrder.clear();
    }

    /**
     * Returns the custom order inventory
     * @return  an array list
     */
    public ArrayList<Item> getCustomOrder() {
        return this.customOrder;
    }

    /**
     * Purchases the individual components of the order and clears the inventory.
     * Adds a bread to the order as this is meant to make the order a burger.
     */
    public void purchaseOrder() {
        for (Item component : this.customOrder) {
            if(!(component instanceof Bread))
                purchaseItem(component.getName(), getTotalInserted());
        }
        cancelOrder();
        getTransactions().add(new Bread());
    }
}
