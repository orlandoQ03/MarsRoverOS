import java.util.Random;

public class MarsRover {
    private int longitude;
    private int latitude;
    private int energy;

    //Constructor
    public MarsRover(int latitude, int longitude, int energy) {
        this.longitude = latitude;
        this.latitude = longitude;
        this.energy = energy;
    }

    // Randomize obstacle detection
    public boolean scanArea() {
        updateEnergy(-5);
        System.out.println("Scanning area...");
        return new Random().nextBoolean();
    }

    // Recharge battery through solar panels
    public void rechargeSolarPanel() {
        updateEnergy(50);
        System.out.println("Solar panels recharged. Current energy level: " + energy);
    }

    // Print status of the rover
    public void printStatus() {
        System.out.println("Current location: (" + longitude + ", " + latitude + "). Energy level: " + energy);
    }

    // Move rover in four directions
    public void move(String direction) {
        // Check if energy is sufficient
        if (energy <= 0) {
            System.out.println("Rover is out of energy. Recharge.");
            return;
        }
        // Check for obstacles
        if (scanArea()) {
            System.out.println("Obstacle detected. Turning left...");
            latitude += 10;
            updateEnergy(-10);
            return;
        }
        // Update longitude/latitude coordinates
        System.out.println("Moving...");
        switch (direction.toUpperCase()) {
            case "FORWARD":
                latitude += 10;
                break;
            case "LEFT":
                longitude -= 10;
                break;
            case "RIGHT":
                longitude += 10;
                break;
            case "BACK":
                latitude -= 10;
                break;
        }
        // Consume energy
        updateEnergy(-15);
        printStatus();
    }
    
    public int getLatitude() {
        return this.latitude;
    }
    public int getLongitude() {
        return this.longitude;
    }
    public int getBattery() {
        return this.energy;
    }

    // Update energy levels accordingly
    private void updateEnergy(int amount) {
        energy += amount;

        // Limit energy ranges
        if (energy < 0) {
            energy = 0;
        }
        else if (energy > 100) {
            energy = 100;
        }
    }
}
