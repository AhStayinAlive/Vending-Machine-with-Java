import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.swing.Timer;
import java.util.Map;
import java.util.Set;

/**
 * Class constructor
 */
public class Controller {
    private VendingMachineView view;
    private VendingMachine vendingMachine;
    private Set<String> displayedItems = new HashSet<>();


    public Controller() {
        this.view = new VendingMachineView();

        /**
         * Sets the button to display the choice between creating a regular and special vending machine.
         */
        this.view.setButtonListener(0, e -> {
            view.displayVendingOptions();
        });

        /**
         * Sets the button to test a machine.
         */
        this.view.setButtonListener(1, e -> {  
            if(vendingMachine != null)  
                displayVM();
            else 
                view.displayMessage("sad.gif", "Cannot test the machine as it is not created yet.", 1);
        });
        

        /**
         * Sets the button for ending application.
         */
        this.view.setButtonListener(2, e ->{
            System.exit(1);
        });

        /**
         * Sets the button to display the maintenance screen of the machine
         */
        this.view.setButtonListener(3, e -> {
            view.displayMaintenance(vendingMachine.displayMoneyStored());
        });

        /**
         * Sets the button to display the replenish money screen.
         * Prepares the buttons that are part of this frame. 
         */
        this.view.setButtonListener(4, e -> {
            view.displayReplenishMoney();
            setReplenishMoneyButtons();
        });

        /**
         * Sets the button to display the replenish stock screen.
         * Prepares the buttons that are part of this frame. 
         */
        this.view.setButtonListener(5, e -> {
            displayReplenishStock();
        });

        /**
         * Sets the button to exit the test machine frame and dispense change
         */
        this.view.setButtonListener(6, e -> {
            ArrayList<Money> change = vendingMachine.calculateChange(vendingMachine.getTotalInserted());
            double total = 0;
            for (Money money : change)
                total += money.getValue()*money.getCount();

            vendingMachine.setTotalInserted(-total);
            view.displayMessage("change.gif", "Here's your change: " + total, 1);
            view.initializeView();
        });

        /**
         * Sets the button to display the set item price screen
         */
        this.view.setButtonListener(7, e -> {
            view.displaySetItemPrice();
        });

        /**
         *  Sets the button to collect the total payment garnered by the machine
         */    
        this.view.setButtonListener(8, e -> {
            double total = vendingMachine.collectPayment();
            if(total > 0) {
                view.displayMessage("collectPayment.gif", total + " collected.", 1);
            }
            else
                view.displayMessage("noCollect.gif", "Nothing to collect.", 1);
            
        });

        /**
         * Sets the button to display the summary of transactions screen
         */
        this.view.setButtonListener(9, e -> {
            view.updateMachine(vendingMachine);
            view.printSummary();
        });

        /**
         * Sets the the button to exit maintenance and back to the test machine screen
         */
        this.view.setButtonListener(10, e -> {
            displayVM();
        }); 

        /**
         * Sets the button to exit the maintenance panel features and back to the maintenance screen
         */
        this.view.setButtonListener(11, e -> {
            view.displayMaintenance(vendingMachine.displayMoneyStored());
            view.updateMachine(vendingMachine);
        });

        /**
         * Sets the button for creating a regular machine 
         */
        this.view.setButtonListener(12, e -> {
            vendingMachine = new VendingMachine();
            view.updateMachine(vendingMachine);
            view.displayMessage("regular.gif", "Making a regular vending machine", 1);
            view.initializeView();
        });

        /**
         * Sets the button for creating a special machine
         */
        this.view.setButtonListener(13, e -> {
            vendingMachine = new SpecialMachine();
            view.updateMachine(vendingMachine);
            view.displayMessage("special.gif", "Making a special vending machine", 1);
            view.initializeView();
        });

        /**
         * Sets the button to show the inventory window 
         */
        this.view.setButtonListener(14, e -> {
            displayInventory();
        });

        /**
         * Sets the button to purchase an order and record every individual component
         */
        this.view.setButtonListener(15, e -> {
            boolean check = true;
            String itemName = null;
            int counter;
            for (Item item : vendingMachine.getItemList()) {
                counter = 0;
                for(Item find : ((SpecialMachine) vendingMachine).getCustomOrder())
                    if(item.getName().equals(find.getName()))
                        counter++;
                if(counter > item.getStock().size()){
                    check = false;
                    itemName = item.getName();
                }
                    
            }
            if(check && !((SpecialMachine) vendingMachine).getCustomOrder().isEmpty())
                purchaseCustomOrder();
            else if(((SpecialMachine) vendingMachine).getCustomOrder().isEmpty())
                view.displayMessage("noPurchase.gif", "Nothing to pruchase.", 1);
            else
                view.displayMessage("noStock.gif", "Not enough stock for " + itemName + ".", 1);
        });

        /**
         * Sets the button to clear the inventory of an order
         */
        this.view.setButtonListener(16, e -> {
            ((SpecialMachine) vendingMachine).cancelOrder();
            view.updateMachine(vendingMachine);
            displayVM();
        });

        /**
         * Sets the button to change the price of the items in the set item price screen
         */
        this.view.setButtonListener(17, e -> {
            String price;
            for (int i = 0; i < vendingMachine.getItemList().length; i++) {
                price = view.getItemPriceTextFields()[i].getText();
                try{
                    if(Double.parseDouble(price) > 0) {
                        vendingMachine.setItemPrice(vendingMachine.getItemList()[i].getName(), Double.parseDouble(price));
                        view.getItemPriceTextFields()[i].setText(" ");
                        view.displayMessage("cart.gif", "Successfully changed price/s", 1);
                    }
                    else if(Double.parseDouble(price) <= 0)
                        view.displayMessage("error.gif", "Please input a value greater than 0.", 1);
                } catch(NumberFormatException f)  {}
                
            }
            view.updateMachine(vendingMachine);
            view.displaySetItemPrice();
        });

        this.view.setMenuDropDown(e -> {
            displayVM();
        });
    }

