package team8.coolync.Model;

/**
 * Created by media on 2015-11-25.
 */
public class FoodItem {
    private String mName;
    private int mAmount;
    private int mThumbnail;

    public String getName() { return mName; }

    public void setName(String name) { this.mName = name; }

    public int getAmount() { return mAmount; }

    public void setAmount(int Amount) {
        this.mAmount = Amount;
    }

    public int getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.mThumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "mName='" + mName + '\'' +
                ", mAmount=" + mAmount +
                ", mThumbnail=" + mThumbnail +
                '}';
    }
}
