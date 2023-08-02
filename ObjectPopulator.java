import java.util.ArrayList;

public class ObjectPopulator { 
    private ArrayList<Money> moneyStored;
    private Item[] allSellableItems, patties, addOns, condiments;

    /**
     * Class constructor
     */
    public ObjectPopulator() {
        populateMoneyStored();
        this.patties = populatePatties();
        this.addOns = populateAddOns();
        this.condiments = populateCondiments();
        this.allSellableItems = populateItemList();
    }

    /**
     * Creates the different denominations and their stack in the machine.
     */
    private void populateMoneyStored() {
        this.moneyStored = new ArrayList<Money>();

        this.moneyStored.add(new Money(1000, 10)); 
        this.moneyStored.add(new Money(500, 20)); 
        this.moneyStored.add(new Money(200, 15)); 
        this.moneyStored.add(new Money(100, 25)); 
        this.moneyStored.add(new Money(50, 30)); 
        this.moneyStored.add(new Money(20, 50)); 
        this.moneyStored.add(new Money(10, 100)); 
        this.moneyStored.add(new Money(5, 5000)); 
        this.moneyStored.add(new Money(1, 5000)); 
        this.moneyStored.add(new Money(0.5, 5000)); 
        this.moneyStored.add(new Money(0.25, 5000));
    }

    /**
     * Creates and stores multiple items under the patty category
     * @return  an item array
     */
    private Item[] populatePatties() {
        Item chicken = new ChickenPatty(), 
            beef = new BeefPatty(), 
            turkey = new TurkeyPatty(), 
            veggie = new VeggiePatty(), 
            wagyu = new WagyuPatty();

        Item[] items = {chicken, beef, turkey, veggie, wagyu};
        
        for (Item item : items) {
            for (int i = 0; i < 10; i++)
                item.addItem(); 
        }

        return items;
    }

    /**
     * Creates and stores multiple items under the add-ons category
     * @return  an item array
     */
    private Item[] populateAddOns() {
        Item lettuce = new Lettuce(), 
            onionRings = new OnionRings(), 
            tomato = new Tomato(), 
            mushroom = new Mushroom(), 
            pickles = new Pickles(), 
            jalapenos = new Jalapenos(), 
            ham = new Ham(), 
            egg = new Egg(), 
            cheese = new Cheese(), 
            bacon = new Bacon();

        Item[] items = {lettuce, onionRings, tomato, mushroom, pickles, jalapenos, ham, egg, cheese, bacon};
        
        for (Item item : items) {
            for (int i = 0; i < 10; i++)
                item.addItem(); 
        }

        return items;
    }


    /**
     * Creates and stores multiple items under the condiments category
     * @return  an item array
     */
    private Item[] populateCondiments() {
        Item ketchup = new Ketchup(), 
            mustard = new Mustard(), 
            mayo = new Mayo(), 
            bbq = new BbqSauce(), 
            siracha = new Siracha();

        Item[] items = {ketchup, mayo, mustard, bbq, siracha};

        for (Item item : items) {
            for (int i = 0; i < 50; i++)
                item.addItem(); 
        }

        return items;
    }

    /**
     * Stores all items in every category into one array
     * @return  an item array
     */
    private Item[] populateItemList() {
        int size = this.patties.length + this.addOns.length + this.condiments.length;

        Item[] items = new Item[size];

        for (int i = 0; i < patties.length; i++)
            items[i] = patties[i];
        for (int i = patties.length; i < size - patties.length; i++)
            items[i] = addOns[i-patties.length];
        for (int index = size - patties.length; index < size; index++) 
            items[index] = condiments[index - (size - patties.length)];
        

        return items;
    }

    /**
     * Returns the add-ons item array
     * @return  an item array
     */
    public Item[] getAddOns() {
        return addOns;
    }

    /**
     * Returns the whole item list array
     * @return  an item array
     */
    public Item[] getItemList() {
        return allSellableItems;
    }

    /**
     * Returns the condiments item array
     * @return  an item array
     */
    public Item[] getCondiments() {
        return condiments;
    }

    /**
     * Returns the patties item array
     * @return  an item array
     */
    public Item[] getPatties() {
        return patties;
    }

    /**
     * Returns the money list
     * @return  an array list
     */
    public ArrayList<Money> getMoneyStored() {
        return moneyStored;
    }
}