    /**
     * Sets up the purchase order process.
     */
    private void purchaseCustomOrder() {
        if (vendingMachine instanceof SpecialMachine) {
            SpecialMachine specialMachine = (SpecialMachine) vendingMachine;
            double totalCost = 0;
            ArrayList<Item> customOrder = specialMachine.getCustomOrder();
            Map<String, String> cookingStepsMap = new HashMap<>();
            ArrayList<String> cookingStepsList = new ArrayList<>();
            ArrayList<String> gifFilenamesList = new ArrayList<>();
            Set<String> processedItems = new HashSet<>();
            
            customOrder.add(new Bread());

            cookingStepsMap.put("Bread", "Getting Bun");
            cookingStepsMap.put("Beef Patty", "Grilling the Beef Patty.");
            cookingStepsMap.put("Chicken Patty", "Making the Chicken Patty.");
            cookingStepsMap.put("Wagyu Patty", "Searing the Wagyu Patty.");
            cookingStepsMap.put("Turkey Patty", "Cooking the Turkey Patty.");
            cookingStepsMap.put("Veggie Patty", "Making the Veggie Patty.");
            cookingStepsMap.put("Cheese", "Melting the cheese.");
            cookingStepsMap.put("Lettuce", "Adding lettuce.");
            cookingStepsMap.put("Tomato", "Slicing tomatoes.");
            cookingStepsMap.put("Onion Rings", "Topping with onion rings.");
            cookingStepsMap.put("Pickles", "Placing pickles.");
            cookingStepsMap.put("Bacon", "Cooking crispy bacon.");
            cookingStepsMap.put("Egg", "Frying egg.");
            cookingStepsMap.put("Mushroom", "Sauteing mushrooms.");
            cookingStepsMap.put("Jalapenos", "Chopping jalapenos.");
            cookingStepsMap.put("Ham", "Layering with ham.");
            cookingStepsMap.put("Mustard", "Drizzling Mustard.");
            cookingStepsMap.put("Mayo", "Adding a dollop of mayonnaise.");
            cookingStepsMap.put("Ketchup", "Applying Ketchup.");
            cookingStepsMap.put("Siracha", "Adding Siracha.");
            cookingStepsMap.put("BBQ Sauce", "Drizzling BBQ Sauce.");
        
            for (Item item : customOrder) {
                String itemName = item.getName();
                String cookingStep = cookingStepsMap.getOrDefault(itemName, "");
                if (!cookingStep.isEmpty() && !processedItems.contains(itemName)) {
                    cookingStepsList.add(cookingStep);
                    String gifFilename = itemName + ".gif";
                    gifFilenamesList.add(gifFilename);
                    processedItems.add(itemName);
                    totalCost += item.getPrice();
                }
            }
            if (vendingMachine.getTotalInserted() >= totalCost) {
                specialMachine.purchaseOrder();
                vendingMachine.setTotalInserted(-totalCost);
                specialMachine.cancelOrder();
                displayCookingSteps(cookingStepsList, gifFilenamesList, 0);
                displayVM();
            } else {
                view.displayMessage("notEnough.gif", "Not enough money to complete the order.", 1);
            }
        } 
    }

