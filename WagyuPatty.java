import java.util.ArrayList;

public class WagyuPatty extends Item{

    public WagyuPatty() {
        super("Wagyu Patty", 75, 261);
    }
    
    public void addItem() {
        this.stock.add(new WagyuPatty());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
     
}
