package entity;

public class ItemDonation extends Donation {
    private String itemType;

    public ItemDonation(String donorName, double amount, String itemType) {
        super(donorName, amount);
        this.itemType = itemType;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @Override
    public void recordDonation() {
        System.out.println("Item donation recorded: " + toString());
    }

    @Override
    public String toString() {
        return super.toString() + ", Item Type: " + itemType;
    }
}