    /**
     * Plays the gifs associated with the individual components in the order.
     * @param cookingStepsList  item list
     * @param gifFilenamesList  gif list
     * @param startIndex  index of the item
     */
    private void displayCookingSteps(List<String> cookingStepsList, List<String> gifFilenamesList, int startIndex) {
        int[] nextIndex = { startIndex };
    
        if (nextIndex[0] < cookingStepsList.size()) {
            String cookingStep = cookingStepsList.get(nextIndex[0]);
            String gifFilename = gifFilenamesList.get(nextIndex[0]);
            String itemName = gifFilename.substring(0, gifFilename.lastIndexOf(".gif"));
    
            if (!displayedItems.contains(itemName)) {
                view.displayMessage(gifFilename, "" + cookingStep, 1);
                displayedItems.add(itemName);
            }
    
            nextIndex[0]++; //Cook next item
            while (nextIndex[0] < cookingStepsList.size() && cookingStepsList.get(nextIndex[0]).equals(cookingStep)) {
                nextIndex[0]++;
            }
    
            Timer timer = new Timer(1000, e -> displayCookingSteps(cookingStepsList, gifFilenamesList, nextIndex[0])); //recursion
            timer.setRepeats(false);
            timer.start();
        } else { //Clear custom order list
            displayedItems.clear(); 
            displayVM();
        }
    }
    

    /**
     * Sets up the test machine screen and all the buttons related to it 
     */
    private void displayVM() {
        String choice;
        Item[] itemList = null;
        if(vendingMachine instanceof SpecialMachine) {
            choice = view.getMenuDropdown().getSelectedItem().toString();
            switch (choice) {
                case "Patties":
                    itemList = vendingMachine.getPatties();
                    break;
                case "Add-ons":
                    itemList = vendingMachine.getAddOns();
                    break;
                case "Condiments":
                    itemList = vendingMachine.getCondiments();
                    break;
            }
        }
        else
            itemList = vendingMachine.getItemList();
        view.displayTestVM(itemList);
        view.updateInfoLabel(vendingMachine.getTotalInserted());
        setItemButtonListeners(itemList);
        setMoneyButtonListeners();
        if(vendingMachine instanceof SpecialMachine){
            setAddToCartButtons(itemList);
        }
    }

    /**
     * Sets up the screen for replenishing stock and the buttons related to the frame
     */
    private void displayReplenishStock() {
        view.displayReplenishStock();
        view.updateMachine(vendingMachine);
        setReplenishButtonListeners();
    }

    /**
     * Sets up the buttons in the replenish stock frame.
     */
    private void setReplenishButtonListeners() {
        for (int i = 0; i < view.getReplenishButtons().length; i++) {
            final int index = i;
            this.view.setReplenishButtonListener(i, e -> {
                String selectedNumber = ((String) view.getBillDropdown().getSelectedItem());
                vendingMachine.replenishStock(vendingMachine.getItemList()[index].getName(), Integer.parseInt(selectedNumber));
                displayReplenishStock();
            });
        }
    }

