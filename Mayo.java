import java.util.ArrayList;

public class Mayo extends Item{
    public Mayo() {
        super("Mayo", 0, 40);
    }

    public void addItem() {
        this.stock.add(new Mayo());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}