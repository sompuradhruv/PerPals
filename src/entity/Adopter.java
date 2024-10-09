package entity;

public class Adopter implements IAdoptable {
    private String adopterName;

    public Adopter(String adopterName) {
        this.adopterName = adopterName;
    }

    @Override
    public void adopt() {
        System.out.println(adopterName + " is adopting a pet.");
    }
}
