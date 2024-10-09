package dao;

import entity.Pet;
import java.util.List;

public interface PetDAO {
    void addPet(Pet pet);
    void removePet(String petName);
    List<Pet> displayPetListings();
    void adoptPet(String petName, String ownerName);
    List<String> displayAdoptedPets();  
}