    /**
     * Sets up the buy buttons in the test machine frame
     * @param itemList  the item list to be shown inside the frame
     */
    private void setItemButtonListeners(Item[] itemList) {
        for (int i = 0; i < itemList.length; i++) {
            final int itemIndex = i;
            this.view.setItemButtonListener(itemIndex, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Item item = itemList[itemIndex];
                    String itemName = item.getName();
                    double itemPrice = item.getPrice();
                    double totalInserted = vendingMachine.getTotalInserted();
                    double remainingAmount;

                    if (totalInserted >= itemPrice) {
                        if (item.getStock().size() != 0) {
                            double extraAmount = vendingMachine.purchaseItem(itemName, totalInserted);
                            vendingMachine.setTotalInserted(extraAmount - totalInserted);
                            displayVM();
                            view.displayMessage("paid.gif", "Successfully Bought", 1);
                        } else
                            view.displayMessage("noStock.gif", itemName + " is out of stock.", 1);
                    } else {
                        remainingAmount = itemPrice - totalInserted;
                        view.displayMessage("insertMoney.gif", "Please insert P" + remainingAmount + " more.", 1);
                    }
                }
            });
        }
    }

    /**
     * Sets the add and minus buttons inside the inventory window
     */
    private void setCartIncreaseDecreaseButtons() {
        SpecialMachine special = (SpecialMachine) vendingMachine;
        ArrayList<Item> itemList = new ArrayList<>();
        boolean flag = false;
    
        for (Item item : vendingMachine.getItemList()) {
            flag = false;
            for (int i = 0; i < special.getCustomOrder().size() && !flag; i++) {
                if(special.getCustomOrder().get(i).getName().equals(item.getName())){
                    itemList.add(item);
                    flag = true;
                }
            }
        }
    
        for (Item item : itemList) {
            final int itemIndex = itemList.indexOf(item);
            this.view.decreaseCartButtonsListeners(itemIndex, e -> {
                special.removeComponent(item.getName());
                displayInventory();
            });
    
            this.view.increaseCartButtonsListeners(itemIndex, f -> {
                special.customizeOrder(item);
                displayInventory();
            });
        }
    }

    /**
     * Sets up the inventory window
     */
    private void displayInventory() {
        if(view.getCustomOrderDialog() != null)
            view.getCustomOrderDialog().dispose();
        view.updateMachine(vendingMachine);
        view.showCustomOrderInventoryDialog();
        setCartIncreaseDecreaseButtons();
    }

    /**
     * Sets up the add to cart buttons in the test machine screen
     * @param itemList
     */
    private void setAddToCartButtons(Item[] itemList) {
        SpecialMachine special = (SpecialMachine) vendingMachine;
        for(int i = 0; i < itemList.length; i++){
            final int itemIndex = i;
            this.view.setCartButtons(itemIndex, e ->{
                special.customizeOrder(itemList[itemIndex]);
                view.displayMessage("cart.gif", "Added to Cart", 1);
            });
        }
    }
    
    /**
     * Sets the insert money buttons in the test machine screen
     */
    private void setMoneyButtonListeners() {
        double[] validDenominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1, 0.5, 0.25};
    
        for (int i = 0; i < validDenominations.length; i++) {
            final double denomination = validDenominations[i];
            view.setMoneyButtonListener(i, e -> {
                vendingMachine.insertMoney(denomination);
                view.updateInfoLabel(vendingMachine.getTotalInserted());
            });
        }
    }
    
    /**
     * Sets up the replenish money buttons in the replenish money screen
     */
    private void setReplenishMoneyButtons() {
        for (int i = 0; i < 12; i++) { 
            final int denominationIndex = i;
            this.view.setIncreaseButtonListener(i, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double[] customOrderDenominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1, 0.5, 0.25, 0.1};
                    String selectedNumberOfBills = ((String) view.getBillDropdown().getSelectedItem());
                    vendingMachine.replenishMoney(new Money(customOrderDenominations[denominationIndex], Integer.parseInt(selectedNumberOfBills)));
                    view.updateInfoLabel(vendingMachine.displayMoneyStored());
                }
            });

            this.view.setDecreaseButtonListener(i, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double[] customOrderDenominations = {1000, 500, 200, 100, 50, 20, 10, 5, 1, 0.5, 0.25, 0.1};
                    String selectedNumberOfBills = ((String) view.getBillDropdown().getSelectedItem());
                    vendingMachine.replenishMoney(new Money(customOrderDenominations[denominationIndex], -Integer.parseInt(selectedNumberOfBills)));
                    view.updateInfoLabel(vendingMachine.displayMoneyStored());
                }
            });
        }
    }
    public static void main(String[] args) {
        new Controller();
    }
}