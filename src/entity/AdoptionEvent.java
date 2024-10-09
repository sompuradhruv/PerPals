package entity;

import java.util.ArrayList;

import java.util.List;
public class AdoptionEvent {
    private String eventName; 
    private List<IAdoptable> participants; 

    public AdoptionEvent(String eventName) {
        this.eventName = eventName;
        this.participants = new ArrayList<>();
    }

    public void registerParticipant(IAdoptable participant) {
        participants.add(participant);
        System.out.println("Participant registered for the adoption event.");
    }

    // Method to host the event
    public void hostEvent() {
        System.out.println("Adoption event '" + eventName + "' is starting...");
        for (IAdoptable participant : participants) {
            participant.adopt(); 
        }
        System.out.println("Adoption event concluded.");
    }

    public String getEventName() {
        return eventName;
    }
}

