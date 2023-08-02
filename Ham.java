import java.util.ArrayList;

public class Ham extends Item{
    public Ham() {
        super("Ham", 20, 145);
    }

    public void addItem() {
        this.stock.add(new Ham());
    }

    public ArrayList<Item> getStock(){
        return stock;
    }
}