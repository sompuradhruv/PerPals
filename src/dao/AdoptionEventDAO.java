package dao;

import java.util.List;

public interface AdoptionEventDAO {
    void addAdoptionEvent(String eventName, String eventDate);
    void deleteAdoptionEvent(String eventName);
    List<String> getAdoptionEvents();
}
