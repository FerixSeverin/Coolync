package team8.coolync.Model;

/**
 * Created by media on 2015-11-25.
 */
public class FoodItem {
    private String mName;
    private int mAmount;

    //Getter and setter for mName and mAmount
    public String getName() { return mName; }

    public void setName(String name) {
        this.mName = name;
    }

    public int getAmount() {
        return mAmount;
    }

    public void setAmount(int Amount) {
        this.mAmount = Amount;
    }
}
