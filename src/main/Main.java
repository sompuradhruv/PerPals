

	package main;

	import dao.*;
	import entity.*;
	import util.*;

	import java.sql.Connection;
	import java.util.List;
	import java.util.Scanner;

	public class Main {

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        boolean isAdmin = false;

	        System.out.println("--- Welcome to PetPals ---");
	        System.out.print("Are you an Admin or User? (Enter 'admin' or 'user'): ");
	        String userType = scanner.nextLine().toLowerCase();

	        if (userType.equals("admin")) {
	            System.out.println("--- Admin Login ---");
	            System.out.print("Enter username: ");
	            String username = scanner.nextLine();
	            System.out.print("Enter password: ");
	            String password = scanner.nextLine();

	            if (username.equals(LoginDetails.USERNAME) && password.equals(LoginDetails.PASSWORD)) {
	                isAdmin = true;
	                System.out.println("Admin login successful.");
	            } else {
	                System.out.println("Invalid admin credentials. Exiting...");
	                return; 
	            }
	        } else if (!userType.equals("user")) {
	            System.out.println("Invalid option. Exiting...");
	            return; 
	        } else {
	            System.out.println("User login successful.");
	        }

	        try (Connection connection = DBConnUtil.getConnection()) {
	            PetDAO petDAO = new PetDAOImpl(connection);
	            DonationDAO donationDAO = new DonationDAOImpl(connection);
	            AdoptionEventDAO adoptionEventDAO = new AdoptionEventDAOImpl(connection);
	            ParticipantDAO participantDAO = new ParticipantDAOImpl(connection);

	            boolean exit = false;

	            while (!exit) {
	                System.out.println("\n--- PetPals Adoption Platform ---");

	                if (isAdmin) {
	                    System.out.println("1. Add a Pet");
	                    System.out.println("2. View Donation Lists");
	                    System.out.println("3. Add Adoption Event");
	                    System.out.println("4. Delete Adoption Event");
	                    System.out.println("5. Display Participants");
	                    System.out.println("6. Display Adopted Pets");  // New option for displaying adopted pets
	                    System.out.println("7. Display Pet Listings");
	                    System.out.println("8. Record a Donation");
	                    System.out.println("9. View Adoption Events");
	                    System.out.println("10. Register for Event");
	                    System.out.println("11. Adopt a Pet");
	                    System.out.println("12. Exit");
	                } else {
	                    System.out.println("1. Display Pet Listings");
	                    System.out.println("2. Record a Donation");
	                    System.out.println("3. View Adoption Events");
	                    System.out.println("4. Register for Event");
	                    System.out.println("5. Adopt a Pet");
	                    System.out.println("6. Display Adopted Pets");  
	                    System.out.println("7. Exit");
	                }

	                System.out.print("Select an option: ");
	                int option = scanner.nextInt();
	                scanner.nextLine(); 

	                if (isAdmin) {
	                    switch (option) {
	                        case 1:
	                            addPet(petDAO, scanner);
	                            break;
	                        case 2:
	                            viewDonationLists(donationDAO);
	                            break;
	                        case 3:
	                            addAdoptionEvent(adoptionEventDAO, scanner);
	                            break;
	                        case 4:
	                            deleteAdoptionEvent(adoptionEventDAO, scanner);
	                            break;
	                        case 5:
	                            displayParticipants(participantDAO);
	                            break;
	                        case 6:
	                            displayAdoptedPets(petDAO); 
	                            break;
	                        case 7:
	                            displayPetListings(petDAO);
	                            break;
	                        case 8:
	                            recordDonation(donationDAO, scanner);
	                            break;
	                        case 9:
	                            viewAdoptionEvents(adoptionEventDAO);
	                            break;
	                        case 10:
	                            registerForEvent(participantDAO, scanner);
	                            break;
	                        case 11:
	                            adoptPet(petDAO, scanner);
	                            break;
	                        case 12:
	                            exit = true;
	                            System.out.println("Exiting...");
	                            break;
	                        default:
	                            System.out.println("Invalid option. Please try again.");
	                    }
	                } else {
	                    switch (option) {
	                        case 1:
	                            displayPetListings(petDAO);
	                            break;
	                        case 2:
	                            recordDonation(donationDAO, scanner);
	                            break;
	                        case 3:
	                            viewAdoptionEvents(adoptionEventDAO);
	                            break;
	                        case 4:
	                            registerForEvent(participantDAO, scanner);
	                            break;
	                        case 5:
	                            adoptPet(petDAO, scanner);
	                            break;
	                        case 6:
	                            displayAdoptedPets(petDAO); 
	                            break;
	                        case 7:
	                            exit = true;
	                            System.out.println("Exiting...");
	                            break;
	                        default:
	                            System.out.println("Invalid option. Please try again.");
	                    }
	                }
	            }
	        } catch (Exception e) {
	            System.out.println("Database connection failed: " + e.getMessage());
	        } finally {
	            scanner.close();
	        }
	    }


	    private static void displayAdoptedPets(PetDAO petDAO) {
	        List<String> adoptedPets = petDAO.displayAdoptedPets();
	        System.out.println("\n--- Adopted Pets ---");
	        if (adoptedPets.isEmpty()) {
	            System.out.println("No pets have been adopted.");
	        } else {
	            for (String adopted : adoptedPets) {
	                System.out.println(adopted);
	            }
	        }
	    }

    
    private static void displayPetListings(PetDAO petDAO) {
        List<Pet> pets = petDAO.displayPetListings();
        System.out.println("\n--- Available Pets ---");
        if (pets.isEmpty()) {
            System.out.println("No pets available for adoption.");
        } else {
            for (Pet pet : pets) {
                System.out.println(pet);
            }
        }
    }

    private static void addPet(PetDAO petDAO, Scanner scanner) {
        System.out.print("Enter Pet Name: ");
        String name = scanner.nextLine();

        int age = 0;
        boolean validAge = false;

        while (!validAge) {
            System.out.print("Enter Pet Age: ");
            try {
                age = scanner.nextInt();
                if (age <= 0) {
                    throw new IllegalArgumentException("Age must be a positive number.");
                }
                validAge = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid age.");
                scanner.next(); 
            }
        }
        scanner.nextLine(); 

        System.out.print("Enter Pet Breed: ");
        String breed = scanner.nextLine(); 

        petDAO.addPet(new Pet(name, age, breed));
    }

    private static void recordDonation(DonationDAO donationDAO, Scanner scanner) {
        System.out.print("Enter Donor Name: ");
        String donorName = scanner.nextLine();

        double amount = 0;
        boolean validAmount = false;

        while (!validAmount) {
            System.out.print("Enter Donation Amount: ");
            try {
                amount = scanner.nextDouble();
                if (amount < 10) {
                    throw new IllegalArgumentException("Donation amount must be at least $10.");
                }
                validAmount = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.next(); 
            }
        }

        donationDAO.recordDonation(donorName, amount);
    }

    private static void viewDonationLists(DonationDAO donationDAO) {
        donationDAO.viewDonationLists();
    }

    private static void addAdoptionEvent(AdoptionEventDAO adoptionEventDAO, Scanner scanner) {
        System.out.print("Enter Adoption Event Name: ");
        String eventName = scanner.nextLine();
        System.out.print("Enter Event Date (YYYY-MM-DD): ");
        String eventDate = scanner.nextLine(); 

        adoptionEventDAO.addAdoptionEvent(eventName, eventDate);
    }

    private static void deleteAdoptionEvent(AdoptionEventDAO adoptionEventDAO, Scanner scanner) {
        System.out.print("Enter the name of the event you want to delete: ");
        String eventName = scanner.nextLine();
        adoptionEventDAO.deleteAdoptionEvent(eventName);
    }

    private static void viewAdoptionEvents(AdoptionEventDAO adoptionEventDAO) {
        List<String> events = adoptionEventDAO.getAdoptionEvents();
        System.out.println("\n--- Upcoming Adoption Events ---");
        if (events.isEmpty()) {
            System.out.println("No upcoming adoption events.");
            return;
        }

        for (String eventName : events) {
            System.out.println("Event: " + eventName);
        }
    }

    private static void registerForEvent(ParticipantDAO participantDAO, Scanner scanner) {
        System.out.print("Enter the name of the event you want to register for: ");
        String eventName = scanner.nextLine();
        System.out.print("Enter your name: ");
        String participantName = scanner.nextLine();

        participantDAO.registerParticipant(eventName, participantName);
    }

    private static void displayParticipants(ParticipantDAO participantDAO) {
        System.out.println("\n--- Participants List ---");
        List<String> participants = participantDAO.getParticipants();
        if (participants.isEmpty()) {
            System.out.println("No participants registered for events.");
        } else {
            for (String participant : participants) {
                System.out.println(participant);
            }
        }
    }
    
    private static void adoptPet(PetDAO petDAO, Scanner scanner) {
        System.out.print("Enter the name of the pet you want to adopt: ");
        String petName = scanner.nextLine();
        System.out.print("Enter your name: ");
        String ownerName = scanner.nextLine();

        List<Pet> pets = petDAO.displayPetListings();
        boolean petExists = pets.stream().anyMatch(pet -> pet.getName().equalsIgnoreCase(petName));

        if (petExists) {
            petDAO.adoptPet(petName, ownerName); 
        } else {
            System.out.println("No pet found with the name: " + petName);
        }
    }

}
