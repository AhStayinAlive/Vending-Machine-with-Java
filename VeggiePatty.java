import java.util.ArrayList;

public class VeggiePatty extends Item{

    public VeggiePatty() {
        super("Veggie Patty", 43, 177);
    }
    
    public void addItem() {
        this.stock.add(new VeggiePatty());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
     
}
