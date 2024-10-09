package dao;

public interface DonationDAO {
    void recordDonation(String donorName, double amount);
    void viewDonationLists();
}
