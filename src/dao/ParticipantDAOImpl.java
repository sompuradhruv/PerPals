package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAOImpl implements ParticipantDAO {
    private Connection connection;

    public ParticipantDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void registerParticipant(String eventName, String participantName) {
        String insertQuery = "INSERT INTO participants (event_name, participant_name) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, eventName);
            pstmt.setString(2, participantName);
            pstmt.executeUpdate();
            System.out.println("Participant " + participantName + " has been registered for event: " + eventName);
        } catch (SQLException e) {
            System.out.println("Error registering for the event: " + e.getMessage());
        }
    }

    @Override
    public List<String> getParticipants() {
        List<String> participants = new ArrayList<>();
        String query = "SELECT * FROM participants";
        
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String eventName = rs.getString("event_name");
                String participantName = rs.getString("participant_name");
                participants.add("Event: " + eventName + ", Participant: " + participantName);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving participants: " + e.getMessage());
        }
        return participants;
    }
}
