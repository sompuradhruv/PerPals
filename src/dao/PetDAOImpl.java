package dao;

import entity.Pet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAOImpl implements PetDAO {
    private Connection connection;

    public PetDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addPet(Pet pet) {
        String insertQuery = "INSERT INTO pets (name, age, breed) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            pstmt.setString(1, pet.getName());
            pstmt.setInt(2, pet.getAge());
            pstmt.setString(3, pet.getBreed());
            pstmt.executeUpdate();
            System.out.println("Pet " + pet.getName() + " has been added.");
        } catch (SQLException e) {
            System.out.println("Error adding pet: " + e.getMessage());
        }
    }

    @Override
    public void removePet(String petName) {
        String deleteQuery = "DELETE FROM pets WHERE name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(deleteQuery)) {
            pstmt.setString(1, petName);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pet " + petName + " has been removed.");
            } else {
                System.out.println("No pet found with the name: " + petName);
            }
        } catch (SQLException e) {
            System.out.println("Error removing pet: " + e.getMessage());
        }
    }

    @Override
    public List<Pet> displayPetListings() {
        List<Pet> pets = new ArrayList<>();
        String query = "SELECT * FROM pets";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String breed = rs.getString("breed");
                pets.add(new Pet(name, age, breed));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving pets: " + e.getMessage());
        }
        return pets;
    }

    @Override
    public void adoptPet(String petName, String ownerName) {
        String insertAdoptedQuery = "INSERT INTO adopted (owner_name, pet_name) VALUES (?, ?)";
        String deletePetQuery = "DELETE FROM pets WHERE name = ?";

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement pstmtAdopted = connection.prepareStatement(insertAdoptedQuery)) {
                pstmtAdopted.setString(1, ownerName);
                pstmtAdopted.setString(2, petName);
                pstmtAdopted.executeUpdate();
            }

            try (PreparedStatement pstmtPet = connection.prepareStatement(deletePetQuery)) {
                pstmtPet.setString(1, petName);
                pstmtPet.executeUpdate();
            }

            connection.commit();
            System.out.println("Pet " + petName + " has been adopted by " + ownerName + ".");
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Error during adoption: " + e.getMessage());
            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
        } finally {
            try {
                connection.setAutoCommit(true);  
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }

    @Override
    public List<String> displayAdoptedPets() {
        List<String> adoptedPets = new ArrayList<>();
        String query = "SELECT owner_name, pet_name FROM adopted";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String ownerName = rs.getString("owner_name");
                String petName = rs.getString("pet_name");
                adoptedPets.add("Owner: " + ownerName + ", Pet: " + petName);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving adopted pets: " + e.getMessage());
        }
        return adoptedPets;
    }
}
