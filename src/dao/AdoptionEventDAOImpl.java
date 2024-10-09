package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdoptionEventDAOImpl implements AdoptionEventDAO {
    private Connection connection;

    public AdoptionEventDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addAdoptionEvent(String eventName, String eventDate) {
        String insertQuery = "INSERT INTO adoption_events (event_name, event_date) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, eventName);
            pstmt.setString(2, eventDate);
            pstmt.executeUpdate();
            System.out.println("Adoption event " + eventName + " has been added.");
        } catch (SQLException e) {
            System.out.println("Error adding adoption event: " + e.getMessage());
        }
    }

    @Override
    public void deleteAdoptionEvent(String eventName) {
        String deleteQuery = "DELETE FROM adoption_events WHERE event_name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(deleteQuery)) {
            pstmt.setString(1, eventName);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Adoption event " + eventName + " has been deleted.");
            } else {
                System.out.println("No event found with the name: " + eventName);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting adoption event: " + e.getMessage());
        }
    }

    @Override
    public List<String> getAdoptionEvents() {
        List<String> events = new ArrayList<>();
        String query = "SELECT * FROM adoption_events";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String eventName = rs.getString("event_name");
                events.add(eventName);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving adoption events: " + e.getMessage());
        }
        return events;
    }
}
