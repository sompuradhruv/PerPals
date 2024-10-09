package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DonationDAOImpl implements DonationDAO {
    private Connection connection;

    public DonationDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void recordDonation(String donorName, double amount) {
        String insertQuery = "INSERT INTO donations (donor_name, amount) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, donorName);
            pstmt.setDouble(2, amount);
            pstmt.executeUpdate();
            System.out.println("Donation of $" + amount + " recorded from " + donorName);
        } catch (SQLException e) {
            System.out.println("Error recording donation: " + e.getMessage());
        }
    }

    @Override
    public void viewDonationLists() {
        String query = "SELECT * FROM donations";
        try (Statement stmt = connection.createStatement(); var rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String donorName = rs.getString("donor_name");
                double amount = rs.getDouble("amount");
                System.out.println("Donor: " + donorName + ", Amount: $" + amount);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving donations: " + e.getMessage());
        }
    }
}
