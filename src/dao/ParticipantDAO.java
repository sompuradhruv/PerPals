package dao;

import java.util.List;

public interface ParticipantDAO {
    void registerParticipant(String eventName, String participantName);
    List<String> getParticipants(); 
}
